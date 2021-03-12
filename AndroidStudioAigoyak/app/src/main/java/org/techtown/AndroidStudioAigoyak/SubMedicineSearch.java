package org.techtown.AndroidStudioAigoyak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//나중에 고치기 (나중에 지워야할수도)
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



//bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스너를 추가합니다.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
                switch (menuItem.getItemId()) {
                    //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
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