
//제품명이 중복 없다고 했을 때 -> 중복 있어도 괜춘
//2는 있고 3은 없을때 해결 됨
//2에도 없으면 터짐
//버퍼에 적어놓은 순서대로 안 들어감. xml 태그 순서임(String 주르륵 쓰려고 작업해놓음)

package org.techtown.myapplication;

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
    //String sampleName = "타이레놀8시간이알서방정"; //사용할지 안 할지 모르겠음
    //String sampleCode = "199603003";//예시 데이터
    String sampleCode = "199200813";


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
        new Thread(){
            @Override
            public void run() {
                items.clear();  //배열 초기화

                //API2
                String address = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"
                        + "?serviceKey=" + apiKey
                        //+ "&itemName=" + sampleName //사용하게 되면 넣기
                        + "&itemSeq=" + sampleCode;

                //API3
                String address2 = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList"
                        + "?ServiceKey=" + apiKey
                        //+ "&item_name=" + sampleName  //사용하게 되면 넣기
                        + "&item_seq=" + sampleCode;


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

                    //API3 관련
                    URL url2 = new URL(address2);
                    InputStream is2 = url2.openStream();
                    InputStreamReader isr2 = new InputStreamReader(is2);
                    XmlPullParserFactory factory2 = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp2 = factory2.newPullParser();
                    xpp2.setInput(isr2);
                    int eventType2= xpp2.getEventType();
                    String tagName2;

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
                                        Toast.makeText(MainActivity.this,"파싱 시작(2).",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;

                            case XmlPullParser.START_TAG:
                                tagName=xpp.getName();
                                //시작
                                if(tagName.equals("item")){
                                    buffer=new StringBuffer();
                                }
////////////////////////////****순서대로 나오는 것이 아님. xml문서의 태그 순서와 같음///////////////////////////////
                                //이미지
                                else if(tagName.equals("itemImage")){
                                    xpp.next();
                                    imageURL=xpp.getText();
                                }
                                //회사명
                                else if(tagName.equals("entpName")){
                                    buffer.append("▼ 회사명");
                                    xpp.next();
                                    buffer.append("\n"+xpp.getText()+"\n\n");  //아래 두줄을 한줄로 줄일 수 있다.
                                }
                                //제품명
                                else if(tagName.equals("itemName")){
                                    buffer.append("▼ 제품명");
                                    xpp.next();
                                    buffer.append("\n"+xpp.getText()+"\n\n");
                                }
//////////////////////////////////null이 있는 경우 코드 추가 필요
                                //효능
                                else if(tagName.equals("efcyQesitm")){
                                    buffer.append("▼ 효과 및 효능");
                                    xpp.next();
                                    //다른 태그가 나오는 것을 막기 위한 코드
                                    String str = xpp.getText();
                                    if(str!=null){
                                        String strc = str.replaceAll("<p>", "");
                                        strc = strc.replaceAll("</p>", "");
                                        strc = strc.replaceAll("<sup>", "");
                                        strc = strc.replaceAll("</sup>", "");
                                        strc = strc.replaceAll("<sub>", "");
                                        strc = strc.replaceAll("</sup>", "");
                                        buffer.append("\n"+strc+"\n\n");
                                    }
                                    else{
                                        buffer.append("\n(없음)\n\n");
                                    }
                                }
                                //사용법
                                else if(tagName.equals("useMethodQesitm")){
                                    buffer.append("▼ 사용법");
                                    xpp.next();
                                    //p태그가 나오는 것을 막기 위한 코드
                                    String str = xpp.getText();
                                    if(str!=null){
                                        String strc = str.replaceAll("<p>", "");
                                        strc = strc.replaceAll("</p>", "");
                                        buffer.append("\n"+strc+"\n\n");
                                    }
                                    else{
                                        buffer.append("\n(없음)\n\n");
                                    }
                                }
                                //주의사항 경고//새 버전
                                else if(tagName.equals("atpnWarnQesitm")){
                                    xpp.next();
                                    //p태그가 나오는 것을 막기 위한 코드
                                    String str = xpp.getText();
                                    if(str!=null){
                                        String strc = str.replaceAll("<p>", "");
                                        strc = strc.replaceAll("</p>", "");
                                        buffer.append("▼ 사용 전 반드시 알아야 할 사항");
                                        buffer.append("\n"+strc+"\n\n");
                                    }
                                }
                                //주의사항
                                else if(tagName.equals("atpnQesitm")){
                                    buffer.append("▼ 사용상 주의사항");
                                    xpp.next();
                                    //p태그가 나오는 것을 막기 위한 코드
                                    String str = xpp.getText();
                                    if(str!=null){
                                        String strc = str.replaceAll("<p>", "");
                                        strc = strc.replaceAll("</p>", "");
                                        buffer.append("\n"+strc+"\n\n");
                                    }
                                    else{
                                        buffer.append("\n(없음)\n\n");
                                    };
                                }
                                //상호작용
                                else if(tagName.equals("intrcQesitm")){
                                    buffer.append("▼ 사용하는 동안 주의해야할 사항");
                                    xpp.next();
                                    //p태그가 나오는 것을 막기 위한 코드
                                    String str = xpp.getText();
                                    if(str!=null){
                                        String strc = str.replaceAll("<p>", "");
                                        strc = strc.replaceAll("</p>", "");
                                        buffer.append("\n"+strc+"\n\n");
                                    }
                                    else{
                                        buffer.append("\n(없음)\n\n");
                                    };
                                }
                                //부작용
                                else if(tagName.equals("seQesitm")){
                                    buffer.append("▼ 부작용");
                                    xpp.next();
                                    //p태그가 나오는 것을 막기 위한 코드
                                    String str = xpp.getText();
                                    if(str!=null){
                                        String strc = str.replaceAll("<p>", "");
                                        strc = strc.replaceAll("</p>", "");
                                        buffer.append("\n"+strc+"\n\n");
                                    }
                                    else{
                                        buffer.append("\n(없음)\n\n");
                                    };
                                }
                                //보관법
                                else if(tagName.equals("depositMethodQesitm")){
                                    buffer.append("▼ 보관법");
                                    xpp.next();
                                    //p태그가 나오는 것을 막기 위한 코드
                                    String str = xpp.getText();
                                    if(str!=null){
                                        String strc = str.replaceAll("<p>", "");
                                        strc = strc.replaceAll("</p>", "");
                                        buffer.append("\n"+strc+"\n\n");
                                    }
                                    else{
                                        buffer.append("\n(없음)\n\n");
                                    };
                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                tagName = xpp.getName();
                                if(tagName.equals("item")){
////////////////////////////////////확인용(성분 넣을 곳 확보하는 코드)
                                    buffer.append("▼ 주성분");
                                    buffer.append("\n(아직 NULL)\n\n");
                                }
                                break;
                        }
                        eventType=xpp.next();
                    }


                    //API3 parsing
                    while(eventType2!=XmlPullParser.END_DOCUMENT){
                        switch (eventType2){
                            case XmlPullParser.START_DOCUMENT:
                                //toast message
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"파싱 시작(3).",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;

                            case XmlPullParser.START_TAG:
                                tagName2=xpp2.getName();
                                //시작
                                if(tagName2.equals("item")){
                                    //buffer=new StringBuffer();
                                }
                                //전문의약품 or 일반의약품
                                else if(tagName2.equals("ETC_OTC_NAME")){
                                    buffer.append("▼ 의약품 구분");
                                    xpp2.next();
                                    buffer.append("\n"+xpp2.getText()+"\n\n");
                                }
                                //의약품 종류
                                else if(tagName2.equals("CLASS_NAME")){
                                    buffer.append("▼ 의약품 분류");
                                    xpp2.next();
                                    buffer.append("\n"+xpp2.getText()+"\n\n");
                                }
                                //성상
                                else if(tagName2.equals("CHART")){
                                    buffer.append("▼ 색상 및 형태");
                                    xpp2.next();
                                    buffer.append("\n"+xpp2.getText()+"\n\n");
                                }
                                //제형
                                else if(tagName2.equals("FORM_CODE_NAME")){
                                    buffer.append("▼ 제형");
                                    xpp2.next();
                                    buffer.append("\n"+xpp2.getText()+"\n\n");
                                }
                                //모양(식별)
                                else if(tagName2.equals("DRUG_SHAPE")){
                                    buffer.append("▼ 모양");
                                    xpp2.next();
                                    buffer.append("\n"+xpp2.getText()+"\n\n");
                                }

                                //세트 "COLOR_CLASS1" & "COLOR_CLASS2"(색깔)
                                else if(tagName2.equals("COLOR_CLASS1")){
                                    buffer.append("▼색깔\n");
                                    xpp2.next();
                                    //null -> 없음
                                    String str = xpp2.getText();
                                    if(str!=null){
                                        buffer.append("(앞)"+str+"\n");
                                    }
                                    else{
                                        buffer.append("(앞)없음"+"\n");
                                    }
                                }
                                else if(tagName2.equals("COLOR_CLASS2")){
                                    xpp2.next();
                                    //null -> 없음
                                    String str = xpp2.getText();
                                    if(str!=null){
                                        buffer.append("(뒤)"+str+"\n\n");
                                    }
                                    else{
                                        buffer.append("(뒤)없음"+"\n\n");
                                    }
                                }

                                //세트 "LENG_LONG" & "LENG_SHORT" & "THICK"(크기)
                                else if(tagName2.equals("LENG_LONG")){
                                    buffer.append("▼크기(mm)\n");
                                    xpp2.next();
                                    //null -> 없음
                                    String str = xpp2.getText();
                                    if(str!=null){
                                        buffer.append("(장축)"+str+"\n");
                                    }
                                    else{
                                        buffer.append("(장축)없음"+"\n");
                                    }
                                }
                                else if(tagName2.equals("LENG_SHORT")){
                                    xpp2.next();
                                    //null -> 없음
                                    String str = xpp2.getText();
                                    if(str!=null){
                                        buffer.append("(단축)"+str+"\n");
                                    }
                                    else{
                                        buffer.append("(단축)없음"+"\n");
                                    }
                                }
                                else if(tagName2.equals("THICK")){
                                    xpp2.next();
                                    //null -> 없음
                                    String str = xpp2.getText();
                                    if(str!=null){
                                        buffer.append("(두께)"+str+"\n\n");
                                    }
                                    else{
                                        buffer.append("(두께)없음"+"\n\n");
                                    }
                                }

                                //세트 "LINE_FRONT" & "LINE_BACK" (분할선)
                                else if(tagName2.equals("LINE_FRONT")){
                                    buffer.append("▼분할선\n");
                                    xpp2.next();
                                    //null -> 없음
                                    String str = xpp2.getText();
                                    if(str!=null){
                                        buffer.append("(앞)"+str+"\n");
                                    }
                                    else{
                                        buffer.append("(앞)없음"+"\n");
                                    }
                                }
                                else if(tagName2.equals("LINE_BACK")){
                                    xpp2.next();
                                    //null -> 없음
                                    String str = xpp2.getText();
                                    if(str!=null){
                                        buffer.append("(뒤)"+str+"\n\n");
                                    }
                                    else{
                                        buffer.append("(뒤)없음"+"\n\n");
                                    }
                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                tagName2 = xpp2.getName();
                                if(tagName2.equals("items")){//**꼭 items여야함 //3이 없어도 2가 나와야하기 때문

                                    items.add(buffer.toString());   //배열 저장

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
                        eventType2=xpp2.next();
                    }//while ..


                    //toast message
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "파싱종료!!(2,3)",Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (MalformedURLException e) { e.printStackTrace();
                } catch (IOException e) {e.printStackTrace();
                } catch (XmlPullParserException e) {e.printStackTrace();}
            }// run() ..
        }.start();
    }
}


