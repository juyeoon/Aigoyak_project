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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_medicine_dur, container, false);
        textView = viewGroup.findViewById(R.id.textView);
        Bundle bundle = getArguments();
        code = bundle.getString("code");//E_MedicineInfo -> E_MedicineInfoMain에서 code 들고옴
        //textView.setText(text);
        text ="";
        initLoadDB(code);//db에서 dur 정보 들고오기.

        return viewGroup;
    }
    private void initLoadDB(String code) {
        DataAdapter dataAdapter = new DataAdapter(getContext());
        dataAdapter.createDatabase();
        dataAdapter.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        dur5List = dataAdapter.getTableData2(code);

        dur2List = dataAdapter.getTableData3(code);

        dur3List = dataAdapter.getTableData1(code);//db에서 가져온 데이터 list에 저장.


        text = allText(dur3List, dur5List, dur2List);
        textView.setText(text);

        // db 닫기
        dataAdapter.close();
    }
//testList.size()

    public String allText(List<Dur3> dur3, List<Dur5> dur5, List<Dur2> dur2){
        String resultText = "▼ 병용 금기\n";

        for(int i=0; i<dur3.size(); i++){
            if(dur3.get(i).getCodeName().equals("(없음)")) {
                resultText = resultText + "(없음)\n\n";
            }
            else {
                resultText = resultText + "⊙" + dur3.get(i).getCodeName() +
                        "\n ☞ " + dur3.get(i).getContent() + "\n\n";
            }
        }
        if(dur5.get(0).getLimit_nm().equals("(없음)")) {
            resultText = resultText + "▼ 연령 금기\n(없음)\n\n";
        }
        else{
            resultText = resultText + "▼ 연령 금기\n" +
                    dur5.get(0).getLimit_nm() + dur5.get(0).getUnit() + " " + dur5.get(0).getStandard() + " " + dur5.get(0).getContent() + "\n\n";

        }
        resultText = resultText + "▼ 임부 금기\n" +
                dur2.get(0).getContent() + "\n\n";

        resultText = resultText + "▼ 노인 주의\n" +
                dur2.get(1).getContent() + "\n\n";

        if(dur2.get(2).getContent().equals("(없음)")) {
            resultText = resultText + "▼ 용량 주의\n(없음)\n\n";
        }
        else {
            resultText = resultText + "▼ 용량 주의\n" +
                    "1일 최대 용량: " + dur2.get(2).getContent() + "\n\n";
        }

        if(dur2.get(3).getContent().equals("(없음)")) {
            resultText = resultText + "▼ 투여 기간 주의\n(없음)\n\n";
        }
        else {
            resultText = resultText + "▼ 투여 기간 주의\n" +
                    "최대 투여 기간: " + dur2.get(3).getContent() + "일\n\n";
        }
        return resultText;
    }
}