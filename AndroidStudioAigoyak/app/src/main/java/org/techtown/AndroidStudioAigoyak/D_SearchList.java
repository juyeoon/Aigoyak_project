package org.techtown.AndroidStudioAigoyak;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.ImageButton;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class D_SearchList extends AppCompatActivity {
    private static final String TAG = "SearchList";
    RecyclerView recyclerView;
    D_SearchAdapter adapter;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);

        //뒤로가기 버튼 누름
        ImageButton button_change = (ImageButton) findViewById(R.id.back_button);
        button_change.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        initUI();// 맞는진 모름
        insertDatatoList();

        /* 실행 안됨(오류)
        if( !C_ProductNameSearch.items.isEmpty() ) {
            //확인 코드
            System.out.println("배열 내용 search");
            System.out.println(C_ProductNameSearch.items.get(0)._id);
            System.out.println(C_ProductNameSearch.items.get(0).name);
            System.out.println(C_ProductNameSearch.items.get(0).corp);
        }
        else{
            System.out.println("배열 비어있음");
        }
        */


    }

    private void initUI(){
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new D_SearchAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    public void insertDatatoList(){
        adapter.setItems(C_ProductNameSearch.items);

        adapter.notifyDataSetChanged();
    }

}
