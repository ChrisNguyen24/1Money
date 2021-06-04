package com.example.sqlitedemo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.Note.ActivitySelectedItem;
import com.example.sqlitedemo.R;
import com.example.sqlitedemo.SQLite.SQLiteNoteDone;
import com.example.sqlitedemo.SQLite.SQLiteStudentHelper;
import com.example.sqlitedemo.fragmentBottom.FragmentHome;
import com.example.sqlitedemo.model.Note;

import java.util.List;

public class RevAdapter extends RecyclerView.Adapter<RevAdapter.CardView>{
    private Activity activity;
    private List<Note> mList;
    private SQLiteStudentHelper sqLiteStudentHelper;
    private SQLiteNoteDone sqLiteNoteDone;
    private RevDoneAdapter revDoneAdapter;

    public RevAdapter(Activity activity) {
        sqLiteStudentHelper = new SQLiteStudentHelper(activity);
        sqLiteNoteDone = new SQLiteNoteDone(activity);
        revDoneAdapter = new RevDoneAdapter(activity);
        this.activity = activity;
    }

    public void setData(List<Note> list){
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
        View v = activity.getLayoutInflater().inflate(R.layout.item_card, parent,false);
        return new CardView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardView holder, int position) {
        Note c = mList.get(position);
        holder.id.setText(Integer.toString(c.getId()));
        holder.title.setText(c.getTitle());
        holder.des.setText(c.getDescription());
        holder.done.setChecked(false);
        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "tessst",Toast.LENGTH_SHORT).show();
                sqLiteStudentHelper.deleteById(c.getId());
                setData(sqLiteStudentHelper.getAll());
                //end update note up

                //update done
                //test
                sqLiteNoteDone.addDone(c);
                for(Note i:sqLiteNoteDone.getAllDone()){
                    System.out.println("test note done "+i.toString());
                }
                revDoneAdapter.setData(sqLiteNoteDone.getAllDone());
            }
        });

        holder.date.setText(c.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ActivitySelectedItem.class);
                intent.putExtra("item", c);
                activity.startActivity(intent);
            }
        });
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
