package com.example.sqlitedemo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.Money.ActivitySelectMoney;
import com.example.sqlitedemo.R;
import com.example.sqlitedemo.model.Money;

import java.util.List;

/**
 * Created by congnguyen on 5/28/21.
 */
public class RevMoneyAdapter extends RecyclerView.Adapter<RevMoneyAdapter.CardView>{
    private Activity activity;
    private List<Money> mList;

    public RevMoneyAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<Money> list){
        this.mList=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardView onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = activity.getLayoutInflater().inflate(R.layout.money_card,parent,false);
        return new CardView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RevMoneyAdapter.CardView holder, int position) {
        Money m = mList.get(position);
        holder.amount.setText(m.getAmount());
        holder.date.setText(m.getDate());
        if(m.getType().equals("Thu")){
            holder.type.setImageResource(R.drawable.ic_thu);
        }else{
            holder.type.setImageResource(R.drawable.ic_chi);
        }
        holder.category.setText(m.getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ActivitySelectMoney.class);
                intent.putExtra("money",m);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        else return 0;
    }

    public class CardView extends RecyclerView.ViewHolder {
        TextView amount, date, category;
        ImageView type;
        public CardView(@NonNull  View v) {
            super(v);
            amount = v.findViewById(R.id.iAmount);
            date = v.findViewById(R.id.iDateMoney);
            category = v.findViewById(R.id.iCategory);
            type = v.findViewById(R.id.iType);
        }
    }
}
