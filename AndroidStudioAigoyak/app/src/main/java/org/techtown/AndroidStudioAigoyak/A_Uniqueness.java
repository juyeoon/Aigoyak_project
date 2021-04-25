package org.techtown.AndroidStudioAigoyak;

        import android.content.Context;
        import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Button;
        import android.widget.Toast;

public class A_Uniqueness extends AppCompatActivity {
    private static final String TAG = "A_uniqueness";
    Context context;

    Button button[] = new Button[19];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uniqueness);

        button[0] = (Button) findViewById(R.id.MAO);
        button[1] = (Button) findViewById(R.id.과민증);
        button[2] = (Button) findViewById(R.id.피부염);
        button[3] = (Button) findViewById(R.id.녹내장);
        button[4] = (Button) findViewById(R.id.당뇨);
        button[5] = (Button) findViewById(R.id.두드러기);
        button[6] = (Button) findViewById(R.id.배뇨);
        button[7] = (Button) findViewById(R.id.부정맥);
        button[8] = (Button) findViewById(R.id.소화성궤양);
        button[9] = (Button) findViewById(R.id.신장);
        button[10] = (Button) findViewById(R.id.심장);
        button[11] = (Button) findViewById(R.id.알레르기);
        button[12] = (Button) findViewById(R.id.유당분해효소결핍증);
        button[13] = (Button) findViewById(R.id.위장);
        button[14] = (Button) findViewById(R.id.임부);
        button[15] = (Button) findViewById(R.id.천식);
        button[16] = (Button) findViewById(R.id.혈액응고);
        button[17] = (Button) findViewById(R.id.피부감염증);
        button[18] = (Button) findViewById(R.id.혈압);


        for(int i=0; i<19;i++) {
            buttonAction(button[i], button[i].getText().toString());
        }

        //설정 완료
        ImageButton button_finish = (ImageButton) findViewById(R.id.button_finish);
        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "환영합니다!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(A_Uniqueness.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void saveNote(String name){//db에 저장
        String feature = name;

        String sql = "insert into " +NoteDatabase.TABLE_USER + "(FEATURE) values (" + "'"+ feature + "')";

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

    }

    private void deleteNote(String name){//db에서 삭제
        String feature = name;//임의로 지정

        String sql = "delete from "+NoteDatabase.TABLE_USER+ " where feature = '" + feature + "'";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }

    //버튼액션
    public void buttonAction(Button button, String text){

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (button.isSelected()) {
                    button.setSelected(false);
                    deleteNote(text);
                } else {
                    button.setSelected(true);
                    saveNote(text);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
