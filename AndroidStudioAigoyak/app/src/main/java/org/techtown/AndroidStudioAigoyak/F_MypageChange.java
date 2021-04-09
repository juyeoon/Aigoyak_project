package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class F_MypageChange extends AppCompatActivity {
    private static final String TAG = "F_MypageChange";
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mypage_change);
        EditText age = (EditText) findViewById(R.id.age);


        String sql = "select FEATURE from " + NoteDatabase.TABLE_USER;//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        Cursor cursor = database.rawQuery(sql);
        int recordCount = cursor.getCount();
        String text[] = new String[recordCount];

        for(int i=0; i< recordCount; i++){
            cursor.moveToNext();
            text[i] = cursor.getString(0);

            System.out.println(text);
        }

        Button button1 = (Button) findViewById(R.id.임산부);
        String text1 = button1.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text1.equals(text[i])){
                button1.setSelected(true);
            }
        }
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button1.isSelected()){
                    button1.setSelected(false);
                    deleteNote(text1);
                }
                else{
                    button1.setSelected(true);
                    changeNote(text1);
                }
            }
        });

        Button button2 = (Button) findViewById(R.id.고혈압);
        String text2 = button2.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text2.equals(text[i])){
                button2.setSelected(true);
            }
        }
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button2.isSelected()){
                    button2.setSelected(false);
                    deleteNote(text2);
                }
                else{
                    button2.setSelected(true);
                    changeNote(text2);
                }
            }
        });
        Button button3 = (Button) findViewById(R.id.당뇨);
        String text3 = button3.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text3.equals(text[i])){
                button3.setSelected(true);
            }
        }
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button3.isSelected()){
                    button3.setSelected(false);
                    deleteNote(text3);
                }
                else{
                    button3.setSelected(true);
                    changeNote(text3);
                }
            }
        });

        //버튼 개수마다 추가될 예정--------------------------------------------------------------------------------------

        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.back_button);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        //확인 버튼 누름
        Button button_finish = (Button) findViewById(R.id.ok_button);
        button_finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!age.getText().toString().equals("")){
                    saveNote(age.getText().toString(), text[0]);
                    Intent intent = new Intent(F_MypageChange.this, F_MypageDetail.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"나이를 입력하세요!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //나이 db에 저장
    private void saveNote(String age, String text){
        String sql = "UPDATE "+NoteDatabase.TABLE_USER+ " SET FEATURE = '" + age +"' where feature = '"+text+"'";
        A_Age.age = age;
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }

    //특이사항 db저장
    private void changeNote(String name){
        String feature = name;

        String sql = "insert into " +NoteDatabase.TABLE_USER + "(FEATURE) values (" + "'"+ feature + "')";

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }

    //특이사항 db삭제
    private void deleteNote(String name){
        String feature = name;

        String sql = "delete from "+NoteDatabase.TABLE_USER+ " where feature = '" + feature + "'";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }
}