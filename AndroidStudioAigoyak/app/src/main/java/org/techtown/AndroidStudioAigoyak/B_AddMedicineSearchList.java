package org.techtown.AndroidStudioAigoyak;

        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.content.Context;
        import androidx.recyclerview.widget.RecyclerView;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import android.widget.ImageButton;
        import android.widget.TextView;

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



public class B_AddMedicineSearchList extends AppCompatActivity {
    private static final String TAG = "B_AddMedicineSearchList";
    ArrayList<Search> items = new ArrayList<Search>();
    RecyclerView recyclerView;
    B_AddSearchAdapter adapter;
    Context context = this;
    int page = 0;
    //API 추가
    String apiKey = MainActivity.KEY;
    XmlPullParser xpp;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);
        String search_word = (String)getIntent().getSerializableExtra("search");//B_AddMedicine에서 검색어 들고옴

        initUI();

        TextView count = (TextView)findViewById((R.id.count));


        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.back_button);
        button_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        new Thread(){
            @Override
            public void run() {
                //API 추가
                int id = 0;
                items.clear();

                String location = URLEncoder.encode(search_word);//한글의 경우 인식이 안되기에 utf-8 방식으로 encoding..

                //요청 URL
                String queryUrl;
                for (int i = 0; i<37; i++) {
                    //파싱 코드
                    page++;
                    queryUrl = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"
                            + "?serviceKey=" + apiKey
                            + "&itemName=" + location
                            + "&numOfRows=" + 100
                            + "&pageNo=" + page;

                    try {

                        //URL객체생성
                        URL url = new URL(queryUrl);

                        //Stream 열기                                     //is는 바이트 스트림이라 문자열로 받기위해 isr이 필요하고 isr을 pullparser에게 줘야하는데
                        InputStream is = url.openStream(); //바이트스트림
                        //문자스트림으로 변환
                        InputStreamReader isr = new InputStreamReader(is);

                        //읽어들인 XML문서를 분석(parse)해주는 객체 생성    //pullparser를 만들려면 Factory가 필요해서 팩토리 만들고 pullparser를 만들었다. 결론, 그리고 pullparser에게 isr연결
                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        XmlPullParser xpp = factory.newPullParser();
                        xpp.setInput(isr);

                        //xpp를 이용해서 xml문서를 분석

                        //xpp.next();   //XmlPullParser는 시작부터 문서의 시작점에 있으므로 next해주면 START_DOCUMENT를 못만난다.
                        int eventType = xpp.getEventType();

                        String tagName;
                        StringBuffer buffer = null;

                        while (eventType != XmlPullParser.END_DOCUMENT) {


                            switch (eventType) {
                                case XmlPullParser.START_DOCUMENT:
                                    break;

                                case XmlPullParser.START_TAG:
                                    tagName = xpp.getName();
                                    if (tagName.equals("item")) {
                                        buffer = new StringBuffer();

                                    } else if (tagName.equals("entpName")) {//회사명
                                        xpp.next();
                                        buffer.append(xpp.getText() + "\n");


                                    } else if (tagName.equals("itemName")) {//제품명
                                        xpp.next();
                                        buffer.append(xpp.getText() + "\n");
                                    } else if (tagName.equals("itemSeq")) {//품목기준코드
                                        xpp.next();
                                        buffer.append(xpp.getText() + "\n");
                                    }
                                    break;

                                case XmlPullParser.TEXT:
                                    break;

                                case XmlPullParser.END_TAG:
                                    tagName = xpp.getName();
                                    if (tagName.equals("item")) {
                                        String[] splitd = buffer.toString().split("\\n");
                                        items.add(new Search(id, splitd[1], splitd[0], splitd[2]));
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                adapter.setItems(items);
                                                adapter.notifyDataSetChanged();
                                                count.setText("검색결과 " + adapter.getItemCount() + "건");
                                            }
                                        });
                                        id++;//idex

                                    }
                                    break;
                            }
                            eventType = xpp.next();
                        }//while
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


    }

    private void initUI(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new B_AddSearchAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
