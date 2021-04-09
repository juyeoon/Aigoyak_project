package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashScreen";
    public static NoteDatabase noteDatabase = null;
    Animation anim;
    Context context;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 화면 가로 고정
        setContentView(R.layout.activity_splashscreen);
        openDatabase();//데이터베이스 열기.
        saveNote();
        linearLayout=(LinearLayout)findViewById(R.id.lin_lay);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {

                String sql = "select FEATURE from " + NoteDatabase.TABLE_USER;

                String text;
                Log.d(TAG, "sql : " + sql);
                NoteDatabase database = NoteDatabase.getInstance(context);
                Cursor cursor = database.rawQuery(sql);
                int recordCount = cursor.getCount();
                cursor.moveToNext();
                text = cursor.getString(0);


                System.out.println("text = " + text);

                if(text.equals("0")) {
                    startActivity(new Intent(org.techtown.AndroidStudioAigoyak.SplashScreen.this, A_Age.class));
                }
                else{
                    deleteNote();
                    startActivity(new Intent(org.techtown.AndroidStudioAigoyak.SplashScreen.this, MainActivity.class));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        linearLayout.startAnimation(anim);

    }
    public void openDatabase() {

        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }

        noteDatabase = NoteDatabase.getInstance(this);
        boolean isOpen = noteDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Note database is open.");
        } else {
            Log.d(TAG, "Note database is not open.");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }
    }
    private void saveNote(){

        String sql = "insert into " +NoteDatabase.TABLE_USER + "(FEATURE) values (" + "'0')";


        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }

    private void deleteNote(){
        String sql = "delete from "+NoteDatabase.TABLE_USER+ " where feature ='0'";

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }
}