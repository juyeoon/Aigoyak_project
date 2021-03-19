package org.techtown.AndroidStudioAigoyak;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class C_ProductNameSearch extends Fragment {
    public C_ProductNameSearch() {

    }

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
                Intent intent = new Intent(getActivity(), D_SearchList.class);
                startActivity(intent);
            }
        });











        return fv;
    }

}




