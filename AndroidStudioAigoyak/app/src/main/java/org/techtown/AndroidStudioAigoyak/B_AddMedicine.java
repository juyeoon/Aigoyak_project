package org.techtown.AndroidStudioAigoyak;

        import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.Button;

public class B_AddMedicine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicine);

        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(B_AddMedicine.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //확인 버튼 누름
        Button button_finish = (Button) findViewById(R.id.button_finish);
        button_finish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(B_AddMedicine.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
