package org.techtown.AndroidStudioAigoyak;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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

public class C_ProductNameSearch extends Fragment {
    public C_ProductNameSearch() {

    }

    //API 추가
    XmlPullParser xpp;
    String key2 = "COqqRqdIM6Kkz9qfzXGH5geAKxrfy90RL6AhqU4%2BaUT19SMd4Oy0YM7lpTZP8%2BY%2FgegeDNplMu%2FA%2B8HdJfGhKQ%3D%3D";

    public static ArrayList<Search> items = new ArrayList<Search>();
    ArrayList<String> items_st = new ArrayList<String>();

    public static C_ProductNameSearch newInstance(){
        return new C_ProductNameSearch();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product_name_search, container, false);
        Button btn_search = rootView.findViewById(R.id.search_button);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), D_SearchList.class);
                startActivity(intent);
            }
        });

        return rootView;
        */
        View fv = inflater.inflate(R.layout.fragment_product_name_search, container, false);
        ImageView btn_search = (ImageView)fv.findViewById(R.id.search_button);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        //API 추가
                        int id = 0;
                        items.clear();
                        EditText edit = (EditText)getView().findViewById(R.id.search_box);
                        String search_word = edit.getText().toString();//EditText에 작성된 Text얻어오기
                        String location = URLEncoder.encode(search_word);//한글의 경우 인식이 안되기에 utf-8 방식으로 encoding..

                        //요청 URL
                        String queryUrl="http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"
                                +"?serviceKey="+ key2
                                +"&itemName=" + location;

                        //파싱 코드
                        try {
                            //URL객체생성
                            URL url= new URL(queryUrl);

                            //Stream 열기                                     //is는 바이트 스트림이라 문자열로 받기위해 isr이 필요하고 isr을 pullparser에게 줘야하는데
                            InputStream is= url.openStream(); //바이트스트림
                            //문자스트림으로 변환
                            InputStreamReader isr=new InputStreamReader(is);

                            //읽어들인 XML문서를 분석(parse)해주는 객체 생성    //pullparser를 만들려면 Factory가 필요해서 팩토리 만들고 pullparser를 만들었다. 결론, 그리고 pullparser에게 isr연결
                            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                            XmlPullParser xpp=factory.newPullParser();
                            xpp.setInput(isr);

                            //xpp를 이용해서 xml문서를 분석

                            //xpp.next();   //XmlPullParser는 시작부터 문서의 시작점에 있으므로 next해주면 START_DOCUMENT를 못만난다.
                            int eventType= xpp.getEventType();

                            String tagName;
                            StringBuffer buffer=null;

                            while(eventType!=XmlPullParser.END_DOCUMENT){



                                switch (eventType){
                                    case XmlPullParser.START_DOCUMENT:
                                        break;

                                    case XmlPullParser.START_TAG:
                                        tagName=xpp.getName();
                                        if(tagName.equals("item")){
                                            buffer=new StringBuffer();

                                        }else if(tagName.equals("entpName")){
                                            //buffer.append("회사명:");
                                            xpp.next();
                                            buffer.append(xpp.getText()+"\n");  //아래 두줄을 한줄로 줄일 수 있다.
//                                    String text = xpp.getText();
//                                    buffer.append(text+"\n");

                                        }else if(tagName.equals("itemName")){
                                            //buffer.append("제품명:");
                                            xpp.next();
                                            buffer.append(xpp.getText()+"\n");

                                        }
                                        break;

                                    case XmlPullParser.TEXT:
                                        break;

                                    case XmlPullParser.END_TAG:
                                        tagName = xpp.getName();
                                        if(tagName.equals("item")){
                                            String[] splitd = buffer.toString().split("\\n");
                                            items.add(new Search(id, splitd[1], splitd[0]));
                                            items_st.add(splitd[1]);
                                            items_st.add(splitd[0]);

                                            //확인코드
                                            System.out.println("splitd 내용");
                                            System.out.println(splitd[1]+", "+splitd[0]);
                                            //확인코드
                                            System.out.println("배열 내용 search");
                                            System.out.println(items.get(id)._id);
                                            System.out.println(items.get(id).name);
                                            System.out.println(items.get(id).corp);
                                            //확인코드
                                            System.out.println("배열 내용 string");
                                            System.out.println(items_st.get(id*2)+", "+items_st.get(id*2+1));
                                            //확인코드
                                            System.out.println(id);
                                            //확인코드
                                            System.out.println(items.size());

                                            id++;

                                        }
                                        break;
                                }

                                eventType=xpp.next();
                                //                        xpp.next();   //두줄을 한줄로 쓸 수 있다.
                                //                        eventType=xpp.getEventType();
                            }//while ..

                        }
                        catch (MalformedURLException e) { e.printStackTrace();} catch (IOException e) {e.printStackTrace();} catch (XmlPullParserException e) {e.printStackTrace();}
                    }
                }.start();
                //확인코드
                System.out.println("파싱 성공");

                Intent intent = new Intent(getActivity(), D_SearchList.class);
                startActivity(intent);
            }
        });
        return fv;
    }
}




