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

    ArrayList<Search> items = new ArrayList<Search>();
    OnNoteItemClickListener listener;
    Context context;

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


        NoteDatabase database = NoteDatabase.getInstance(context);
        String sql = "select name from " + NoteDatabase.TABLE_BOOKMARK;
        Log.d(TAG, "sql : " + sql);
        String name = items.get(position).getName();
        int recordCount=-1;
        if (database != null){
            Cursor outCursor = database.rawQuery(sql);
            recordCount = outCursor.getCount();

            for(int i=0; i<recordCount; i++){
                outCursor.moveToNext();
                String nname = outCursor.getString(0);
                System.out.println("F_BookmarkAdapter(nname): "+nname);

                if(name.equals(nname)){//Bookmark에 저장되어 있으면 버튼 선택된 상태
                    viewHolder.heart.setSelected(true);
                }
            }
            outCursor.close();
        }


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

            heart.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(heart.isSelected()){
                        heart.setSelected(false);
                        deleteNote(name.getText().toString());
                    }
                    else{
                        heart.setSelected(true);
                        saveNote(name.getText().toString(), corp.getText().toString());
                    }
                }
            });

        }

        public void setItem(Search item) {//내가 적은 텍스트를 불러와 저장하는 것
            heart.setVisibility(View.VISIBLE); //heart 조건 걸어서 보이게 하기 (아직 안 함)
            name.setText(item.getName());
            corp.setText(item.getCorp());
        }
        public void setLayout(){
            layout.setVisibility(View.VISIBLE);
        }
    }

    //db에 데이터 저장
    private void saveNote(String name, String corp){

        String sql = "insert into " +NoteDatabase.TABLE_BOOKMARK +//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기
                "(NAME, CORP) values (" +
                "'"+ name + "', " +
                "'"+ corp + "')";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

    }
    //db에서 삭제
    private void deleteNote(String name){
        String sql = "delete from " + NoteDatabase.TABLE_BOOKMARK + " where " + "name = '" + name +"'";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }
}


