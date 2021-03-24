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

public class B_AddMedicine extends AppCompatActivity {
    private static final String TAG = "hi";
    int y=0, m=0, d=0, y2=0, m2=0, d2=0,h=12, mi=11;
    int date = 0, date2 = 0;
    Button date_start_button;
    Button date_end_button;
    Button clock_button;
    Button button_finish;
    Context context;
    int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicine);
        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(B_AddMedicine.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //확인 버튼 누름
        button_finish = (Button) findViewById(R.id.button_finish);
        button_finish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if(date-date2  > 0 || date == 0){//종료 날짜가 더 앞에 있으면 버튼 숨기기
                    System.out.println("날짜를 다시 설정해주세요");//이거 나중에 토스트메시지로 변경
                }
                else{
                    button_finish.setVisibility(View.VISIBLE);
                    saveNote();
                    Intent intent = new Intent(B_AddMedicine.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        Button search_button = (Button) findViewById((R.id.search_button)); // 아직 안 함.


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
    }




    private void saveNote(){
        String name = "노란약";//임의로 지정
        String clock = clock_button.getText().toString();

        String sql = "insert into " +NoteDatabase.TABLE_NOTE +//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기
                "(NAME, CLOCK, DATE, DATE2) values (" +
                "'"+ name + "', " +
                "'"+ clock + "', " +
                date + ", " +
                date2 + ")";


        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

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
        },2021, 2, 23);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();

    }


    void showTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {//테마 하나 임의로 지정해서 사용중.
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                h = hourOfDay;
                mi = minute;
                clock_button.setText(String.valueOf(h + ":" + mi));
            }

        }, 8, 12, true);

        timePickerDialog.setMessage("메시지");
        timePickerDialog.show();
    }
}
