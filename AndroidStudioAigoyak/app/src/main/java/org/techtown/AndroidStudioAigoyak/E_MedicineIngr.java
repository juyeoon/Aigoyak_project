package org.techtown.AndroidStudioAigoyak;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class E_MedicineIngr extends Fragment{
    ViewGroup viewGroup;
    TextView textView;
    String code;
    String text;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance( );
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_medicine_info_detail, container, false);
        textView = viewGroup.findViewById(R.id.textView);
        Bundle bundle = getArguments();
        code = bundle.getString("code");//D_SearchAdapter에서 품목기준코드 들고옴
        text = "";
        String path = "ingredient";
        databaseReference = firebaseDatabase.getReference(path);
        addImageEventListener(databaseReference, code);//코드를 가져옴.

        return viewGroup;
    }

    private void addImageEventListener(DatabaseReference mPostReference, String code) {//조건에 맞는 코드 알아내기

        mPostReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataAdapter dataAdapter = new DataAdapter(getContext());
                dataAdapter.createDatabase();
                dataAdapter.open();

                Ingredient ingredient = new Ingredient();
                Ingredient select = new Ingredient();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    select = snapshot.getValue(Ingredient.class);
                    String select_code = Integer.toString(select.getCode());
                    if((select_code.equals(code))){
                        ingredient= select;

                        System.out.println("getCode값: "+ ingredient.getCode());
                        System.out.println("getName값: "+ ingredient.getName());
                        System.out.println("getCorp값: "+ ingredient.getCorp());
                        System.out.println("getIngr값: "+ ingredient.getIngr());
                        System.out.println("getAdd값: "+ ingredient.getAdd());
                        text = allText(ingredient.getName(),ingredient.getIngr(),ingredient.getAdd());
                        textView.setText(text);
                        break;
                    };
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
    public String allText(String name, String ingr, String ingr_add){
        String resultText="";

        if(name.equals("")){
            name = "(없음)";
        }
        else{
            resultText = resultText + "▼ 제품명\n" + name + "\n\n";
        }
        if(ingr.equals("")){
            ingr = "(없음)";
        }
        else{
            resultText = resultText + "▼ 주성분\n" + ingr + "\n\n";
        }
        if(ingr_add.equals("")){
            ingr_add ="(없음)";
        }

        else{
            resultText = resultText + "▼ 첨가제\n" + ingr_add + "\n\n";
        }

        return resultText;
    }
}
