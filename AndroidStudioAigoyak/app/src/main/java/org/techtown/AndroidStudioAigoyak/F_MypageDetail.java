package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

public class F_MypageDetail extends AppCompatActivity {

    private static final String TAG = "UserAdapter";

    ArrayList<User> items = new ArrayList<User>();
    private int position;
    Context context;
    OnNoteItemClickListener listener;

    public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position = position;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_detail);

        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        //수정 버튼 누름
        ImageButton button_change = (ImageButton) findViewById(R.id.change_button);
        button_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(F_MypageDetail.this, F_MypageChange.class);
                startActivity(intent);
            }
        });

        TextView number = (TextView) findViewById(R.id.숫자);

        String sql = "select FEATURE from " + NoteDatabase.TABLE_USER;
        String text= "";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        Cursor cursor = database.rawQuery(sql);
        int recordCount = cursor.getCount();

        for(int i=0; i< recordCount; i++){
            cursor.moveToNext();
            if(i==0){
                text = cursor.getString(0);
                number.setText(text);
                text = "";
            }
            else if(i==1){ text = cursor.getString(0); }
            else{ text = text + "\n" + cursor.getString(0); }

        }

        TextView feature = (TextView) findViewById(R.id.feature);
        feature.setText(text);
    }
}