package org.techtown.AndroidStudioAigoyak;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class A_Age extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.age);

        ImageButton button = (ImageButton) findViewById(R.id.button_next);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(A_Age.this, A_Uniqueness.class);
                startActivity(intent);
            }
        });

    }
}
