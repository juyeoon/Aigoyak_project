package org.techtown.AndroidStudioAigoyak;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


//성분 검색할 때 나올 애들
public class D_SearchListComponentName extends AppCompatActivity {
    private static final String TAG = "D_SearchListComponentName";
    ArrayList<Search> items = new ArrayList<Search>();
    RecyclerView recyclerView;
    D_SearchAdapter adapter;
    String code = new String();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance( );
    private DatabaseReference databaseReference;


    int i=0;
    TextView count;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);

        String search_word = (String)getIntent().getSerializableExtra("search");//C_ComponentNameSearch에서 검색어 들고옴

        initUI();

        String path = "ingredient";
        databaseReference = firebaseDatabase.getReference(path);
        addImageEventListener(databaseReference, search_word);//코드를 가져옴.

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
    private void addImageEventListener(DatabaseReference mPostReference, String search_word) {//조건에 맞는 코드 알아내기

        mPostReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Ingredient ingredient = new Ingredient();
                Ingredient select = new Ingredient();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    select = snapshot.getValue(Ingredient.class);

                    if((select.getIngr().contains(search_word))){
                        ingredient= select;
                        code = Integer.toString(ingredient.getCode());
                        items.add(new Search(i, ingredient.getName(), ingredient.getCorp(), code));
                        adapter.setItems(items);
                        adapter.notifyDataSetChanged();
                    };
                }
                count.setText("검색결과 " + adapter.getItemCount() + "건");

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }


}
