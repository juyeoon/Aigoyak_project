package org.techtown.AndroidStudioAigoyak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    public static String KEY = "COqqRqdIM6Kkz9qfzXGH5geAKxrfy90RL6AhqU4%2BaUT19SMd4Oy0YM7lpTZP8%2BY%2FgegeDNplMu%2FA%2B8HdJfGhKQ%3D%3D";//api key 추가
    private static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private B_Management fragment1;
    private C_MedicineSearch fragment2;
    private F_Mypage fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fragment1 = new B_Management();
        fragment2 = new C_MedicineSearch();
        fragment3 = new F_Mypage();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commitAllowingStateLoss();

    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
        switch (menuItem.getItemId()) {

            case R.id.복약관리: {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_layout, fragment1).commitAllowingStateLoss();
                return true;
            }
            case R.id.의약품검색: {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_layout, fragment2).commitAllowingStateLoss();
                return true;
            }

            case R.id.마이페이지: {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_layout, fragment3).commitAllowingStateLoss();
                return true;
            }
            default:
                return false;
        }
        }
       });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        }
}