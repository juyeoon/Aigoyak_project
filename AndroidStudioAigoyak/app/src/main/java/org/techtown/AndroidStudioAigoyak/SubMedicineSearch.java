package org.techtown.AndroidStudioAigoyak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubMedicineSearch extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private C_ProductNameSearch fragment1;
    private C_ComponentNameSearch fragment2;
    private C_ShapeSearch fragment3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_medicine_search);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fragment1 = new C_ProductNameSearch();
        fragment2 = new C_ComponentNameSearch();
        fragment3 = new C_ShapeSearch();

        getSupportFragmentManager().beginTransaction().replace(R.id.sub_layout, fragment1).commitAllowingStateLoss();




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
                switch (menuItem.getItemId()) {

                    case R.id.제품명: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.sub_layout, fragment1).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.성분명: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.sub_layout, fragment2).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.약모양: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.sub_layout, fragment3).commitAllowingStateLoss();
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });
    }
}