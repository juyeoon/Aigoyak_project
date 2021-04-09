package org.techtown.AndroidStudioAigoyak;

import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.app.TimePickerDialog;
        import android.content.Context;
import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.util.Log;
        import android.view.View;
        import android.widget.DatePicker;
        import android.widget.ImageButton;
        import android.widget.Button;
        import android.widget.TimePicker;
        import android.widget.Toast;
        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import androidx.core.app.NotificationCompat;
        import android.app.Notification;
        import android.app.NotificationChannel;
        import android.app.NotificationManager;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;

        import java.util.GregorianCalendar;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.Locale;

public class B_AddMedicine extends AppCompatActivity {
    private static final String TAG = "B_AddMedicine";
    private AlarmManager alarmManager;
    private GregorianCalendar mCalendar;
    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;


    Calendar cal = Calendar.getInstance();
    int y = cal.get(Calendar.YEAR);//년
    int m = cal.get(Calendar.MONTH);//월
    int d = cal.get(Calendar.DAY_OF_MONTH);//일
    int y2 = cal.get(Calendar.YEAR);//년2
    int m2 = cal.get(Calendar.MONTH);//월2
    int d2 = cal.get(Calendar.DAY_OF_MONTH);//일
    int h = cal.get(Calendar.HOUR_OF_DAY);//시
    int mi = cal.get(Calendar.MINUTE);//분

    int date = y*10000 + m*100 + d;
    int date2 = y2*10000 + m2*100 + d2;
    int a=0;

    Button date_start_button;
    Button date_end_button;
    Button clock_button;
    Button button_finish;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        mCalendar = new GregorianCalendar();

        Log.v("Alarm: ", mCalendar.getTime().toString());


        setContentView(R.layout.add_medicine);

        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        //복약 시작 날짜 정하는 버튼
        date_start_button = (Button) findViewById((R.id.date_start_button));
        date_start_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                a = 1;//start_button save
                showDate(a);


            }
        });

        //복약 종료 날짜 정하는 버튼
        date_end_button = (Button) findViewById((R.id.date_end_button));
        date_end_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                a = 2;//end_button save
                showDate(a);
            }
        });

        //복약 시간 정하는 버튼
        clock_button = (Button) findViewById((R.id.clock_button));
        clock_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTime();
            }
        });


        //확인 버튼 누름
        button_finish = (Button) findViewById(R.id.button_finish);
        button_finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(date-date2  > 0 || date == 0){//종료 날짜가 더 앞에 있으면 토스트메시지 띄우기
                    Toast.makeText(getApplicationContext(),"날짜를 다시 설정해주세요", Toast.LENGTH_LONG).show();
                }
                else if(y2-y != 0){//년도 달라지면 토스트메시지 띄우기
                    Toast.makeText(getApplicationContext(),"너무 많은 날의 입력은 권장하지 않습니다. 같은 년도로 선택해주세요", Toast.LENGTH_LONG).show();
                }
                else{

                        int ny =y; //now year
                        int nm =m; //now month
                        int nd =d; //now day
                        int ndate; //now date

                    if(m-m2 !=0) {//종료 달이 시작달과 다를 때
                        //시작하는 달의 시작 일자부터 31일까지 저장.

                        for (int j = d; j < 32; j++) {// 시작달의 d일부터 31일까지 저장.
                            ndate = ny * 10000 + nm * 100 + nd;
                            saveNote(ndate);
                            nd++;
                        }

                        nd = 1;//1일부터
                        nm = m+1;//시작하는 달의 다음달
                        for (int i = m+1; i < m2; i++) {//ex) 시작하는 달의 다음달의 1일부터 끝나는 달 전까지의 31일까지 저장.

                            for (int j = 1; j < 32; j++) {// 시작달의 1일부터 31일까지 저장.
                                ndate = ny * 10000 + nm * 100 + nd;
                                saveNote(ndate);
                                nd++;
                            }

                            nm++; //자신의 달보다 낮은 달의 날짜 31일까지 다 채움.

                        }

                        nm = m2;//끝나는 달
                        nd = 1;//1일부터
                        for (int k = 1; k <= d2; k++) {// 끝나는 달의 1일부터 d2일까지 저장.
                            ndate = ny * 10000 + nm * 100 + nd;
                            saveNote(ndate);
                            nd++;
                        }
                    }
                    else{//종료 달과 시작 달이 같을 때
                        for(int i=d; i<=d2; i++){//d일부터 d2일까지 저장.
                            ndate = ny*10000+nm*100+nd;
                            saveNote(ndate);
                            nd++;
                        }
                    }


                    Intent intent = new Intent(B_AddMedicine.this, MainActivity.class);
                    startActivity(intent);
                    setAlarm(); // 알람 적용하는 코드----------------------------------------------------------------------------------------
                    Toast.makeText(getApplicationContext(),"등록완료", Toast.LENGTH_LONG).show();

                }

            }
        });

        Button search_button = (Button) findViewById((R.id.search_button)); // 아직 안 함.



    }

    //알람 설정
    public void setAlarm(){
        int time = h*100+mi;
        Intent receiverIntent = new Intent(this, Alarm.class);
        //receiverIntent.putExtra("text","연습");------------------------------------------------------------------------------이거 살려야함.
        //receiverIntent.putExtra("id", time);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, receiverIntent, 0);

        String from = ""+y+"-"+m+"-"+d+" "+h+":"+mi+":00";
        System.out.println(from);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime =null;
        try{
            datetime = dateFormat.parse(from);
        }
        catch(ParseException e){
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);

        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
    }


    //데이터 저장
    private void saveNote(int ndate){
        String name = "노란약";//임의로 지정
        String clock = clock_button.getText().toString();

        String sql = "insert into " +NoteDatabase.TABLE_NOTE +//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기
                "(NAME, CLOCK, DATE, DATE2) values (" +
                "'"+ name + "', " +
                "'"+ clock + "', " +
                ndate + ", " +
                0 + ")";

        /* 실험으로 해봄 된다 ^^^^^^^^^^
        String sql2 = "insert into " +NoteDatabase.TABLE_BOOKMARK +//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기
                "(NAME, CORP) values (" +
                "'"+ name + "', " +
                "'"+ "안녕" + "')";
    */

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
        //database.execSQL(sql2);

    }

    void showDate(int a) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(a==1){//시작 날짜 저장
                    y = year;
                    m = month+1;
                    d = dayOfMonth;
                    date = y*10000 + m*100 + d; //ex) 20210302
                    date_start_button.setText(String.valueOf(y + "년 " + m + "월 " + d + "일"));

                }
                else if(a==2){//끝 날짜 저장
                    y2 = year;
                    m2 = month+1;
                    d2 = dayOfMonth;
                    date2 = y2*10000 + m2*100 + d2; //ex) 20210302
                    date_end_button.setText(String.valueOf(y2 + "년 " + m2 + "월 " + d2 + "일"));
                }

            }
        },cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }


    void showTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {//테마 하나 임의로 지정해서 사용중.
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                h = hourOfDay;
                mi = minute;
                clock_button.setText(String.valueOf(h + "시 " + mi + "분"));
            }

        }, h, mi, true);


        timePickerDialog.show();
    }
}
