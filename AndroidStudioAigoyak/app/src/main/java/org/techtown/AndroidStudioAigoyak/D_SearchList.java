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
    }

    private void initUI(){
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new D_SearchAdapter(this);
        adapter.addItem(new Search(0,"아스피린","회사명1"));
        adapter.addItem(new Search(1, "항생제", "회사명2"));
        recyclerView.setAdapter(adapter);
    }


}
