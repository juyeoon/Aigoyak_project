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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uniqueness);

        Button button1 = (Button) findViewById(R.id.MAO);
        Button button2 = (Button) findViewById(R.id.과민증);
        Button button3 = (Button) findViewById(R.id.피부염);
        Button button4 = (Button) findViewById(R.id.녹내장);
        Button button5 = (Button) findViewById(R.id.당뇨);
        Button button6 = (Button) findViewById(R.id.두드러기);
        Button button7 = (Button) findViewById(R.id.배뇨);
        Button button8 = (Button) findViewById(R.id.부정맥);
        Button button9 = (Button) findViewById(R.id.소화성궤양);
        Button button10 = (Button) findViewById(R.id.신장);
        Button button11 = (Button) findViewById(R.id.심장);
        Button button12 = (Button) findViewById(R.id.알레르기);
        Button button13 = (Button) findViewById(R.id.유당분해효소결핍증);
        Button button14 = (Button) findViewById(R.id.위장);
        Button button15 = (Button) findViewById(R.id.임부);
        Button button16 = (Button) findViewById(R.id.천식);
        Button button17 = (Button) findViewById(R.id.혈액응고);
        Button button18 = (Button) findViewById(R.id.피부감염증);
        Button button19 = (Button) findViewById(R.id.혈압);

        String text1 = button1.getText().toString();
        String text2 = button2.getText().toString();
        String text3 = button3.getText().toString();
        String text4 = button4.getText().toString();
        String text5 = button5.getText().toString();
        String text6 = button6.getText().toString();
        String text7 = button7.getText().toString();
        String text8 = button8.getText().toString();
        String text9 = button9.getText().toString();
        String text10 = button10.getText().toString();
        String text11 = button11.getText().toString();
        String text12 = button12.getText().toString();
        String text13 = button13.getText().toString();
        String text14 = button14.getText().toString();
        String text15 = button15.getText().toString();
        String text16 = button16.getText().toString();
        String text17 = button17.getText().toString();
        String text18 = button18.getText().toString();
        String text19 = button19.getText().toString();

        buttonAction(button1, text1);
        buttonAction(button2, text2);
        buttonAction(button3, text3);
        buttonAction(button4, text4);
        buttonAction(button5, text5);
        buttonAction(button6, text6);
        buttonAction(button7, text7);
        buttonAction(button8, text8);
        buttonAction(button9, text9);
        buttonAction(button10, text10);
        buttonAction(button11, text11);
        buttonAction(button12, text12);
        buttonAction(button13, text13);
        buttonAction(button14, text14);
        buttonAction(button15, text15);
        buttonAction(button16, text16);
        buttonAction(button17, text17);
        buttonAction(button18, text18);
        buttonAction(button19, text19);

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
