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



public class B_Management extends Fragment{
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_management, container, false);

        Button btn_change = rootView.findViewById(R.id.plus_button);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), B_AddMedicine.class);
                startActivity(intent);
            }
        });

        return rootView;

    }



}