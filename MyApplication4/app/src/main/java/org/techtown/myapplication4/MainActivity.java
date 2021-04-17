package org.techtown.myapplication4;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    //데이터 받아 온 것 여기 저장
    String sampleCode = "199200813";

    //추가 코드(특이사항이 하나인 경우)
    ArrayList<String> keyword = new ArrayList<String>(); //특이사항 데이터 저장하는곳

    //************************************************ apiKey 넣어야함 8888888888888888888888888888888888888888888888888888
    String apiKey="COqqRqdIM6Kkz9qfzXGH5geAKxrfy90RL6AhqU4%2BaUT19SMd4Oy0YM7lpTZP8%2BY%2FgegeDNplMu%2FA%2B8HdJfGhKQ%3D%3D";
    String imageURL; //이미지 URL 저장

    ListView listView;
    ArrayAdapter adapter;

    ArrayList<String> items = new ArrayList<String>(); //데이터 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
    }

    public void clickBtn(View view) {
        //확인용 코드//사용할 땐 데이터베이스에 있는 거 넣기//없을땐 넣기 전에 null 걸러야함
        keyword.add("과민증 환자");
        keyword.add("이 약에");
        keyword.add("마십시오.");
        keyword.add("용법과 용량을");
        keyword.add("확인용 문자열");
        keyword.add("임산부");
        keyword.add("고혈압");

        new Thread(){
            @Override
            public void run() {
                items.clear();  //배열 초기화

                //API2
                String address = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"
                        + "?serviceKey=" + apiKey
                        + "&itemSeq=" + sampleCode;

                try {
                    //API2 관련
                    URL url = new URL(address);
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(isr);
                    int eventType= xpp.getEventType();
                    String tagName;

                    //buffer는 하나만 사용 -> 글이 이어져야하기 때문
                    StringBuffer buffer=null;

                    //API2 parsing
                    while(eventType!=XmlPullParser.END_DOCUMENT){
                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                //toast message
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"파싱 시작",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;

                            case XmlPullParser.START_TAG:
                                tagName=xpp.getName();
                                //시작
                                if(tagName.equals("item")){
                                    buffer=new StringBuffer();
                                }
                                //주의사항
                                else if(tagName.equals("atpnQesitm")){
                                    xpp.next();
                                    //p태그가 나오는 것을 막기 위한 코드
                                    String str = xpp.getText();
                                    if(str!=null){
                                        String strc = str.replaceAll("<p>", "");
                                        strc = strc.replaceAll("</p>", "\n");
                                        buffer.append(strc+" ");
                                    }
                                    else{
                                        buffer.append("(없음)");  //이거는 있어야할지 없어도 될지 잘 모르겠음
                                    };
                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                tagName = xpp.getName();
                                if(tagName.equals("item")){
                                    items.add(buffer.toString());   //배열 저장

                                    //추가 코드(특이사항 찾기)
                                    for(int i = 0; i<keyword.size(); i++){
                                        if(buffer.toString().contains(keyword.get(i))){
                                            System.out.println("찾았음");//해당할때 일어나는 일을 여기 쓰면 됨
                                        }
                                        else{
                                            System.out.println("없음");//해당 없을 때 일어나는 일을 여기 쓰면 됨
                                        }
                                    }

                                    //리스트뷰 갱신
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                                break;
                        }
                        eventType=xpp.next();
                    }

                    //toast message
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "파싱종료",Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (MalformedURLException e) { e.printStackTrace();
                } catch (IOException e) {e.printStackTrace();
                } catch (XmlPullParserException e) {e.printStackTrace();}
            }// run() ..
        }.start();
    }
}