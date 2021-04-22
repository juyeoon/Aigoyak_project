package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class F_MypageChange extends AppCompatActivity {
    private static final String TAG = "F_MypageChange";
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mypage_change);
        EditText age = (EditText) findViewById(R.id.age);


        String sql = "select FEATURE from " + NoteDatabase.TABLE_USER;//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        Cursor cursor = database.rawQuery(sql);
        int recordCount = cursor.getCount();
        String text[] = new String[recordCount];

        for(int i=0; i< recordCount; i++){
            cursor.moveToNext();
            text[i] = cursor.getString(0);

            System.out.println(text);
        }
        Button button1 = (Button) findViewById(R.id.MAO);
        Button button2 = (Button) findViewById(R.id.과민증);
        Button button3 = (Button) findViewById(R.id.피부염);
        Button button4 = (Button) findViewById(R.id.녹내장);
        Button button5 = (Button) findViewById(R.id.당뇨);
        Button button6 = (Button) findViewById(R.id.두드러기);
        Button button7 = (Button) findViewById(R.id.배뇨);
        Button button8 = (Button) findViewById(R.id.부정맥);
        Button button9 = (Button) findViewById(R.id.소화성궤양);
        Button button10 = (Button) findViewById(R.id.신장);
        Button button11 = (Button) findViewById(R.id.심장);
        Button button12 = (Button) findViewById(R.id.알레르기);
        Button button13 = (Button) findViewById(R.id.유당분해효소결핍증);
        Button button14 = (Button) findViewById(R.id.위장);
        Button button15 = (Button) findViewById(R.id.임부);
        Button button16 = (Button) findViewById(R.id.천식);
        Button button17 = (Button) findViewById(R.id.혈액응고);
        Button button18 = (Button) findViewById(R.id.피부감염증);
        Button button19 = (Button) findViewById(R.id.혈압);
        Button button20 = (Button) findViewById(R.id.추가등록);

        String text1 = button1.getText().toString();
        String text2 = button2.getText().toString();
        String text3 = button3.getText().toString();
        String text4 = button4.getText().toString();
        String text5 = button5.getText().toString();
        String text6 = button6.getText().toString();
        String text7 = button7.getText().toString();
        String text8 = button8.getText().toString();
        String text9 = button9.getText().toString();
        String text10 = button10.getText().toString();
        String text11 = button11.getText().toString();
        String text12 = button12.getText().toString();
        String text13 = button13.getText().toString();
        String text14 = button14.getText().toString();
        String text15 = button15.getText().toString();
        String text16 = button16.getText().toString();
        String text17 = button17.getText().toString();
        String text18 = button18.getText().toString();
        String text19 = button19.getText().toString();
        String text20 = button20.getText().toString();

        for(int i=0; i< recordCount; i++){
            if(text1.equals(text[i])){
                button1.setSelected(true);
            }
            else if(text2.equals(text[i])){
                button2.setSelected(true);
            }
            else if(text3.equals(text[i])){
                button3.setSelected(true);
            }
            else if(text4.equals(text[i])){
                button4.setSelected(true);
            }
            else if(text5.equals(text[i])){
                button5.setSelected(true);
            }
            else if(text6.equals(text[i])){
                button6.setSelected(true);
            }
            else if(text7.equals(text[i])){
                button7.setSelected(true);
            }
            else if(text8.equals(text[i])){
                button8.setSelected(true);
            }
            else if(text9.equals(text[i])){
                button9.setSelected(true);
            }
            else if(text10.equals(text[i])){
                button10.setSelected(true);
            }
            else if(text11.equals(text[i])){
                button11.setSelected(true);
            }
            else if(text12.equals(text[i])){
                button12.setSelected(true);
            }
            else if(text13.equals(text[i])){
                button13.setSelected(true);
            }
            else if(text14.equals(text[i])){
                button14.setSelected(true);
            }
            else if(text15.equals(text[i])){
                button15.setSelected(true);
            }
            else if(text16.equals(text[i])){
                button16.setSelected(true);
            }
            else if(text17.equals(text[i])){
                button17.setSelected(true);
            }
            else if(text18.equals(text[i])){
                button18.setSelected(true);
            }
            else if(text19.equals(text[i])){
                button19.setSelected(true);
            }
            else{//추가 등록에 대한 text가 저장되어 있을 때
                if(i != 0) {
                    System.out.println("i값: " + i);
                    button20.setText(text[i]);//그 텍스트 저장
                    button20.setSelected(true);
                }
            }//그래서 만약 추가 키워드 추가하고 디비 저장 안 하면 나갔다 오면 저장 안 되어 있을거임

        }
        buttonAction(button1, text1);
        buttonAction(button2, text2);
        buttonAction(button3, text3);
        buttonAction(button4, text4);
        buttonAction(button5, text5);
        buttonAction(button6, text6);
        buttonAction(button7, text7);
        buttonAction(button8, text8);
        buttonAction(button9, text9);
        buttonAction(button10, text10);
        buttonAction(button11, text11);
        buttonAction(button12, text12);
        buttonAction(button13, text13);
        buttonAction(button14, text14);
        buttonAction(button15, text15);
        buttonAction(button16, text16);
        buttonAction(button17, text17);
        buttonAction(button18, text18);
        buttonAction(button19, text19);


        button20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button20.isSelected()){
                    String text20 = button20.getText().toString();
                    button20.setSelected(false);
                    deleteNote(text20);
                }
                else{
                    String text20 = button20.getText().toString();
                    button20.setSelected(true);
                    changeNote(text20);
                }
            }
        });


        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.back_button);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        //확인 버튼 누름
        Button button_finish = (Button) findViewById(R.id.ok_button);
        button_finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!age.getText().toString().equals("")){
                    saveNote(age.getText().toString(), text[0]);
                    Intent intent = new Intent(F_MypageChange.this, F_MypageDetail.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"나이를 입력하세요!", Toast.LENGTH_LONG).show();
                }
            }
        });

        ///키워드 추가
        Button add = (Button) findViewById(R.id.add);
        EditText keyword = (EditText) findViewById(R.id.keyword);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button20.getText().toString().equals("추가등록")){

                    button20.setText(keyword.getText().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"키워드를 삭제하고 시도해주세요.", Toast.LENGTH_LONG).show();
                }

            }
        });

        ///키워드 삭제
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!button20.getText().toString().equals("추가등록")) {
                    String text20 = button20.getText().toString();
                    deleteNote(text20);
                    button20.setSelected(false);
                    button20.setText("추가등록");
                }
            }
        });
    }

    //나이 db에 저장
    private void saveNote(String age, String text){
        String sql = "UPDATE "+NoteDatabase.TABLE_USER+ " SET FEATURE = '" + age +"' where feature = '"+text+"'";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }

    //특이사항 db저장
    private void changeNote(String name){
        String feature = name;

        String sql = "insert into " +NoteDatabase.TABLE_USER + "(FEATURE) values (" + "'"+ feature + "')";

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }

    //특이사항 db삭제
    private void deleteNote(String name){
        String feature = name;

        String sql = "delete from "+NoteDatabase.TABLE_USER+ " where feature = '" + feature + "'";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }
    //버튼액션
    public void buttonAction(Button button, String text){

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (button.isSelected()) {
                    button.setSelected(false);
                    deleteNote(text);
                } else {
                    button.setSelected(true);
                    changeNote(text);
                }
            }
        });
    }
}