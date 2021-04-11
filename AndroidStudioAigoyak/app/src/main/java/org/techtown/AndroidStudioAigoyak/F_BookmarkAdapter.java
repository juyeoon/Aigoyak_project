package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

public class F_BookmarkAdapter extends RecyclerView.Adapter<F_BookmarkAdapter.ViewHolder> {//일단 완료
    private static final String TAG = "BookmarkAdapter";
    private Context context;
    ArrayList<Search> items = new ArrayList<Search>();
    OnNoteItemClickListener listener;
    String code;
    String name;
    String corp;

    public F_BookmarkAdapter(Context context){ this.context = context; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.fragment_search_list, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position){
        viewHolder.setItem(items.get(position));
        viewHolder.setLayout();
        code = items.get(position).getCode();

        NoteDatabase database = NoteDatabase.getInstance(context);
        String sql = "select code from " + NoteDatabase.TABLE_BOOKMARK;
        Log.d(TAG, "sql : " + sql);

        int recordCount=-1;
        if (database != null){
            Cursor outCursor = database.rawQuery(sql);
            recordCount = outCursor.getCount();

            for(int i=0; i<recordCount; i++){
                outCursor.moveToNext();
                String ncode = outCursor.getString(0);
                System.out.println("F_BookmarkAdapter(ncode): "+ncode);

                if(code.equals(ncode)){//Bookmark에 저장되어 있으면 버튼 선택된 상태
                    viewHolder.heart.setSelected(true);
                }
            }
            outCursor.close();
        }


        //리스트 누르면 상세페이지로 이동
        viewHolder.itemView.setClickable(true);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(position != RecyclerView.NO_POSITION){
                    code = items.get(position).getCode();
                    name = items.get(position).getCode();
                    corp = items.get(position).getCode();

                    Intent intent =new Intent(context,E_MedicineInfo.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("code", code);//품목기준코드 E_MedicineInfo -> E_MedicineInfoDetail로 보냄.
                    intent.putExtra("name", name);//의약품명
                    intent.putExtra("corp", corp);//회사명
                    System.out.println("position: " + position);
                    System.out.println("code: " + code);

                    context.startActivity(intent);
                }
            }
        });

        viewHolder.heart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if( viewHolder.heart.isSelected()){
                    viewHolder.heart.setSelected(false);
                    deleteNote(items.get(position).getCode());
                }
                else{
                    viewHolder.heart.setSelected(true);
                    saveNote(items.get(position).getCode(), items.get(position).getName(),items.get(position).getCorp());
                }
            }
        });



    }

    @Override
    public int getItemCount(){
        return items.size();
    }//RecyclerView의 총 개수
    public void addItem(Search item){
        items.add(item);
    }//외부에서 item을 추가시킬 함수
    public Search getItem(int position){
        return items.get(position);
    } //x
    public void setItems(ArrayList<Search> items){
        this.items = items;
    }
    public interface OnItemClickListener{ void onItemClick(View v, int position);}
    public void setOnItemClickListener(OnNoteItemClickListener listener){this.listener = listener;}

    class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView name;
        TextView corp;
        ImageView heart;

        public ViewHolder(View itemView){
            super(itemView);
            layout = itemView.findViewById(R.id.layout1);
            name = itemView.findViewById(R.id.name);
            corp = itemView.findViewById(R.id.corp);
            heart = itemView.findViewById(R.id.heart_button);



        }

        public void setItem(Search item) {//내가 적은 텍스트를 불러와 저장하는 것
            //heart.setVisibility(View.VISIBLE); //heart 조건 걸어서 보이게 하기
            name.setText(item.getName());
            corp.setText(item.getCorp());
        }
        public void setLayout(){
            layout.setVisibility(View.VISIBLE);
        }
    }

    //db에 데이터 저장
    private void saveNote(String code, String name, String corp){

        String sql = "insert into " +NoteDatabase.TABLE_BOOKMARK +//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기
                "(CODE, NAME, CORP) values (" +
                "'"+ code + "', " +
                "'"+ name + "', " +
                "'"+ corp + "')";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

    }
    //db에서 삭제
    private void deleteNote(String code){
        String sql = "delete from " + NoteDatabase.TABLE_BOOKMARK + " where " + "code = '" + code +"'";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }
}


