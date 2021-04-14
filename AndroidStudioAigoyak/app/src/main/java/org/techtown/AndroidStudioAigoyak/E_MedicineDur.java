package org.techtown.AndroidStudioAigoyak;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class E_MedicineDur extends Fragment {

    ViewGroup viewGroup;
    TextView textView;
    String text;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_medicine_dur, container, false);
        textView = viewGroup.findViewById(R.id.textView);

        Bundle bundle =getArguments();
        text = bundle.getString("text");//E_MedicineInfo -> E_MedicineInfoMain에서 json으로 text 들고옴
        textView.setText(text);
        return viewGroup;
    }
}