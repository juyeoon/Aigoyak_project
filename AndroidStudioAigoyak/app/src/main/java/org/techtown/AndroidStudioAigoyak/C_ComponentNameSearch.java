package org.techtown.AndroidStudioAigoyak;

        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

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
        return fv;
    }

}







