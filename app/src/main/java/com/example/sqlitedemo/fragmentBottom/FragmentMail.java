package com.example.sqlitedemo.fragmentBottom;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sqlitedemo.Money.ActivityAddWallet;
import com.example.sqlitedemo.R;
import com.example.sqlitedemo.adapter.RevMoneyAdapter;
import com.example.sqlitedemo.SQLite.SQLiteMoneyHelper;
import com.example.sqlitedemo.model.Money;
import com.example.sqlitedemo.model.Wallet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FragmentMail extends Fragment {
    private ImageView btShowHide;
    private TextView money;
    private RevMoneyAdapter moneyAdapter;
    private List<Money> list;
    private RecyclerView recyclerView;
    private SQLiteMoneyHelper sqlite;
    private int frag = 1;
    private final String walletFile = "wallet";

    private String readFile(){
        String res = "";
        try {
            FileInputStream fileInputStream = getActivity().openFileInput(walletFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line=br.readLine())!=null){
                buffer.append(line);
            }
            Log.v("test file",buffer.toString());
            res = buffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //in Wallet
        View v = inflater.inflate(R.layout.fragment_mail, container, false);
        sqlite = new SQLiteMoneyHelper(getContext());
        btShowHide = v.findViewById(R.id.showBtn);
        money = v.findViewById(R.id.etMoney);

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityAddWallet.class);
                startActivity(intent);
            }
        });

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String balance = String.valueOf(String.format("%.3f", sqlite.balance(Float.parseFloat(readFile()))));
        money.setText(balance+" Đ");
        btShowHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(frag==1){
                    btShowHide.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                    money.setText("****"+" Đ");
                    frag = 0;
                }
                else{
                    money.setText(balance+" Đ");
                    btShowHide.setImageResource(R.drawable.ic_baseline_money_off_24);
                    frag = 1;
                }
            }
        });
    }

    @Override
    public void onResume() {
        String balance = String.valueOf(String.format("%.3f", sqlite.balance(Float.parseFloat(readFile()))));
        money.setText(balance+" Đ");
        super.onResume();
        moneyAdapter.setData(sqlite.getAll());
        recyclerView.setAdapter(moneyAdapter);
        btShowHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(frag==1){
                    btShowHide.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                    money.setText("****"+" Đ");
                    frag = 0;
                }
                else{
                    money.setText(balance+" Đ");
                    btShowHide.setImageResource(R.drawable.ic_baseline_money_off_24);
                    frag = 1;
                }
            }
        });
    }

}