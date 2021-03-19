package org.techtown.AndroidStudioAigoyak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private B_Management fragment1;
    private C_MedicineSearch fragment2;
    private F_Mypage fragment3;


    public static NoteDatabase noteDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fragment1 = new B_Management();
        fragment2 = new C_MedicineSearch();
        fragment3 = new F_Mypage();



        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commitAllowingStateLoss();

        openDatabase();//데이터베이스 열기.

    //bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스너를 추가합니다.
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
        switch (menuItem.getItemId()) {
            //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
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


    public void openDatabase() {//ㅇㅣ건 어디다 넣냐냐

        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }

        noteDatabase = NoteDatabase.getInstance(this);
        boolean isOpen = noteDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Note database is open.");
        } else {
            Log.d(TAG, "Note database is not open.");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }
    }

}