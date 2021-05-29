package com.example.sqlitedemo.fragmentBottom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.adapter.RevMoneyAdapter;
import com.example.sqlitedemo.SQLite.SQLiteMoneyHelper;
import com.example.sqlitedemo.model.Money;

import java.util.ArrayList;
import java.util.List;


public class FragmentMail extends Fragment {

    private RevMoneyAdapter moneyAdapter;
    private List<Money> list;
    private RecyclerView recyclerView;
    private SQLiteMoneyHelper sqlite;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //in Wallet
        View v = inflater.inflate(R.layout.fragment_mail, container, false);
        sqlite = new SQLiteMoneyHelper(getContext());
        recyclerView = v.findViewById(R.id.revMoney);
        moneyAdapter = new RevMoneyAdapter(getActivity());
        list = new ArrayList<>();
        //list.add(new Money(1,"1234","test","test2","test3","note"));
        moneyAdapter.setData(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(moneyAdapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        moneyAdapter.setData(sqlite.getAll());
        recyclerView.setAdapter(moneyAdapter);
    }
}