package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.io.InputStream;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class E_MedicineInfo  extends AppCompatActivity {
    private static final String TAG = "E_MedicineInfo";
    private E_MedicineInfoMain fragment = new E_MedicineInfoMain();

    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_info);
        ImageButton button_back = (ImageButton) findViewById(R.id.back_button);
        ImageButton button_heart = (ImageButton) findViewById(R.id.heart_button);
        ImageView imageview = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();

        getSupportFragmentManager().beginTransaction().replace(R.id.sub_layout, fragment).commitAllowingStateLoss();
        String code = intent.getStringExtra("code");
        String name = intent.getStringExtra("name");
        String corp= intent.getStringExtra("corp");


        //storage에서 사진 들고오기
        Uri imgUri;
        String image_name = "medisine/" + code + ".png"; //나중에 이 형식으로 사용

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance("gs://androidstudioaigoyak.appspot.com");
        StorageReference rootRef = firebaseStorage.getReference();

        StorageReference imgRef = rootRef.child(image_name);

        if(imgRef!=null){

            imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>(){
                @Override
                public void onSuccess(Uri uri){
                    Glide.with(E_MedicineInfo.this).load(uri).into(imageview);
                }
            });
        }



        //

        Bundle bundle = new Bundle();
        bundle.putString("code", code);// E_MedicineInfoMain으로 전송
        fragment.setArguments(bundle);

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

                if(code.equals(ncode)){//Bookmark에 저장되어 있으면 버튼 선택된 상태
                    button_heart.setSelected(true);
                }
            }
            outCursor.close();
        }

        //뒤로가기 버튼 누름
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        //즐겨찾기 추가 버튼 누름
        button_heart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button_heart.isSelected()){
                    button_heart.setSelected(false);
                    deleteNote(code);
                }
                else{
                    button_heart.setSelected(true);
                    if(!(searchCode(code).equals(code))) {
                        saveNote(code, name, corp);
                    }
                }
            }
        });

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

    //즐겨찾기에 저장이 되어있는지 확인
    private String searchCode(String code){
        String sql = "select code from " +NoteDatabase.TABLE_BOOKMARK + " where " + "code = '" + code + "'";
        Log.d(TAG, "sql: "+ sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

        String ncode="";
        if (database != null){
            Cursor outCursor = database.rawQuery(sql);
            outCursor.moveToNext();
            if( outCursor.getCount() == 1 ) {
                ncode = ncode + outCursor.getString(0);
            }
            outCursor.close();
        }
        return ncode;
    }


}