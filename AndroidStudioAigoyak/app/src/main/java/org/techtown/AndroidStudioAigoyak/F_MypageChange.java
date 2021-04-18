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

        Button button1 = (Button) findViewById(R.id.MAO);
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

        Button button2 = (Button) findViewById(R.id.과민증);
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

        Button button3 = (Button) findViewById(R.id.피부염);
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

        Button button4 = (Button) findViewById(R.id.녹내장);
        String text4 = button4.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text4.equals(text[i])){
                button4.setSelected(true);
            }
        }
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button4.isSelected()){
                    button4.setSelected(false);
                    deleteNote(text4);
                }
                else{
                    button4.setSelected(true);
                    changeNote(text4);
                }
            }
        });
        Button button5 = (Button) findViewById(R.id.당뇨);
        String text5 = button5.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text5.equals(text[i])){
                button5.setSelected(true);
            }
        }
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button5.isSelected()){
                    button5.setSelected(false);
                    deleteNote(text5);
                }
                else{
                    button5.setSelected(true);
                    changeNote(text5);
                }
            }
        });

        Button button6 = (Button) findViewById(R.id.두드러기);
        String text6 = button6.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text6.equals(text[i])){
                button6.setSelected(true);
            }
        }
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button6.isSelected()){
                    button6.setSelected(false);
                    deleteNote(text6);
                }
                else{
                    button6.setSelected(true);
                    changeNote(text6);
                }
            }
        });

        Button button7 = (Button) findViewById(R.id.배뇨);
        String text7 = button7.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text7.equals(text[i])){
                button7.setSelected(true);
            }
        }
        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button7.isSelected()){
                    button7.setSelected(false);
                    deleteNote(text7);
                }
                else{
                    button7.setSelected(true);
                    changeNote(text7);
                }
            }
        });

        Button button8 = (Button) findViewById(R.id.부정맥);
        String text8 = button8.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text8.equals(text[i])){
                button8.setSelected(true);
            }
        }
        button8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button8.isSelected()){
                    button8.setSelected(false);
                    deleteNote(text8);
                }
                else{
                    button8.setSelected(true);
                    changeNote(text8);
                }
            }
        });

        Button button9 = (Button) findViewById(R.id.소화성궤양);
        String text9 = button9.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text9.equals(text[i])){
                button9.setSelected(true);
            }
        }
        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button9.isSelected()){
                    button9.setSelected(false);
                    deleteNote(text9);
                }
                else{
                    button9.setSelected(true);
                    changeNote(text9);
                }
            }
        });

        Button button10 = (Button) findViewById(R.id.신장);
        String text10 = button10.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text10.equals(text[i])){
                button10.setSelected(true);
            }
        }
        button10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button10.isSelected()){
                    button10.setSelected(false);
                    deleteNote(text10);
                }
                else{
                    button10.setSelected(true);
                    changeNote(text10);
                }
            }
        });

        Button button11 = (Button) findViewById(R.id.심장);
        String text11 = button11.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text11.equals(text[i])){
                button11.setSelected(true);
            }
        }
        button11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button11.isSelected()){
                    button11.setSelected(false);
                    deleteNote(text11);
                }
                else{
                    button11.setSelected(true);
                    changeNote(text11);
                }
            }
        });

        Button button12 = (Button) findViewById(R.id.알레르기);
        String text12 = button12.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text12.equals(text[i])){
                button12.setSelected(true);
            }
        }
        button12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button12.isSelected()){
                    button12.setSelected(false);
                    deleteNote(text12);
                }
                else{
                    button12.setSelected(true);
                    changeNote(text12);
                }
            }
        });

        Button button13 = (Button) findViewById(R.id.유당분해효소결핍증);
        String text13 = button13.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text13.equals(text[i])){
                button13.setSelected(true);
            }
        }
        button13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button13.isSelected()){
                    button13.setSelected(false);
                    deleteNote(text13);
                }
                else{
                    button13.setSelected(true);
                    changeNote(text13);
                }
            }
        });

        Button button14 = (Button) findViewById(R.id.위장);
        String text14 = button14.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text14.equals(text[i])){
                button14.setSelected(true);
            }
        }
        button14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button14.isSelected()){
                    button14.setSelected(false);
                    deleteNote(text14);
                }
                else{
                    button14.setSelected(true);
                    changeNote(text14);
                }
            }
        });

        Button button15 = (Button) findViewById(R.id.임부);
        String text15 = button15.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text15.equals(text[i])){
                button15.setSelected(true);
            }
        }
        button15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button15.isSelected()){
                    button15.setSelected(false);
                    deleteNote(text15);
                }
                else{
                    button15.setSelected(true);
                    changeNote(text15);
                }
            }
        });

        Button button16 = (Button) findViewById(R.id.천식);
        String text16 = button16.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text16.equals(text[i])){
                button16.setSelected(true);
            }
        }
        button16.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button16.isSelected()){
                    button16.setSelected(false);
                    deleteNote(text16);
                }
                else{
                    button16.setSelected(true);
                    changeNote(text16);
                }
            }
        });

        Button button17 = (Button) findViewById(R.id.혈액응고);
        String text17 = button17.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text17.equals(text[i])){
                button17.setSelected(true);
            }
        }
        button17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button17.isSelected()){
                    button17.setSelected(false);
                    deleteNote(text17);
                }
                else{
                    button17.setSelected(true);
                    changeNote(text17);
                }
            }
        });

        Button button18 = (Button) findViewById(R.id.피부감염증);
        String text18 = button18.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text18.equals(text[i])){
                button18.setSelected(true);
            }
        }
        button18.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button18.isSelected()){
                    button18.setSelected(false);
                    deleteNote(text18);
                }
                else{
                    button18.setSelected(true);
                    changeNote(text18);
                }
            }
        });

        Button button19 = (Button) findViewById(R.id.혈압);
        String text19 = button19.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text19.equals(text[i])){
                button19.setSelected(true);
            }
        }
        button19.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button19.isSelected()){
                    button19.setSelected(false);
                    deleteNote(text19);
                }
                else{
                    button19.setSelected(true);
                    changeNote(text19);
                }
            }
        });

        Button button20 = (Button) findViewById(R.id.추가등록);
        String text20 = button20.getText().toString();
        for(int i=0; i< recordCount; i++){
            if(text20.equals(text[i])){
                button20.setSelected(true);
            }
        }
        button20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button20.isSelected()){
                    String text20 = button20.getText().toString();
                    button20.setSelected(false);
                    deleteNote(text20);
                }
                else{
                    String text20 = button20.getText().toString();
                    button20.setSelected(true);
                    changeNote(text20);
                }
            }
        });


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

        ///키워드 추가
        Button add = (Button) findViewById(R.id.add);
        EditText keyword = (EditText) findViewById(R.id.keyword);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button20.getText().toString().equals("추가등록")){

                    button20.setText(keyword.getText().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"키워드를 삭제하고 시도해주세요.", Toast.LENGTH_LONG).show();
                }

            }
        });

        ///키워드 삭제
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!button20.getText().toString().equals("추가등록")) {
                    String text20 = button20.getText().toString();
                    deleteNote(text20);
                    button20.setSelected(false);
                    button20.setText("추가등록");
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