package org.techtown.AndroidStudioAigoyak;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.techtown.AndroidStudioAigoyak.B_AddMedicine.alarmManager;
import static org.techtown.AndroidStudioAigoyak.B_AddMedicine.pendingIntent;

public class B_MedicationList extends RecyclerView.Adapter<B_MedicationList.ViewHolder>{//일단 완료
    private static final String TAG = "NoteAdapter";
    ArrayList<Note> items = new ArrayList<Note>();//아이템이 들어갈 배열
    private int position;
    Context context;
    OnNoteItemClickListener listener;
    String code;
    String name;
    String corp;
    private OnItemClickListener mListener = null;

    public B_MedicationList(Context context){
        this.context =context;
    }


    public interface OnItemClickListener{ void onItemClick(View v, int position);}
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position = position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.fragment_medication_list, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position){
        int i= 0;
        int color_white = ContextCompat.getColor(context.getApplicationContext(), R.color.white);
        int color_gray = ContextCompat.getColor(context.getApplicationContext(), R.color.gray);
        viewHolder.setItem(items.get(position));

        viewHolder.setLayout();

        // 리스트& db 데이터 삭제
        viewHolder.trashcan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int id = items.get(position).get_id();
                int alarmNumber = items.get(position).getAlarm();

                String sql = "delete from " + NoteDatabase.TABLE_NOTE + " where " + "_id = " + id;
                items.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                Log.d(TAG, "sql : " + sql);
                NoteDatabase database = NoteDatabase.getInstance(context);
                database.execSQL(sql);


                //알람 삭제
                Intent receiverIntent = new Intent(context, Alarm.class);
                alarmManager = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), alarmNumber, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(pendingIntent);
                pendingIntent.cancel();
                System.out.println("B_MedicationList-alarmNumber: " + alarmNumber);
                Toast.makeText(context,"삭제완료", Toast.LENGTH_LONG).show(); //안됨..
            }
        });



        //리스트 선택 시 의약품 상세페이지로 이동
        viewHolder.itemView.setClickable(true);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String position_clock = items.get(position).getClock();
                System.out.println(position_clock);
                code = items.get(position).getCode();
                name = items.get(position).getName();
                corp = items.get(position).getCorp();
                if(position !=RecyclerView.NO_POSITION){
                    Intent intent  = new Intent(context, E_MedicineInfo.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("code", code);//품목기준코드 E_MedicineInfo -> E_MedicineInfoDetail로 보냄.
                    intent.putExtra("name", name);//의약품명
                    intent.putExtra("corp", corp);//회사명
                    System.out.println("B_MedicationList: position: " + position);
                    System.out.println("B_MedicationList: code: " + code);
                    context.startActivity(intent);
                }
            }
        });









        int id = items.get(position).get_id();
        String sql = "select date2 from " + NoteDatabase.TABLE_NOTE + " where " + "_id = " + id;
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        Cursor cursor = database.rawQuery(sql);
        cursor.moveToNext();
        int state = cursor.getInt(0);


        if(state == 0){//선택이 안 된 상태
            viewHolder.completeButton.setSelected(false);
            viewHolder.completeButton.setText("미\n복\n용");
            viewHolder.completeButton.setTextColor(color_gray);
        }
        else if(state == 1){//선택된 상태
            viewHolder.completeButton.setSelected(true);
            viewHolder.completeButton.setTextColor(color_white);
            viewHolder.completeButton.setText("복\n용\n함");
        }

        viewHolder.completeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(viewHolder.completeButton.isSelected()){//선택된 상태에서 선택 안 된 상태로 바꿈
                    updateNote(0, id);
                    viewHolder.completeButton.setSelected(false);
                    viewHolder.completeButton.setText("미\n복\n용");
                    viewHolder.completeButton.setTextColor(color_gray);

                }
                else{//선택 안 된 상태에서 선택된 상태로 바꿈
                    updateNote(1, id);
                    viewHolder.completeButton.setSelected(true);
                    viewHolder.completeButton.setText("복\n용\n함");
                    viewHolder.completeButton.setTextColor(color_white);
                }
            }
        });
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




    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView name;
        TextView clock;
        ImageView warning;
        ImageView trashcan;
        Button completeButton;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            layout = itemView.findViewById(R.id.layout1);
            name = itemView.findViewById(R.id.name);
            clock = itemView.findViewById(R.id.clock);
            warning = itemView.findViewById(R.id.warning);
            trashcan = itemView.findViewById(R.id.trashcan);
            completeButton = itemView.findViewById(R.id.완료);


        }
        public void setItem(Note item) {
            warning.setVisibility(View.INVISIBLE); //warning 조건 걸어서 보이게 하기 (아직 안 함)----------------------------------------------------
            name.setText(item.getName());
            clock.setText(item.getClock());

        }
        public void setLayout(){
            layout.setVisibility(View.VISIBLE);
        }
    }

    //복약 완료 상태 변경
    private void updateNote(int i, int id){
        String sql = "UPDATE "+NoteDatabase.TABLE_NOTE+ " SET DATE2 = " + i + " where " + "_id = " + id;

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

    }

}
