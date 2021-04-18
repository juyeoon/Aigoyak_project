package org.techtown.AndroidStudioAigoyak;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class E_MedicineDur extends Fragment {

    ViewGroup viewGroup;
    TextView textView;
    String code;
    String text;
    public List<Dur2> dur2List;
    public List<Dur3> dur3List;
    public List<Dur5> dur5List;
    public List<Ingredient> ingrList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_medicine_dur, container, false);
        textView = viewGroup.findViewById(R.id.textView);
        Bundle bundle =getArguments();
        code = bundle.getString("code");//E_MedicineInfo -> E_MedicineInfoMain에서 code 들고옴
        //textView.setText(text);
        text ="";
        initLoadDB(code);//db에서 dur 정보 들고오기.

        return viewGroup;
    }
    private void initLoadDB(String code) {
        String with, age, preg, old, volume, term;

        DataAdapter dataAdapter = new DataAdapter(getContext());
        dataAdapter.createDatabase();
        dataAdapter.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        dur5List = dataAdapter.getTableData2(code);

        dur2List = dataAdapter.getTableData3(code);

        dur3List = dataAdapter.getTableData1(code);//db에서 가져온 데이터 list에 저장.



        text = allText(dur3List.get(0).getContent(), dur5List.get(0).getContent(), dur2List.get(0).getContent(), dur2List.get(1).getContent(), dur2List.get(2).getContent(), dur2List.get(3).getContent());
        textView.setText(text);

        // db 닫기
        dataAdapter.close();
    }

    public String allText(String with, String age, String preg, String old, String volume, String term){
        String resultText="";

        resultText = "▼ 병용금기\n" + with + "\n\n" +
                "▼ 연령금기\n" + age + "\n\n" +
                "▼ 임부금기\n" + preg + "\n\n" +
                "▼ 노인금기\n" + old + "\n\n" +
                "▼ 용량금기\n" + volume + "\n\n" +
                "▼ 투여기간금기\n" + term + "\n\n";



        return resultText;
    }

}