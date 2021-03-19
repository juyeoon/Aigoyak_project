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
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class B_Management extends Fragment{
    private static final String TAG= "Fragment1";
    MainActivity activity;
    RecyclerView recyclerView;
    B_MedicationList adapter;

    Context context;
    OnTabItemSelectedListener listener;

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
        Button btn_change = rootView.findViewById(R.id.plus_button);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), B_AddMedicine.class);
                startActivity(intent);
            }
        });
        initUI(rootView);

        loadNoteListData();

        return rootView;

    }

    private void initUI(ViewGroup rootView){
        //recyclerView연결
        recyclerView = rootView.findViewById(R.id.recyclerView);

        //LinearLayoutManager을 이용하여 recyclerView설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //어댑터 연결
        adapter = new B_MedicationList();

        adapter.addItem(new Note(0, "아스피린", "7:00"));//임의로 데이터 넣음
        recyclerView.setAdapter(adapter); // 용도 알아보기

        //이건 나중에 할거임 무슨 역할 하는지 잘 모르겠음 ^^ 나중에 연구
        /*
        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(B_MedicationList.ViewHolder holder, View view, int position) {
                Note item = adapter.getItem(position);

                Log.d(TAG, "아이템 선택됨 : " + item.get_id());

                if (listener != null) {
                    listener.showFragment2(item);
                }
            }
        });
        */
    }

    //이거는 db연결해서 데이터를 리스트에 저장시키는거
    public int loadNoteListData(){
        String sql = "select _id, NAME, CLOCK from " + NoteDatabase.TABLE_NOTE + " order by _id desc";

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

                items.add(new Note(_id, name, clock));
            }
            outCursor.close();

            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
        return recordCount;
    }





}