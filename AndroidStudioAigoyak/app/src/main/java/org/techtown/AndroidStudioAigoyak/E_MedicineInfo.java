package org.techtown.AndroidStudioAigoyak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class E_MedicineInfo  extends AppCompatActivity {
    private E_MedicineInfoMain fragment = new E_MedicineInfoMain();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_info);
        Intent intent = getIntent();

        getSupportFragmentManager().beginTransaction().replace(R.id.sub_layout, fragment).commitAllowingStateLoss();
        String code = intent.getStringExtra("code");

        Bundle bundle = new Bundle();
        bundle.putString("code", code);// E_MedicineInfoMain으로 전송

        fragment.setArguments(bundle);




        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.back_button);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        //즐겨찾기 추가 버튼 누름
        ImageButton button_heart = (ImageButton) findViewById(R.id.heart_button);
        button_heart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button_heart.isSelected()){
                    button_heart.setSelected(false);
                }
                else{
                    button_heart.setSelected(true);
                }
            }
        });

    }
}