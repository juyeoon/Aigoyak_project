package org.techtown.AndroidStudioAigoyak;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class A_Nickname extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nickname);

        ImageButton button = (ImageButton) findViewById(R.id.button_next);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(A_Nickname.this, A_Age.class);
                startActivity(intent);
            }
        });

    }
}
