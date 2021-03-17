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
        recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new B_MedicationList();

        adapter.addItem(new Note(0, "아스피린", "7:00", "", ""));

        //이건 나중에 할거임
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
    }

    public int loadNoteListData(){
        AppConstants.println("데이터를 불러오겠습니다");
        String sql = "select _id, name, clock, location_x, location_y from" +NoteDatabase.TABLE_NOTE + "order by CREATE_DATE desc";

        int recordCount = -1;
        NoteDatabase database = NoteDatabase.getInstance(context);
        if (database != null){
            Cursor outCursor = database.rawQuery(sql);

            recordCount = outCursor.getCount();

            ArrayList<Note> items = new ArrayList<Note>();

            for(int i=0; i<recordCount; i++){
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String name = outCursor.getString(1);
                String clock = outCursor.getString(2);
                String locationX = outCursor.getString(3);
                String locationY = outCursor.getString(4);

                items.add(new Note(_id, name, clock, locationX, locationY));
            }
            outCursor.close();

            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
        return recordCount;
    }


}