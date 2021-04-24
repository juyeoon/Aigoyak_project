package org.techtown.AndroidStudioAigoyak;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class C_ShapeSearch extends Fragment {
    public C_ShapeSearch() {

    }

    public static C_ShapeSearch newInstance(){
        return new C_ShapeSearch();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    String select[] = new String[4];
    Button form[] = new Button[4];
    Button shape[] = new Button[7];
    Button color[] = new Button[17];
    Button line[] = new Button[4];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_shape_search, container, false);


        Button ok_button = (Button)fv.findViewById(R.id.button_finish);

        form[0]= (Button)fv.findViewById(R.id.form1);//전체
        form[1]= (Button)fv.findViewById(R.id.form2);//정제
        form[2]= (Button)fv.findViewById(R.id.form3);//정제
        form[3]= (Button)fv.findViewById(R.id.form4);//정제

        shape[0] = (Button)fv.findViewById(R.id.shape1);//전체
        shape[1] = (Button)fv.findViewById(R.id.shape2);//전체
        shape[2] = (Button)fv.findViewById(R.id.shape3);//전체
        shape[3] = (Button)fv.findViewById(R.id.shape4);//전체
        shape[4] = (Button)fv.findViewById(R.id.shape5);//전체
        shape[5] = (Button)fv.findViewById(R.id.shape6);//전체
        shape[6] = (Button)fv.findViewById(R.id.shape7);//전체

        color[0] = (Button)fv.findViewById(R.id.color1);//전체
        color[1] = (Button)fv.findViewById(R.id.color2);//하양
        color[2] = (Button)fv.findViewById(R.id.color3);//노량
        color[3] = (Button)fv.findViewById(R.id.color4);//주황
        color[4] = (Button)fv.findViewById(R.id.color5);//분홍
        color[5] = (Button)fv.findViewById(R.id.color6);//빨강
        color[6] = (Button)fv.findViewById(R.id.color7);//갈색
        color[7] = (Button)fv.findViewById(R.id.color8);//연두
        color[8] = (Button)fv.findViewById(R.id.color9);//초록
        color[9] = (Button)fv.findViewById(R.id.color10);//청록
        color[10] = (Button)fv.findViewById(R.id.color11);//파랑
        color[11] = (Button)fv.findViewById(R.id.color12);//남색
        color[12] = (Button)fv.findViewById(R.id.color13);//자주
        color[13] = (Button)fv.findViewById(R.id.color14);//보라
        color[14] = (Button)fv.findViewById(R.id.color15);//회색
        color[15] = (Button)fv.findViewById(R.id.color16);//검정
        color[16] = (Button)fv.findViewById(R.id.color17);//투명

        line[0] = (Button)fv.findViewById(R.id.line1);//전체
        line[1] = (Button)fv.findViewById(R.id.line2);//전체
        line[2] = (Button)fv.findViewById(R.id.line3);//전체
        line[3] = (Button)fv.findViewById(R.id.line4);//전체

        form[0].setSelected(true);
        shape[0].setSelected(true);
        color[0].setSelected(true);
        line[0].setSelected(true);

        for(int i=0; i<4; i++){ select[i] = "전체"; }

        for(int i=0; i<4; i++){
            buttonActionForm(i);
        }
        for(int i=0; i<7; i++){
            buttonActionShape(i);
        }
        for(int i=0; i<17; i++){
            buttonActionColor(i);
        }
        for(int i=0; i<4; i++){
            buttonActionLine(i);
        }

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), D_SearchListShape.class);//-------------------이거 바꾸기
                //default가 전체
                intent.putExtra("form", select[0]);
                intent.putExtra("shape", select[1]);
                intent.putExtra("color", select[2]);
                intent.putExtra("line", select[3]);//검색어 D_SearchListShape.class로 보냄.-------------------이거 바꾸기
                startActivity(intent);
            }
        });

        return fv;
    }

    //버튼액션_제형
    public void buttonActionForm(int index){

        form[index].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!form[index].isSelected()) {
                    form[index].setSelected(true);
                    select[0] = form[index].getText().toString();
                    for(int i=0; i<4; i++){
                        if(i != index){
                            form[i].setSelected(false);
                        }
                    }
                }
            }
        });
    }

    //버튼액션_모양
    public void buttonActionShape(int index){

        shape[index].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!shape[index].isSelected()) {
                    shape[index].setSelected(true);
                    select[1] = shape[index].getText().toString();

                    for(int i=0; i<7; i++){
                        if(i != index){
                            shape[i].setSelected(false);
                        }
                    }
                }
            }
        });
    }

    //버튼액션_색깔
    public void buttonActionColor(int index){

        color[index].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!color[index].isSelected()) {
                    color[index].setSelected(true);
                    select[2] = color[index].getText().toString();

                    for(int i=0; i<17; i++){
                        if(i != index){
                            color[i].setSelected(false);
                        }
                    }
                }
            }
        });
    }

    //버튼액션_선
    public void buttonActionLine(int index){

        line[index].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!line[index].isSelected()) {
                    line[index].setSelected(true);
                    select[3] = line[index].getText().toString();

                    for(int i=0; i<4; i++){
                        if(i != index){
                            line[i].setSelected(false);
                        }
                    }
                }
            }
        });
    }


}






