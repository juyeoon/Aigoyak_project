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

public class E_MedicineIngr extends Fragment{
    ViewGroup viewGroup;
    TextView textView;
    String code;
    String text;
    public List<Ingredient> ingrList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_medicine_info_detail, container, false);
        textView = viewGroup.findViewById(R.id.textView);
        Bundle bundle = getArguments();
        code = bundle.getString("code");//D_SearchAdapter에서 품목기준코드 들고옴
        text = "";
        initLoadDB(code);//db에서 dur 정보 들고오기.

        return viewGroup;
    }
    private void initLoadDB(String code) {
        DataAdapter dataAdapter = new DataAdapter(getContext());
        dataAdapter.createDatabase();
        dataAdapter.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        ingrList = dataAdapter.getTableData4(code);

        text = allText(ingrList.get(0).getName(),ingrList.get(0).getIngr(),ingrList.get(0).getAdd(),ingrList.get(0).getAddWarn());
        textView.setText(text);

        // db 닫기
        dataAdapter.close();
    }

    public String allText(String name, String ingr, String ingre_add, String add_warning){
        String resultText="";
        /*// 나중에 바꾸기-----------------------------------------------------------------------
        resultText = "▼ 제품명\n" + name + "\n\n" +
                "▼ 주성분\n" + ingr + "\n\n" +
                "▼ 첨가물\n" + ingre_add + "\n\n" +
                "▼ 첨가물 주의\n" + add_warning + "\n\n";

         */
        resultText = "▼ 제품명\n" + name + "\n\n" +
                "▼ 주성분\n" + ingr + "\n\n" +
                "▼ 첨가물\n" + ingre_add + "\n\n";
        return resultText;
    }



}
