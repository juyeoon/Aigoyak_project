package org.techtown.AndroidStudioAigoyak;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;
        import android.widget.ImageView;

        import androidx.fragment.app.Fragment;

public class  C_ComponentNameSearch extends Fragment {
    public  C_ComponentNameSearch() {

    }

    public static  C_ComponentNameSearch newInstance(){
        return new  C_ComponentNameSearch();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_component_name_search, container, false);

        ImageView btn_search = (ImageView)fv.findViewById(R.id.search_button);//검색 버튼
        EditText edit = (EditText)fv.findViewById(R.id.search_box);//검색어

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_word = edit.getText().toString();//검색어 얻어오기

                Intent intent = new Intent(getActivity(), D_SearchListComponentName.class);
                intent.putExtra("search", search_word);//검색어 D_SearchListComponentName로 보냄.
                startActivity(intent);
            }
        });



        return fv;
    }

}







