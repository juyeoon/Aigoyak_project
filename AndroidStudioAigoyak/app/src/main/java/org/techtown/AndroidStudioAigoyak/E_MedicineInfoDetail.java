package org.techtown.AndroidStudioAigoyak;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class E_MedicineInfoDetail extends Fragment {
    ViewGroup viewGroup;

    //데이터 받아 온 것 여기 저장
    //String sampleName = "타이레놀8시간이알서방정"; //사용할지 안 할지 모르겠음
    //String sampleCode = "199603003"; //예시 데이터

    //************************************************ apiKey 넣어야함 8888888888888888888888888888888888888888888888888888
    String apiKey="COqqRqdIM6Kkz9qfzXGH5geAKxrfy90RL6AhqU4%2BaUT19SMd4Oy0YM7lpTZP8%2BY%2FgegeDNplMu%2FA%2B8HdJfGhKQ%3D%3D";
    String imageURL; //이미지 URL 저장

    TextView text;
    String sampleCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_medicine_info_detail, container, false);
        text = viewGroup.findViewById(R.id.textView);

        Bundle bundle =getArguments();
        sampleCode = bundle.getString("code");//D_SearchAdapter에서 품목기준코드 들고옴



        new Thread(){
            @Override
            public void run() {

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

                                    break;

                                case XmlPullParser.START_TAG:
                                    tagName=xpp.getName();
                                    //시작
                                    if(tagName.equals("item")){
                                        buffer=new StringBuffer();
                                    }
////////////////////////////****순서대로 나오는 것이 아님. xml문서의 태그 순서와 같음///////////////////////////////
                                    //이미지(null 존재)
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
                                    //효능
                                    else if(tagName.equals("efcyQesitm")){
                                        xpp.next();
                                        String str = xpp.getText();
                                        if(str != null){
                                            String strc = str.replaceAll("<p>", ""); //p태그가 나오는 것을 막기 위한 코드
                                            strc = strc.replaceAll("</p>", "");
                                            buffer.append("▼ 효과 및 효능");
                                            buffer.append("\n"+strc+"\n\n");
                                        }
                                    }
                                    //사용법
                                    else if(tagName.equals("useMethodQesitm")){
                                        xpp.next();
                                        String str = xpp.getText();
                                        if(str != null){
                                            String strc = str.replaceAll("<p>", "");
                                            strc = strc.replaceAll("</p>", "");
                                            strc = strc.replaceAll("<sup>", "");
                                            strc = strc.replaceAll("</sup>", "");
                                            strc = strc.replaceAll("<sub>", "");
                                            strc = strc.replaceAll("</sub>", "");
                                            buffer.append("▼ 사용법");
                                            buffer.append("\n"+strc+"\n\n");
                                        }
                                    }
                                    //주의사항 경고
                                    else if(tagName.equals("atpnWarnQesitm")){
                                        xpp.next();
                                        String str = xpp.getText();
                                        if(str != null){
                                            String strc = str.replaceAll("<p>", "");
                                            strc = strc.replaceAll("</p>", "");
                                            buffer.append("▼ 사용 전 반드시 알아야 할 사항");
                                            buffer.append("\n"+strc+"\n\n");
                                        }
                                    }
                                    //주의사항
                                    else if(tagName.equals("atpnQesitm")){
                                        xpp.next();
                                        String str = xpp.getText();
                                        if(str != null){
                                            String strc = str.replaceAll("<p>", "");
                                            strc = strc.replaceAll("</p>", "");
                                            buffer.append("▼ 사용상 주의사항");
                                            buffer.append("\n"+strc+"\n\n");
                                        }
                                    }
                                    //상호작용
                                    else if(tagName.equals("intrcQesitm")){
                                        xpp.next();
                                        String str = xpp.getText();
                                        if(str != null){
                                            String strc = str.replaceAll("<p>", "");
                                            strc = strc.replaceAll("</p>", "");
                                            buffer.append("▼ 사용하는 동안 주의해야할 사항");
                                            buffer.append("\n"+strc+"\n\n");
                                        }
                                    }
                                    //부작용
                                    else if(tagName.equals("seQesitm")){
                                        xpp.next();
                                        String str = xpp.getText();
                                        if(str != null){
                                            String strc = str.replaceAll("<p>", "");
                                            strc = strc.replaceAll("</p>", "");
                                            buffer.append("▼ 부작용");
                                            buffer.append("\n"+strc+"\n\n");
                                        }
                                    }
                                    //보관법
                                    else if(tagName.equals("depositMethodQesitm")){
                                        xpp.next();
                                        String str = xpp.getText();
                                        if(str != null){
                                            String strc = str.replaceAll("<p>", "");
                                            strc = strc.replaceAll("</p>", "");
                                            buffer.append("▼ 보관법");
                                            buffer.append("\n"+strc+"\n\n");
                                        }
                                    }
                                    break;

                                case XmlPullParser.TEXT:
                                    break;

                                case XmlPullParser.END_TAG:
                                    tagName = xpp.getName();
                                    if(tagName.equals("item")){
////////////////////////////////////확인용(성분 넣을 곳 확보하는 코드)/////////////////////////////////////////
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

                                    break;

                                case XmlPullParser.START_TAG:
                                    tagName2=xpp2.getName();
                                    //시작
                                    if(tagName2.equals("item")){
                                    }
                                    //전문의약품 or 일반의약품
                                    else if(tagName2.equals("ETC_OTC_NAME")){
                                        xpp2.next();
                                        String str = xpp2.getText();
                                        if(str != null){
                                            buffer.append("▼ 의약품 구분");
                                            buffer.append("\n"+str+"\n\n");
                                        }
                                    }
                                    //의약품 종류
                                    else if(tagName2.equals("CLASS_NAME")){
                                        xpp2.next();
                                        String str = xpp2.getText();
                                        if(str != null){
                                            buffer.append("▼ 의약품 분류");
                                            buffer.append("\n"+str+"\n\n");
                                        }
                                    }
                                    //성상
                                    else if(tagName2.equals("CHART")){
                                        xpp2.next();
                                        String str = xpp2.getText();
                                        if(str != null){
                                            buffer.append("▼ 색상 및 형태");
                                            buffer.append("\n"+str+"\n\n");
                                        }
                                    }
                                    //제형
                                    else if(tagName2.equals("FORM_CODE_NAME")){
                                        xpp2.next();
                                        String str = xpp2.getText();
                                        if(str != null){
                                            buffer.append("▼ 제형");
                                            buffer.append("\n"+str+"\n\n");
                                        }
                                    }
                                    //모양(식별)
                                    else if(tagName2.equals("DRUG_SHAPE")){
                                        xpp2.next();
                                        String str = xpp2.getText();
                                        if(str != null){
                                            buffer.append("▼ 모양");
                                            buffer.append("\n"+str+"\n\n");
                                        }
                                    }

                                    //세트 "COLOR_CLASS1" & "COLOR_CLASS2"(색깔)
                                    else if(tagName2.equals("COLOR_CLASS1")){
                                        buffer.append("▼ 색깔");
                                        xpp2.next();
                                        String str = xpp2.getText();
                                        if(str != null){
                                            buffer.append("\n"+"(앞)"+str+"\n");
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

                                        text.setText(buffer.toString());

                                    }
                                    break;
                            }
                            eventType2=xpp2.next();
                        }//while ..



                } catch (MalformedURLException e) { e.printStackTrace();
                } catch (IOException e) {e.printStackTrace();
                } catch (XmlPullParserException e) {e.printStackTrace();}
            }// run() ..
        }.start();


        return viewGroup;
    }
}



