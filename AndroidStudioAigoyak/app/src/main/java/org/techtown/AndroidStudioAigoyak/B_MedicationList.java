package org.techtown.AndroidStudioAigoyak;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class B_MedicationList extends RecyclerView.Adapter<B_MedicationList.ViewHolder>{
    ArrayList<Note> items = new ArrayList<Note>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.fragment_medication_list, viewGroup, false);

        ImageView trashcan = itemView.findViewById(R.id.trashcan);
        trashcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote(); //아직 안 만듦 (추가해야함)
            }
        });



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position){
        Note item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public void addItem(Note item){
        items.add(item);
    }

    public void setItems(ArrayList<Note> items){
        this.items = items;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout1;
        RelativeLayout layout2;
        TextView name;
        TextView clock;
        ImageView warning;
        ImageView trashcan;

        public ViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name);
            clock = itemView.findViewById(R.id.clock);
            warning = itemView.findViewById(R.id.warning);
            trashcan = itemView.findViewById(R.id.trashcan);

        }
        public void setItem(Note item) {
            //warning 조건 걸어서 보이게 하기 (아직 안 함)
            warning.setVisibility(View.VISIBLE);

            name.setText(item.getName());
            clock.setText(item.getClock());
        }
    }
}
