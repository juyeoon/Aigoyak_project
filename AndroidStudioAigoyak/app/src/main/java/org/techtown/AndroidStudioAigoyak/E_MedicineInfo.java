package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class E_MedicineInfo  extends AppCompatActivity {
    private static final String TAG = "E_MedicineInfo";
    private E_MedicineInfoMain fragment = new E_MedicineInfoMain();

    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_info);
        Intent intent = getIntent();

        getSupportFragmentManager().beginTransaction().replace(R.id.sub_layout, fragment).commitAllowingStateLoss();
        String code = intent.getStringExtra("code");
        String name = intent.getStringExtra("name");
        String corp= intent.getStringExtra("corp");

        Bundle bundle = new Bundle();
        bundle.putString("code", code);// E_MedicineInfoMain으로 전송

        fragment.setArguments(bundle);




        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.back_button);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        //즐겨찾기 추가 버튼 누름
        ImageButton button_heart = (ImageButton) findViewById(R.id.heart_button);
        button_heart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button_heart.isSelected()){
                    button_heart.setSelected(false);
                    deleteNote(code);
                }
                else{
                    button_heart.setSelected(true);
                    saveNote(code, name, corp);
                }
            }
        });

        NoteDatabase database = NoteDatabase.getInstance(context);
        String sql = "select code from " + NoteDatabase.TABLE_BOOKMARK;
        Log.d(TAG, "sql : " + sql);

        int recordCount=-1;
        if (database != null){
            Cursor outCursor = database.rawQuery(sql);
            recordCount = outCursor.getCount();

            for(int i=0; i<recordCount; i++){
                outCursor.moveToNext();
                String ncode = outCursor.getString(0);
                System.out.println("F_BookmarkAdapter(ncode): "+ncode);

                if(code.equals(ncode)){//Bookmark에 저장되어 있으면 버튼 선택된 상태
                    button_heart.setSelected(true);
                }
            }
            outCursor.close();
        }

    }

    //db에 데이터 저장
    private void saveNote(String code, String name, String corp){

        String sql = "insert into " +NoteDatabase.TABLE_BOOKMARK +//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기
                "(CODE, NAME, CORP) values (" +
                "'"+ code + "', " +
                "'"+ name + "', " +
                "'"+ corp + "')";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

    }
    //db에서 삭제
    private void deleteNote(String code){
        String sql = "delete from " + NoteDatabase.TABLE_BOOKMARK + " where " + "code = '" + code +"'";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }
}