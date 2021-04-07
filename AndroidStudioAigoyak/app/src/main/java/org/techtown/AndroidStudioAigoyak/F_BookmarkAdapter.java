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

public class F_BookmarkAdapter extends RecyclerView.Adapter<F_BookmarkAdapter.ViewHolder> {//일단 완료
    private static final String TAG = "BookmarkAdapter";

    ArrayList<Search> items = new ArrayList<Search>();

    OnNoteItemClickListener listener;

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
        //Note item = items.get(position); //위랑 같은 코드
        //viewHolder.setItem(item);
        viewHolder.setLayout();
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

    public void setOnItemClickListener(OnNoteItemClickListener listener){//x
        this.listener = listener;
    }




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
            heart = itemView.findViewById(R.id.heart1);


        }

        public void setItem(Search item) {//내가 적은 텍스트를 불러와 저장하는 것
            heart.setVisibility(View.VISIBLE); //warning 조건 걸어서 보이게 하기 (아직 안 함)
            name.setText(item.getName());
            corp.setText(item.getCorp());
        }
        public void setLayout(){
            layout.setVisibility(View.VISIBLE);
        }
    }

}
