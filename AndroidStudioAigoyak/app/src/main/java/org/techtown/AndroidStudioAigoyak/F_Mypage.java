package org.techtown.AndroidStudioAigoyak;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Intent;

public class F_Mypage extends Fragment{
    MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }
    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        //회원정보 버튼 누름
        Button btn_info = rootView.findViewById(R.id.회원정보);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), F_MypageDetail.class);
                startActivity(intent);
            }
        });

        //즐겨찾기 버튼 누름
        Button btn_bookmark = rootView.findViewById(R.id.즐겨찾기);
        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), F_Bookmark.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}