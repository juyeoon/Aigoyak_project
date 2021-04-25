package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//성분 검색할 때 나올 애들
public class D_SearchListShape extends AppCompatActivity {
    private static final String TAG = "D_SearchListShape";
    ArrayList<Search> items = new ArrayList<Search>();
    RecyclerView recyclerView;
    D_SearchAdapter adapter;
    String count_num;
    String code = new String();
    public List<Ingredient> ingrList;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance( );
    private DatabaseReference databaseReference;

    int i=0;
    TextView count;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);

        String form = (String)getIntent().getSerializableExtra("form");//C_ShapeSearch에서 들고옴
        String shape = (String)getIntent().getSerializableExtra("shape");
        String color = (String)getIntent().getSerializableExtra("color");
        String line = (String)getIntent().getSerializableExtra("line");


        initUI();
//
        String path = "discrimination";
            path = "discrimination";
            databaseReference = firebaseDatabase.getReference(path);
            addImageEventListener(databaseReference, form, shape, color, line);//코드를 가져옴.



        count = (TextView)findViewById((R.id.count));



        //뒤로가기 버튼 누름
        ImageButton button_back = (ImageButton) findViewById(R.id.back_button);
        button_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

    }

    private void initUI(){
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new D_SearchAdapter(this);
        recyclerView.setAdapter(adapter);
    }
/*
    private String initLoadDB() {

        return "검색결과 " + adapter.getItemCount() + "건";

    }
*/

    private void addImageEventListener(DatabaseReference mPostReference, String form, String shape, String color, String line) {//조건에 맞는 코드 알아내기

        mPostReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataAdapter dataAdapter = new DataAdapter(getApplicationContext());
                dataAdapter.createDatabase();
                dataAdapter.open();

                Image image = new Image();
                Image select = new Image();
                int number = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    select = snapshot.getValue(Image.class);

                    if((select.getForm().contains(form) || form.equals("전체"))
                    && (select.getShape().contains(shape)|| shape.equals("전체"))
                    && (select.getLine().contains(line) || line.equals("전체"))
                    && (select.getColor().contains(color) || color.equals("전체") )){

                        image= select;
                        code = Integer.toString(image.getCode());
                        ingrList = dataAdapter.getTableData4(code);
                        items.add(new Search(i, ingrList.get(0).getName(), ingrList.get(0).getCorp(), ingrList.get(0).getCode()));
                        adapter.setItems(items);
                        adapter.notifyDataSetChanged();
                    };
                }
                /*
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                image.add(snapshot.getValue(Image.class));
                }
                */
                //System.out.println(image.get(0).getCode());

//

                count.setText("검색결과 " + adapter.getItemCount() + "건");
             //
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
