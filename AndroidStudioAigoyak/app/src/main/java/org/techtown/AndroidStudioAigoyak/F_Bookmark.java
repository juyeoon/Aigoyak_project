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

        adapter = new F_BookmarkAdapter();
        recyclerView.setAdapter(adapter);
    }


    //이거는 db연결해서 데이터를 리스트에 저장시키는거
    public int loadNoteListData(){
        //String medicine_name = "아스피린";
        //String sql = "select _id, NAME, CORP from " + NoteDatabase.TABLE_MEDICINE; //bookmark테이블에 저장된 이름 불러오기

        String sql = "select _id, NAME, CORP from " + NoteDatabase.TABLE_BOOKMARK; //임의로 설정함. 나중에 바꾸기
        System.out.println(sql);
        int recordCount=-1;
        NoteDatabase database = NoteDatabase.getInstance(context);

        if (database != null){
            Cursor outCursor = database.rawQuery(sql);
            recordCount = outCursor.getCount();

            //_id, name, clock가 담겨질 배열 생성
            ArrayList<Search> items = new ArrayList<Search>();

            //하나하나 추가
            for(int i=0; i<recordCount; i++){
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String name = outCursor.getString(1);
                String corp = outCursor.getString(2);
                items.add(new Search(_id, name, corp,"0"));
            }
            outCursor.close();

            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
        return recordCount;
    }

}

