package com.example.sqlitedemo.fragmentBottom;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.api.ApiService;
import com.example.sqlitedemo.extention.ActivityCovid;
import com.example.sqlitedemo.extention.ActivityTax;
import com.example.sqlitedemo.menu.ActivityContact;
import com.example.sqlitedemo.model.Covid;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentNoty extends Fragment {
    private CardView cardViewCovid, cardViewTax, cardViewLoca;
    private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_noty, container, false);
        cardViewCovid = v.findViewById(R.id.exCovid);
        cardViewTax = v.findViewById(R.id.exTax);
        cardViewLoca = v.findViewById(R.id.exLoca);
        cardViewCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallApi();
            }
        });
        cardViewTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityTax.class);
                startActivity(intent);
            }
        });
        cardViewLoca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=atm");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        return v;
    }

    private void clickCallApi() {
        ApiService.apiService.getCovid().enqueue(new Callback<Covid>() {
            @Override
            public void onResponse(Call<Covid> call, Response<Covid> response) {
                Toast.makeText(getContext(),"call api OKK",Toast.LENGTH_SHORT).show();
                Covid covid = response.body();
                if(covid!=null){
                    Intent intent = new Intent(v.getContext(), ActivityCovid.class);
                    intent.putExtra("covid",covid);
                    startActivity(intent);
                    System.out.println("call api ok");
                }
            }

            @Override
            public void onFailure(Call<Covid> call, Throwable t) {
                Toast.makeText(getContext(),"call api fail",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.mymenu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.myInfo:
                //Toast.makeText(this,"get contact", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ActivityContact.class);
                startActivity(intent);
                break;
            case R.id.myExit:
                System.exit(0);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}