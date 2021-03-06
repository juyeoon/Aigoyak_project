package org.techtown.AndroidStudioAigoyak;

import androidx.appcompat.app.AppCompatActivity;
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
        import android.widget.ImageButton;

        import java.util.ArrayList;

public class F_Bookmark extends AppCompatActivity {
    private static final String TAG = "Bookmark";
    RecyclerView recyclerView;
    F_BookmarkAdapter adapter;
    Context context;

    //_id, name, clock가 담겨질 배열 생성
    ArrayList<Search> items = new ArrayList<Search>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark);

        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.back_button);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        initUI();
        loadNoteListData();
    }

    private void initUI(){
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new F_BookmarkAdapter(this);
        recyclerView.setAdapter(adapter);
    }


    //db연결해서 데이터를 리스트에 저장시키는거
    public int loadNoteListData(){
        String sql = "select _id, CODE, NAME, CORP from " + NoteDatabase.TABLE_BOOKMARK;
        int recordCount=-1;
        NoteDatabase database = NoteDatabase.getInstance(context);

        if (database != null){
            Cursor outCursor = database.rawQuery(sql);
            recordCount = outCursor.getCount();

            for(int i=0; i<recordCount; i++){
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String code = outCursor.getString(1);
                String name = outCursor.getString(2);
                String corp = outCursor.getString(3);
                items.add(new Search(_id, name, corp, code));
            }
            outCursor.close();

            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
        return recordCount;
    }

}

