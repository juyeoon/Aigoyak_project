package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//////////////////////////////////////////이거 고치는 중
public class B_AddSearchAdapter extends RecyclerView.Adapter<B_AddSearchAdapter.ViewHolder> {//일단 완료
    private static final String TAG = "B_AddSearchAdapter";

    ArrayList<Search> items = new ArrayList<Search>();
    OnNoteItemClickListener listener;
    Context context;


    public B_AddSearchAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.fragment_add_search_list, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position){
        viewHolder.setItem(items.get(position));
        viewHolder.setLayout();

        String productName = items.get(position).getName();
        String productCode = items.get(position).getCode();
        String productCorp = items.get(position).getCorp();

        viewHolder.select_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context,B_AddMedicine.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("productName", productName);//의약품명 B_AddMedicine로 보냄.
                intent.putExtra("productCode", productCode);//품번 B_AddMedicine로 보냄.
                intent.putExtra("productCorp", productCorp);//품번 B_AddMedicine로 보냄.

                System.out.println("B_AddSearchAdapter - productName: " + productName);
                System.out.println("B_AddSearchAdapter - productCode: " + productCode);
                System.out.println("B_AddSearchAdapter - productCode: " + productCorp);

                context.startActivity(intent);
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

    public void setOnItemClickListener(OnNoteItemClickListener listener){//x
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView name;
        TextView corp;
        Button select_button;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            layout = itemView.findViewById(R.id.layout1);
            name = itemView.findViewById(R.id.name);
            corp = itemView.findViewById(R.id.corp);
            select_button = itemView.findViewById(R.id.select_button);




        }

        public void setItem(Search item) {
            name.setText(item.getName());
            corp.setText(item.getCorp());
        }
        public void setLayout(){
            layout.setVisibility(View.VISIBLE);
        }
    }
}
