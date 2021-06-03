package com.example.sqlitedemo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.Note.ActivitySelectedItem;
import com.example.sqlitedemo.R;
import com.example.sqlitedemo.SQLite.SQLiteNoteDone;
import com.example.sqlitedemo.SQLite.SQLiteStudentHelper;
import com.example.sqlitedemo.model.Note;

import java.util.List;

public class RevDoneAdapter extends RecyclerView.Adapter<RevDoneAdapter.CardView>{
    private Activity activity;
    private List<Note> mList;

    public RevDoneAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<Note> list){
        System.out.println("test update");
        this.mList = list;
        notifyDataSetChanged();
    }

    public void addData(Note c){
        this.mList.add(c);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = activity.getLayoutInflater().inflate(R.layout.item_card_done, parent,false);
        return new CardView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardView holder, int position) {
        Note c = mList.get(position);
        holder.id.setText(Integer.toString(c.getId()));
        holder.title.setText(c.getTitle());
        holder.des.setText(c.getDescription());
        holder.done.setVisibility(View.GONE);
        holder.date.setText(c.getDate());
    }

    @Override
    public int getItemCount() {
        if(mList!=null)return mList.size();
        return 0;
    }

    public class CardView extends RecyclerView.ViewHolder {
        TextView id, title, des, date;
        CheckBox done;
        ImageView img;
        public CardView(@NonNull View v) {
            super(v);
            done = v.findViewById(R.id.iDone);
            id = v.findViewById(R.id.iID);
            title = v.findViewById(R.id.iTitle);
            des = v.findViewById(R.id.iDesc);
            date = v.findViewById(R.id.iDate);
        }
    }
}
