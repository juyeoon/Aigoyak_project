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
    Button button[] = new Button[19];
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

        }
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
        Button add_button = (Button) findViewById(R.id.추가등록);

        for(int i=0; i< recordCount; i++){
            if(button[0].getText().toString().equals(text[i])){
                button[0].setSelected(true);
            }
            else if(button[1].getText().toString().equals(text[i])){
                button[1].setSelected(true);
            }
            else if(button[2].getText().toString().equals(text[i])){
                button[2].setSelected(true);
            }
            else if(button[3].getText().toString().equals(text[i])){
                button[3].setSelected(true);
            }
            else if(button[4].getText().toString().equals(text[i])){
                button[4].setSelected(true);
            }
            else if(button[5].getText().toString().equals(text[i])){
                button[5].setSelected(true);
            }
            else if(button[6].getText().toString().equals(text[i])){
                button[6].setSelected(true);
            }
            else if(button[7].getText().toString().equals(text[i])){
                button[7].setSelected(true);
            }
            else if(button[8].getText().toString().equals(text[i])){
                button[8].setSelected(true);
            }
            else if(button[9].getText().toString().equals(text[i])){
                button[9].setSelected(true);
            }
            else if(button[10].getText().toString().equals(text[i])){
                button[10].setSelected(true);
            }
            else if(button[11].getText().toString().equals(text[i])){
                button[11].setSelected(true);
            }
            else if(button[12].getText().toString().equals(text[i])){
                button[12].setSelected(true);
            }
            else if(button[13].getText().toString().equals(text[i])){
                button[13].setSelected(true);
            }
            else if(button[14].getText().toString().equals(text[i])){
                button[14].setSelected(true);
            }
            else if(button[15].getText().toString().equals(text[i])){
                button[15].setSelected(true);
            }
            else if(button[16].getText().toString().equals(text[i])){
                button[16].setSelected(true);
            }
            else if(button[17].getText().toString().equals(text[i])){
                button[17].setSelected(true);
            }
            else if(button[18].getText().toString().equals(text[i])){
                button[18].setSelected(true);
            }
            else{//추가 등록에 대한 text가 저장되어 있을 때
                if(i != 0) {
                    add_button.setText(text[i]);//그 텍스트 저장
                    add_button.setSelected(true);
                }
            }//그래서 만약 추가 키워드 추가하고 디비 저장 안 하면 나갔다 오면 저장 안 되어 있을거임

        }
        for(int i=0; i<19;i++) {
            buttonAction(button[i], button[i].getText().toString());
        }

        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(add_button.isSelected()){
                    String text20 = add_button.getText().toString();
                    add_button.setSelected(false);
                    deleteNote(text20);
                }
                else{
                    if(!add_button.getText().toString().equals("추가등록")) {
                        String text20 = add_button.getText().toString();
                        add_button.setSelected(true);
                        changeNote(text20);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"키워드를 추가해주세요!", Toast.LENGTH_LONG).show();

                    }
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
                if(add_button.getText().toString().equals("추가등록")){

                    add_button.setText(keyword.getText().toString());
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
                if(!add_button.getText().toString().equals("추가등록")) {
                    String text20 = add_button.getText().toString();
                    deleteNote(text20);
                    add_button.setSelected(false);
                    add_button.setText("추가등록");
                }
            }
        });
    }

    //나이 db에 저장
    private void saveNote(String age, String text){
        String sql = "UPDATE "+NoteDatabase.TABLE_USER+ " SET FEATURE = '" + age +"' where feature = '"+text+"'";
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
                    changeNote(text);
                }
            }
        });
    }
}