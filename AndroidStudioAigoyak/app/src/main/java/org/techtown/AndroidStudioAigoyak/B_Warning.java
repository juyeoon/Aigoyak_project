package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class B_Warning extends AppCompatActivity {
    private static final String TAG = "B_Warning";
    Context context;

    //추가 코드(특이사항이 하나인 경우)
    ArrayList<String> keyword = new ArrayList<String>(); //특이사항 데이터 저장하는곳
    String caution; //데이터 저장
    String apiKey = MainActivity.KEY;
    String result_text;
    String code;
    String age;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.warning);
        TextView text = (TextView) findViewById(R.id.text);
        TextView dur_text = (TextView) findViewById(R.id.dur_text);
        Intent intent = getIntent();
        code = intent.getStringExtra("code");//B_MedicationList에서 품목기준코드 들고옴

        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.back_button);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        //지정한 특이사항 찾기
        String user_sql = "select FEATURE from " + NoteDatabase.TABLE_USER;
        Log.d(TAG, "user_sql : " + user_sql);

        NoteDatabase database = NoteDatabase.getInstance(context);
        Cursor user_cursor = database.rawQuery(user_sql);
        int user_recordCount = user_cursor.getCount();

        for(int i=0; i< user_recordCount; i++){
            user_cursor.moveToNext();
            if(i==0){ age = user_cursor.getString(0);}
            if(i!=0){ keyword.add(user_cursor.getString(0)); }

        }

        dur_text.setText(initLoadDB(code));
        new Thread(){
            @Override
            public void run() {
                String address = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"
                        + "?serviceKey=" + apiKey
                        + "&itemSeq=" + code;

                try {
                    URL url = new URL(address);
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(isr);
                    int eventType= xpp.getEventType();
                    String tagName;
                    result_text = "▼ 특이사항 관련\n";

                    while(eventType!=XmlPullParser.END_DOCUMENT){
                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:

                                break;

                            case XmlPullParser.START_TAG:
                                tagName=xpp.getName();

                                //주의사항
                                if(tagName.equals("atpnQesitm")){
                                    xpp.next();
                                    //p태그가 나오는 것을 막기 위한 코드
                                    String str = xpp.getText();
                                    if(str!=null){
                                        String strc = str.replaceAll("<p>", "");
                                        caution = strc.replaceAll("</p>", "\n");

                                    }
                                    else{
                                        caution ="(없음)";
                                    };
                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                tagName = xpp.getName();
                                if(tagName.equals("item")){
                                    //추가 코드(특이사항 찾기)
                                    for(int i = 0; i<keyword.size(); i++){
                                        if(caution.contains(keyword.get(i))){
                                            result_text = result_text + "'" + keyword.get(i)+"'" + "에 대한 주의사항이 있습니다.\n\n";//해당할때 일어나는 일을 여기 쓰면 됨

                                        }
                                    }
                                    if(Integer.parseInt(age) >= 65) {
                                        if (caution.contains("노인") || caution.contains("고령자")) {
                                            result_text = result_text + "'고령자(노인)'에 대한 주의사항이 있습니다.\n\n";
                                        }
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            text.setText(result_text);
                                        }
                                    });
                                }
                                break;
                        }
                        eventType=xpp.next();
                    }

                } catch (MalformedURLException e) { e.printStackTrace();
                } catch (IOException e) {e.printStackTrace();
                } catch (XmlPullParserException e) {e.printStackTrace();}
            }// run() ..
        }.start();

    }

    private String initLoadDB(String code) {
        String text="";
        DataAdapter dataAdapter = new DataAdapter(getApplicationContext());
        dataAdapter.createDatabase();
        dataAdapter.open();

        if(Integer.parseInt(age) >= 65){
            //노인 주의
            String sql6 = "select CONTENT from " + DataAdapter.TABLE_OLD + " where code= '" + code +"'";
            Log.d(TAG, "sql6 : " + sql6);
            Cursor cursor6 = dataAdapter.rawQuery(sql6);
            if (cursor6.getCount()!=0)
            {
                cursor6.moveToNext();
                text = text +"▼ 노인 주의\n" + "65세 이상의 고령자는 이 약을 사용 시 주의해야 합니다.\n\n";//멘트 추가
            }

        }

        //나이 주의
        String sql = "select LIMIT_NM, UNIT, STANDARD, CONTENT from " + DataAdapter.TABLE_AGE + " where code= '" + code +"'";
        Log.d(TAG, "sql : " + sql);
        Cursor cursor = dataAdapter.rawQuery(sql);
        if (cursor.getCount()!=0)
        {
            cursor.moveToNext();
            String limit_nm = cursor.getString(0);//나이
            String unit = cursor.getString(1);//세, 개월
            String standard = cursor.getString(2);//이상, 이하

            if(standard.equals("이상")) {//범위가 '이상'일때
                if (unit.equals("세")) {//단위가 '세'이면
                    if (Integer.parseInt(age) >= Integer.parseInt(limit_nm)) {
                        text = text + "▼ 나이 주의\n"+
                                cursor.getString(0)+
                                cursor.getString(1)+ " " +
                                cursor.getString(2) +
                                "은(는) 이 약을 사용 시 주의해야 합니다.\n\n";//멘트 추가
                    }
                } else {//단위가 '개월'이면
                    if (Integer.parseInt(age) >= Integer.parseInt(limit_nm) / 12) {
                        text = text + "▼ 나이 주의\n"+
                                cursor.getString(0)+
                                cursor.getString(1)+ " " +
                                cursor.getString(2) +
                                "은(는) 이 약을 사용 시 주의해야 합니다.\n\n";//멘트 추가
                    }
                }
            }
            else{//범위가 '이하'일때
                if (unit.equals("세")) {//단위가 '세'이면
                    if (Integer.parseInt(age) <= Integer.parseInt(limit_nm)) {
                        text = text + "▼ 나이 주의\n"+
                                cursor.getString(0)+
                                cursor.getString(1)+ " " +
                                cursor.getString(2) +
                                "은(는) 이 약을 사용 시 주의해야 합니다.\n\n";//멘트 추가
                    }
                } else {//단위가 '개월'이면
                    if (Integer.parseInt(age) <= Integer.parseInt(limit_nm) / 12) {
                        text = text + "▼ 나이 주의\n"+
                                cursor.getString(0)+
                                cursor.getString(1)+ " " +
                                cursor.getString(2) +
                                "은(는) 이 약을 사용 시 주의해야 합니다.\n\n";//멘트 추가
                    }
                }
            }
        }

        //투여기간
        String sql3 = "select CONTENT from " + DataAdapter.TABLE_PERIOD + " where code= '" + code +"'";
        Log.d(TAG, "sql3 : " + sql3);
        Cursor cursor3 = dataAdapter.rawQuery(sql3);
        if (cursor3.getCount()!=0)
        {
            cursor3.moveToNext();
            text = text +"▼ 투여기간 주의\n"+
                    "이 약의 최대 투여 기간은 " + cursor3.getString(0) + "일입니다.\n\n";//멘트 추가
        }

        //용량
        String sql4 = "select CONTENT from " + DataAdapter.TABLE_AMOUNT + " where code= '" + code +"'";
        Log.d(TAG, "sql4 : " + sql4);
        Cursor cursor4 = dataAdapter.rawQuery(sql4);
        if (cursor4.getCount()!=0)
        {
            cursor4.moveToNext();
            text = text +"▼ 용량 주의\n"+
                    "이 약의 1일 최대 용량은 " + cursor4.getString(0) + "입니다.\n\n";//멘트 추가
        }

        //병용금기
        String code_a_date = "0";
        String code_b_date[] = new String[50];
        String code_b[] = new String[50];
        String code_b_name[] = new String[50];
        NoteDatabase database = NoteDatabase.getInstance(getApplicationContext());

        String manage_sql_a = "select date from " + NoteDatabase.TABLE_NOTE + " where code = '" + code + "'";
        if(database !=null) {
            Cursor aCursor = database.rawQuery(manage_sql_a);
            aCursor.moveToNext();
            code_a_date = aCursor.getString(0);
        }

        String manage_sql = "select name, code, date from " + NoteDatabase.TABLE_NOTE + " where date = '" + code_a_date + "'";
        int recordCount = -1;
        int index = 0;//code_b의 index
        if(database!=null){
            Cursor manageCursor = database.rawQuery(manage_sql);
            recordCount = manageCursor.getCount();

            for(int i=0; i<recordCount; i++){
                manageCursor.moveToNext();
                code_b_name[index] = manageCursor.getString(0);
                code_b[index] = manageCursor.getString(1);
                code_b_date[index] = manageCursor.getString(2);

                index++;
            }
        }

        for(int i=0; i<index; i++) {

            String sql5 = "select CONTENT from " + DataAdapter.TABLE_WITH + " where code_a= " + code + " AND code_b= " + code_b[i];
            Log.d(TAG, "sql5 : " + sql5);
            Cursor cursor5 = dataAdapter.rawQuery(sql5);

            if (cursor5.getCount()!=0) {
                if (i == 0) {
                    text = text + "▼ 병용 금기\n";
                }
                cursor5.moveToNext();
                text = text + "이 약은 " + code_b_name[i] + "와 함께 사용할 수 없습니다.\n ->";
            }
        }
        // db 닫기
        dataAdapter.close();
        return text;
    }
}
