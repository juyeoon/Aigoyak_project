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

    public C_ProductNameSearch() {}
    public static C_ProductNameSearch newInstance(){
        return new C_ProductNameSearch();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fv = inflater.inflate(R.layout.fragment_product_name_search, container, false);

        ImageView btn_search = (ImageView)fv.findViewById(R.id.search_button);//검색 버튼
        EditText edit = (EditText)fv.findViewById(R.id.search_box);//검색어


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_word = edit.getText().toString();//검색어 얻어오기

                Intent intent = new Intent(getActivity(), D_SearchList.class);
                intent.putExtra("search", search_word);//검색어 D_SearchList로 보냄.
                startActivity(intent);
            }
        });
        return fv;
    }
}




