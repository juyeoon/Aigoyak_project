package org.techtown.AndroidStudioAigoyak;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class B_Management extends Fragment{
    private static final String TAG= "Fragment1";
    MainActivity activity;
    RecyclerView recyclerView;
    B_MedicationList adapter;
    CalendarView calendarView;
    Context context;
    OnTabItemSelectedListener listener;

    Calendar cal = Calendar.getInstance();
    int y = cal.get(Calendar.YEAR);
    int m = cal.get(Calendar.MONTH)+1;
    int d = cal.get(Calendar.DAY_OF_MONTH);
    int now_date =  y*10000 + m*100 + d;
    int select_date = now_date;




    int sec = cal.get(Calendar.SECOND);






    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_management, container, false);
        Button btn_plus = rootView.findViewById(R.id.plus_button);
        btn_plus.setOnClickListener(new View.OnClickListener() {//plus_button 누르면 복약 추가 페이지로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), B_AddMedicine.class);
                startActivity(intent);
            }
        });


        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    y = year;
                    m = month + 1;
                    d = dayOfMonth;
                    select_date = y * 10000 + m * 100 + d;
                    System.out.println(select_date + "," + now_date);
                    loadNoteListData();
                }
            });

        initUI(rootView);
        loadNoteListData(); //성공 ^^^^^^^^^^^^^
        /*
        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(B_MedicationList.ViewHolder holder, View view, int position) {
                Note item = adapter.getItem(position);
                System.out.println("왜 안돼");
                Log.d(TAG, "아이템 선택됨 : " + item.get_id());

                if (listener != null) {
                    listener.showFragment2(item);
                }
            }
        });

         */


        return rootView;

    }

    private void initUI(ViewGroup rootView){
        //recyclerView연결
        recyclerView = rootView.findViewById(R.id.recyclerView);

        //LinearLayoutManager을 이용하여 recyclerView설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //어댑터 연결
        adapter = new B_MedicationList(getContext());

        recyclerView.setAdapter(adapter); // 용도 알아보기

    }

    //이거는 db연결해서 데이터를 리스트에 저장시키는거
    public int loadNoteListData(){
        String sql = "select _id, NAME, CLOCK, DATE, DATE2 from " + NoteDatabase.TABLE_NOTE + " where DATE = " + select_date + " order by _id desc";
        System.out.println(sql);
        int recordCount=-1;
        NoteDatabase database = NoteDatabase.getInstance(context);

        if (database != null){
            Cursor outCursor = database.rawQuery(sql);

            recordCount = outCursor.getCount();

            //_id, name, clock가 담겨질 배열 생성
            ArrayList<Note> items = new ArrayList<Note>();

            //하나하나 추가
            for(int i=0; i<recordCount; i++){
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String name = outCursor.getString(1);
                String clock = outCursor.getString(2);
                int date = outCursor.getInt(3);
                int date2 = outCursor.getInt(4);

                items.add(new Note(_id, name, clock, date, date2));
            }
            outCursor.close();

            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
        return recordCount;
    }




}