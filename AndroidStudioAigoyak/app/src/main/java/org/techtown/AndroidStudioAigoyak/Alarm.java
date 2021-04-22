package org.techtown.AndroidStudioAigoyak;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import androidx.core.app.NotificationCompat;


public class Alarm extends BroadcastReceiver{

    public Alarm(){    }

    NotificationManager manager;
    NotificationCompat.Builder builder;
    int id;

    @Override
    public void onReceive(Context context, Intent intent) {
        id = intent.getIntExtra("id",0);
        String CHANNEL_ID = intent.getStringExtra("channel_id");
        String CHANNEL_NAME = intent.getStringExtra("channel_id");
        builder = null;
        manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            );
            builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent intent2 = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,id,intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setSmallIcon(R.drawable.logo);
        //알림창 제목
        builder.setContentTitle("복약 알림");
        //알림창 내용
        builder.setContentText("약 먹을 시간이에요~");
        //알림창 아이콘
        builder.setSmallIcon(R.drawable.aigoyak_launcher);
        //알림창 터치시 자동 삭제
        builder.setAutoCancel(true);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        manager.notify(id,notification);//id를 계속 다르게 주면 됨 알림 여러개 가능.
    }
}





