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


public class C_MedicineSearch extends Fragment {

    ViewPager vp;
    LinearLayout linear;
    int i = 0;
    private C_ProductNameSearch fragment1;
    private C_ComponentNameSearch fragment2;
    private C_ShapeSearch fragment3;

    public C_MedicineSearch() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_medicine_search, container, false);
        vp = fv.findViewById(R.id.frame_layout);
        linear = fv.findViewById(R.id.linear);
        fragment1 = new C_ProductNameSearch();
        fragment2 = new C_ComponentNameSearch();
        fragment3 = new C_ShapeSearch();

        vp.setAdapter(new PagerAdapter(getChildFragmentManager()));
        vp.setCurrentItem(0);


        TextView button1, button2, button3;
        button1 = (TextView) fv.findViewById(R.id.제품명);
        button2 = (TextView) fv.findViewById(R.id.성분명);
        button3 = (TextView) fv.findViewById(R.id.약모양);
        MainActivity activity = null;


        button1.setOnClickListener(movePageListener);
        button1.setTag(0);
        button2.setOnClickListener(movePageListener);
        button2.setTag(1);
        button3.setOnClickListener(movePageListener);
        button3.setTag(2);

        button1.setSelected(true);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int i = 0;
                while (i < 3) {
                    if (position == i) {
                        linear.findViewWithTag(i).setSelected(true);
                    } else {
                        linear.findViewWithTag(i).setSelected(false);
                    }
                    i++;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
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
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
            getItem(0);
        }

        public Fragment getItem(int position) {
            if (position == 0) {
                return fragment1;
            } else if (position == 1) {
                return fragment2;
            } else {
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