package com.example.sqlitedemo.fragmentChart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.SQLite.SQLiteMoneyHelper;
import com.example.sqlitedemo.adapter.RevMoneyAdapter;
import com.example.sqlitedemo.model.Money;

import java.util.ArrayList;
import java.util.List;


public class FragmentType extends Fragment {
    private View v;
    private Spinner spinnerType;
    private RecyclerView recyclerView;
    private TextView total;
    private RevMoneyAdapter revMoneyAdapter;
    private SQLiteMoneyHelper sqlite;
    List<Money> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_type, container, false);
        spinnerType = v.findViewById(R.id.spinnerType);
        recyclerView = v.findViewById(R.id.revType);
        total = v.findViewById(R.id.total);
        sqlite = new SQLiteMoneyHelper(getContext());
        revMoneyAdapter = new RevMoneyAdapter(getActivity());
        list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        revMoneyAdapter.setData(list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(revMoneyAdapter);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lsType  = getResources().getStringArray(R.array.type);
                revMoneyAdapter.setData(sqlite.getByType(lsType[position]));
                total.setText(String.format("%.0f",sqlite.sumType(lsType[position]))+".000.VNĐ");
                recyclerView.setAdapter(revMoneyAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        String type = spinnerType.getSelectedItem().toString();
        total.setText(String.format("%.0f",sqlite.sumType(type))+".000.VNĐ");
        revMoneyAdapter.setData(sqlite.getByType(type));
        recyclerView.setAdapter(revMoneyAdapter);
    }
}