package org.techtown.AndroidStudioAigoyak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class F_MypageDetail extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_detail);

        //뒤로가기 버튼 누름
        ImageButton button_change = (ImageButton) findViewById(R.id.change_button);
        button_change.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(F_MypageDetail.this, F_MypageChange.class);
                startActivity(intent);
            }
        });
    }
}