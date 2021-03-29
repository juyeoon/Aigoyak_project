package org.techtown.AndroidStudioAigoyak;

import android.os.Handler;
import android.util.Log;


public class AppConstants {


    public static String FOLDER_PHOTO;

    public static String DATABASE_NAME = "note.db";


    private static Handler handler = new Handler();
    private static final String TAG = "AppConstants";
    public static void println(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, data);
            }
        });
    }

}