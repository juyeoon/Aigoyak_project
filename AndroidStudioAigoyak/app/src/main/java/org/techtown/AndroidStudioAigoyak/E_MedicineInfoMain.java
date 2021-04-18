package org.techtown.AndroidStudioAigoyak;

import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class E_MedicineInfoMain extends Fragment {
    ViewPager vp;
    LinearLayout linear;
    private E_MedicineInfoDetail fragment1;
    private E_MedicineIngr fragment2;// 만드는중
    private E_MedicineDur fragment3;


    public E_MedicineInfoMain() {    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState);    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_medicine_info_main, container, false);
        vp = fv.findViewById(R.id.vp);
        linear = fv.findViewById(R.id.button);
        fragment1= new E_MedicineInfoDetail();
        fragment2 = new E_MedicineIngr();
        fragment3 = new E_MedicineDur();

        vp.setAdapter(new PagerAdapter(getChildFragmentManager()));
        vp.setCurrentItem(0);

        Bundle bundle = getArguments();
        String code = bundle.getString("code");
        bundle.putString("code",code);
        fragment1.setArguments(bundle);// E_MedicineInfoDetail로 품번 전달
        fragment2.setArguments(bundle);// E_MedicineIngr로 품번 전달 예정
        fragment3.setArguments(bundle);// E_MedicineDur로 품번 전달
        TextView button1, button2, button3;

        button1 = (TextView) fv.findViewById(R.id.상세정보);
        button2 = (TextView) fv.findViewById(R.id.성분);
        button3 = (TextView) fv.findViewById(R.id.DUR);

        button1.setOnClickListener(movePageListener);
        button1.setTag(0);
        button2.setOnClickListener(movePageListener);
        button2.setTag(1);
        button3.setOnClickListener(movePageListener);
        button3.setTag(2);

        button1.setSelected(true);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                int i = 0;
                while (i < 3) {
                    if (position == i) {
                        linear.findViewWithTag(i).setSelected(true);
                    }
                    else {
                        linear.findViewWithTag(i).setSelected(false);
                    }
                    i++;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        return fv;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onLowMemory() { super.onLowMemory();}

    @Override
    public void onDestroy() { super.onDestroy();}

    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
            getItem(0);
        }

        public Fragment getItem(int position) {
            if (position == 0) {
                return fragment1;
            }
            else if(position == 1){
                return fragment2;
            }
            else{
                return fragment3;
            }
        }
        public int getCount() {
            return 3;
        }
    }

    View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            int i = 0;
            while (i < 3) {
                if (tag == i) {
                    linear.findViewWithTag(i).setSelected(true);
                } else {
                    linear.findViewWithTag(i).setSelected(false);
                }
                i++;
            }
            vp.setCurrentItem(tag);
        }
    };


}