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

public class D_SearchAdapter extends RecyclerView.Adapter<D_SearchAdapter.ViewHolder> {//일단 완료
    private static final String TAG = "SearchAdapter";

    ArrayList<Search> items = new ArrayList<Search>();
    OnNoteItemClickListener listener;
    Context context;


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
    }

    //삭제하는건데 이거 연구해야할 것 같음.
    /*
    private void removeItemView(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size()); // 지워진 만큼 다시 채워넣기.
    }
     */

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

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView name;
        TextView corp;
        ImageView heart;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            layout = itemView.findViewById(R.id.layout1);
            name = itemView.findViewById(R.id.name);
            corp = itemView.findViewById(R.id.corp);
            heart = itemView.findViewById(R.id.heart1);

            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                        public void onClick(View v){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Intent intent =new Intent(context,E_MedicineInfo.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });

            //// 되나 몰것네
            /*
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    removeItemView(getAdapterPosition());
                    return false;
                }
            });
            ////
            */

        }

        public void setItem(Search item) {
            heart.setVisibility(View.VISIBLE); //heart 조건 걸어서 보이게 하기 (아직 안 함)
            name.setText(item.getName());
            corp.setText(item.getCorp());
        }
        public void setLayout(){
            layout.setVisibility(View.VISIBLE);
        }
    }



}
