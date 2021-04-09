package org.techtown.AndroidStudioAigoyak;

        import android.content.Context;
        import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.util.Log;
        import android.view.View;
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


        ImageButton button_finish = (ImageButton) findViewById(R.id.button_finish);
        button_finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"환영합니다!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(A_Uniqueness.this, MainActivity.class);
                startActivity(intent);

            }
        });

        Button button1 = (Button) findViewById(R.id.임산부);
        String text1 = button1.getText().toString();
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button1.isSelected()){
                    button1.setSelected(false);
                    deleteNote(text1);
                }
                else{
                    button1.setSelected(true);
                    saveNote(text1);
                }
            }
        });

        Button button2 = (Button) findViewById(R.id.고혈압);
        String text2 = button2.getText().toString();

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button2.isSelected()){
                    button2.setSelected(false);
                    deleteNote(text2);
                }
                else{
                    button2.setSelected(true);
                    saveNote(text2);
                }
            }
        });
        Button button3 = (Button) findViewById(R.id.당뇨);
        String text3 = button3.getText().toString();

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button3.isSelected()){
                    button3.setSelected(false);
                    deleteNote(text3);
                }
                else{
                    button3.setSelected(true);
                    saveNote(text3);
                }
            }
        });

        //버튼 개수마다 추가될 예정=====================================================================================================================



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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}


