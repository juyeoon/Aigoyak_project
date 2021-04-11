package org.techtown.AndroidStudioAigoyak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class E_MedicineInfo  extends AppCompatActivity {
    private E_MedicineInfoMain fragment;

    private FragmentTransaction transaction;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_info);

        fragment = new E_MedicineInfoMain();
        getSupportFragmentManager().beginTransaction().replace(R.id.sub_layout, fragment).commitAllowingStateLoss();

        Intent intent =getIntent();
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

        ImageButton button1 = (ImageButton) findViewById(R.id.heart_button);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button1.isSelected()){
                    button1.setSelected(false);
                }
                else{
                    button1.setSelected(true);
                }
            }
        });





    }
/*
    public void Fragment(View view){

    }

 */
}