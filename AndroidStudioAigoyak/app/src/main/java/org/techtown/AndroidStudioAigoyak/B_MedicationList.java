package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
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

public class B_MedicationList extends RecyclerView.Adapter<B_MedicationList.ViewHolder>{//일단 완료
    private static final String TAG = "NoteAdapter";
    //아이템이 들어갈 배열
    ArrayList<Note> items = new ArrayList<Note>();


    OnNoteItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.fragment_medication_list, viewGroup, false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position){
        viewHolder.setItem(items.get(position));
        //Note item = items.get(position); //위랑 같은 코드
        //viewHolder.setItem(item);
        viewHolder.setLayout();
    }

    @Override
    public int getItemCount(){
        return items.size();
    }//RecyclerView의 총 개수

    public void addItem(Note item){
        items.add(item);
    }//외부에서 item을 추가시킬 함수

    public Note getItem(int position){
        return items.get(position);
    } //x

    public void setItems(ArrayList<Note> items){
        this.items = items;
    }

    public void setOnItemClickListener(OnNoteItemClickListener listener){//x
        this.listener = listener;
    }




    class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView name;
        TextView clock;
        TextView date;
        ImageView warning;
        ImageView trashcan;


        public ViewHolder(View itemView){
            super(itemView);
            String delete_name = "노란약";
            layout = itemView.findViewById(R.id.layout1);
            name = itemView.findViewById(R.id.name);
            clock = itemView.findViewById(R.id.clock);
            date = itemView.findViewById(R.id.date);
            warning = itemView.findViewById(R.id.warning);
            trashcan = itemView.findViewById(R.id.trashcan);

            //버튼 클릭 시 SQLite에서 데이터 삭제
            trashcan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteNote(); //아직 안 만듦 (추가해야함)

                    notifyDataSetChanged();
                   // adapter.notifyDataSetChanged();//새로고침?? 이거 제대로 작동 안 함.
                }

                Context context;

                private void deleteNote(){

                    String sql = "delete from " + NoteDatabase.TABLE_NOTE + " where " + "NAME = '" + delete_name +"'";//어떻게 할까 아직 난제
                    Log.d(TAG, "sql : " + sql);
                    NoteDatabase database = NoteDatabase.getInstance(context);
                    database.execSQL(sql);


                }


            });


        }

        public void setItem(Note item) {//내가 적은 텍스트를 불러와 저장하는 것
            warning.setVisibility(View.INVISIBLE); //warning 조건 걸어서 보이게 하기 (아직 안 함)
            name.setText(item.getName());
            clock.setText(item.getClock());

            int start_date = item.getDate();
            int end_date = item.getDate2();
            date.setText(start_date + " ~ " + end_date);

        }
        public void setLayout(){
            layout.setVisibility(View.VISIBLE);
        }
    }

}
