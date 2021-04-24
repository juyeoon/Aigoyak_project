package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

//성분 검색할 때 나올 애들
public class D_SearchListComponentName extends AppCompatActivity {
    private static final String TAG = "D_SearchListComponentName";
    ArrayList<Search> items = new ArrayList<Search>();
    RecyclerView recyclerView;
    D_SearchAdapter adapter;
    String count_num;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);

        String search_word = (String)getIntent().getSerializableExtra("search");//C_ComponentNameSearch에서 검색어 들고옴

        initUI();
        count_num = initLoadDB(search_word);
        TextView count = (TextView)findViewById((R.id.count));
        count.setText(count_num);
        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.back_button);
        button_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

    }

    private void initUI(){
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new D_SearchAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private String initLoadDB(String search_word) {
        DataAdapter dataAdapter = new DataAdapter(getApplicationContext());
        dataAdapter.createDatabase();
        dataAdapter.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        items = dataAdapter.ingrSearch(search_word);//이거써야함.

        adapter.setItems(items);
        adapter.notifyDataSetChanged();

        // db 닫기
        dataAdapter.close();
        return "검색결과 " + adapter.getItemCount() + "건";
    }
}
