package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

public class A_Age extends AppCompatActivity {
    private static final String TAG = "A_Age";
    Context context;

    public static String age = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.age);

        ImageButton button = (ImageButton) findViewById(R.id.button_next);
        EditText age_text = (EditText) findViewById(R.id.age);

        //버튼 클릭 시 나이 저장 후 특이사항 설정 화면으로 넘어감.
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                age =  age_text.getText().toString();

                if(!age.equals("")) {
                    saveNote();//db 저장
                    Intent intent = new Intent(A_Age.this, A_Uniqueness.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"나이를 입력하세요!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //나이 db에 저장
    private void saveNote(){
        String sql = "UPDATE "+NoteDatabase.TABLE_USER+ " SET FEATURE = '" +age+"'";

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }

    //뒤로가기 막기
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
