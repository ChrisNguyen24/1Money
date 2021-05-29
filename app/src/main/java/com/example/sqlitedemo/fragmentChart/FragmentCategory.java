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
import android.widget.Toast;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.SQLite.SQLiteMoneyHelper;
import com.example.sqlitedemo.adapter.RevMoneyAdapter;
import com.example.sqlitedemo.model.Money;

import java.util.ArrayList;
import java.util.List;


public class FragmentCategory extends Fragment {
    private View v;
    private Spinner spinnerCategory;
    private RecyclerView recyclerView;

    private RevMoneyAdapter revMoneyAdapter;
    private SQLiteMoneyHelper sqlite;
    List<Money> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_category, container, false);
        spinnerCategory = v.findViewById(R.id.cSpinnerCategory);
        recyclerView = v.findViewById(R.id.cRevCate);

        sqlite = new SQLiteMoneyHelper(getContext());
        revMoneyAdapter = new RevMoneyAdapter(getActivity());
        list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        revMoneyAdapter.setData(list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(revMoneyAdapter);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lsCategory  = getResources().getStringArray(R.array.category);
                revMoneyAdapter.setData(sqlite.getByCategory(lsCategory[position]));
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
        String category = spinnerCategory.getSelectedItem().toString();
        revMoneyAdapter.setData(sqlite.getByCategory(category));
        recyclerView.setAdapter(revMoneyAdapter);
    }
}