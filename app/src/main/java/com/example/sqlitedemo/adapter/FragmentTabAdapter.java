package com.example.sqlitedemo.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sqlitedemo.fragmentChart.FragmentCategory;
import com.example.sqlitedemo.fragmentChart.FragmentType;

/**
 * Created by congnguyen on 5/28/21.
 */
public class FragmentTabAdapter extends FragmentStatePagerAdapter {
    private int numPage = 2;
    public FragmentTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentCategory();
            case 1: return new FragmentType();
            default:return new FragmentCategory();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Money by Category";
            case 1: return "Money by Type";
            default:return "Money by Category";
        }
    }
}
