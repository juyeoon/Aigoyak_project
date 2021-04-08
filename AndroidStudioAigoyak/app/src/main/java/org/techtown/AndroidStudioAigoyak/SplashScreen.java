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
        linearLayout=(LinearLayout)findViewById(R.id.lin_lay); // Declare an imageView to show the animation.
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha); // Create the animation.
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                String sql = "select FEATURE from " + NoteDatabase.TABLE_USER;//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기

                String text;
                Log.d(TAG, "sql : " + sql);
                NoteDatabase database = NoteDatabase.getInstance(context);
                Cursor cursor = database.rawQuery(sql);
                int recordCount = cursor.getCount();
                cursor.moveToNext();
                text = cursor.getString(0);


                System.out.println("text = " + text);

                if(text.equals("0")) {
                    startActivity(new Intent(org.techtown.AndroidStudioAigoyak.SplashScreen.this, A_Age.class)); //실험을 위해 이거먼저 실시
                }
                else{
                    deleteNote();
                    startActivity(new Intent(org.techtown.AndroidStudioAigoyak.SplashScreen.this, MainActivity.class)); //실험을 위해 이거먼저 실시
                }
                //startActivity(new Intent(org.techtown.AndroidStudioAigoyak.SplashScreen.this, MainActivity.class));
                // HomeActivity.class is the activity to go after showing the splash screen.


                /*   //이거 나중에 닉네임 저장할 수 있는 디비 만들어지면 추가하기!
                if(name == 0){
                    startActivity(new Intent(org.techtown.AndroidStudioAigoyak.SplashScreen.this, A_Nickname.class));
                }
                else{
                    startActivity(new Intent(org.techtown.AndroidStudioAigoyak.SplashScreen.this, MainActivity.class));
                }


                 */
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
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

        String sql = "insert into " +NoteDatabase.TABLE_USER +//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기
                "(FEATURE) values (" +
                "'0')";



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







/*
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

public class SplashScreen extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    */
/** Called when the activity is first created. *//*

    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    //Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashScreen.this.finish();
                }

            }
        };
        splashTread.start();

    }

}*/
