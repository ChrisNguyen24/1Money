package com.example.sqlitedemo.fragmentBottom;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.SQLite.SQLiteNoteDone;
import com.example.sqlitedemo.adapter.RevAdapter;
import com.example.sqlitedemo.SQLite.SQLiteStudentHelper;
import com.example.sqlitedemo.adapter.RevDoneAdapter;
import com.example.sqlitedemo.model.Cong;
import com.example.sqlitedemo.model.Note;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment {
    private RevAdapter revAdapter;
    private RevDoneAdapter revDoneAdapter;
    private View v;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewDone;
    private List<Note> list,done;
    private EditText etSearch;
    //sqli
    private SQLiteStudentHelper sqli;
    private SQLiteNoteDone sqLiteNoteDone;
    private Button resetDone;
    private MenuItem fav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.rev);

        revAdapter = new RevAdapter(getActivity());
        revDoneAdapter = new RevDoneAdapter(getActivity());

        list  = new ArrayList<>();
        done = new ArrayList<>();
//        list.add(new Note(1,"Cong","content","date"));
        sqli = new SQLiteStudentHelper(getContext());
        sqLiteNoteDone = new SQLiteNoteDone(getContext());
        //revAdapter.setData(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(revAdapter);

        recyclerViewDone = v.findViewById(R.id.revDone);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        recyclerViewDone.setLayoutManager(layoutManager1);
        recyclerViewDone.setAdapter(revDoneAdapter);
        //searchView = v.findViewById(R.id.serView);
        resetDone = v.findViewById(R.id.resetDone);
        resetDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteNoteDone.deleteAll();
                revDoneAdapter.setData(sqLiteNoteDone.getAllDone());
            }
        });
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Cong> ls = new ArrayList<>();
                ls = sqli.getByName(String.valueOf(newText));
                revAdapter.setData(ls);
                return false;
            }
        });*/

    }

    @Override
    public void onResume() {
        super.onResume();
        //Toast.makeText(getContext(), "loading.....", Toast.LENGTH_SHORT).show();
        list = sqli.getAll();
        revAdapter.setData(list);
        done = sqLiteNoteDone.getAllDone();
        revDoneAdapter.setData(done);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull  Menu menu, @NonNull  MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.mSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list = sqli.getByName(newText);
                revAdapter.setData(list);
                recyclerView.setAdapter(revAdapter);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}