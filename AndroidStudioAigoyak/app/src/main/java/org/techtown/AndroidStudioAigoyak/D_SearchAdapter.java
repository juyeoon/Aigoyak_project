package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class D_SearchAdapter extends RecyclerView.Adapter<D_SearchAdapter.ViewHolder> {//일단 완료
    private static final String TAG = "SearchAdapter";

    ArrayList<Search> items = new ArrayList<Search>();
    OnNoteItemClickListener listener;
    Context context;
    String code;
    String name;
    String corp;


    public D_SearchAdapter(Context context){
        this.context = context;
    }

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
                if(code.equals(ncode)){//Bookmark에 저장되어 있으면 버튼 선택된 상태
                    viewHolder.heart.setSelected(true);
                    break;
                }
                else{
                    viewHolder.heart.setSelected(false);
                }
            }
            outCursor.close();
        }

        viewHolder.itemView.setClickable(true);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(position != RecyclerView.NO_POSITION){
                    code = items.get(position).getCode();
                    name = items.get(position).getName();
                    corp = items.get(position).getCorp();

                    Intent intent =new Intent(context,E_MedicineInfo.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("code", code);//품목기준코드 E_MedicineInfo -> E_MedicineInfoDetail로 보냄.
                    intent.putExtra("name", name);//의약품명
                    intent.putExtra("corp", corp);//회사명

                    context.startActivity(intent);
                }
            }
        });

        viewHolder.heart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                code = items.get(position).getCode();
                name = items.get(position).getName();
                corp = items.get(position).getCorp();

                if(viewHolder.heart.isSelected()){
                    viewHolder.heart.setSelected(false);
                    deleteNote(code);
                }
                else{
                    viewHolder.heart.setSelected(true);
                    if(!(searchCode(code).equals(code))) {
                        saveNote(code, name, corp);
                    }
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
    }

    public void setItems(ArrayList<Search> items){
        this.items = items;
    }

    public void setOnItemClickListener(OnNoteItemClickListener listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView name;
        TextView corp;
        ImageButton heart;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            layout = itemView.findViewById(R.id.layout1);
            name = itemView.findViewById(R.id.name);
            corp = itemView.findViewById(R.id.corp);
            heart = itemView.findViewById(R.id.heart_button);

        }

        public void setItem(Search item) {
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

    //즐겨찾기에 저장이 되어있는지 확인
    private String searchCode(String code){
        String sql = "select code from " +NoteDatabase.TABLE_BOOKMARK + " where " + "code = '" + code + "'";
        Log.d(TAG, "sql: "+ sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

        String ncode="";
        if (database != null){
            Cursor outCursor = database.rawQuery(sql);
            outCursor.moveToNext();

            if( outCursor.getCount() == 1 ) {
                ncode = ncode + outCursor.getString(0);
            }
            outCursor.close();
        }
        return ncode;
    }
}
