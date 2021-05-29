package com.example.sqlitedemo.fragmentBottom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.adapter.FragmentTabAdapter;
import com.google.android.material.tabs.TabLayout;


public class FragmentSearch extends Fragment {
    private TabLayout tab;
    private ViewPager viewPager;
    private FragmentTabAdapter fragmentTabAdapter;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_search, container, false);
        //tab in Chart
        tab = v.findViewById(R.id.tabLayout);
        viewPager = v.findViewById(R.id.vp);
        fragmentTabAdapter = new FragmentTabAdapter(getChildFragmentManager(), FragmentTabAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(fragmentTabAdapter);
        tab.setupWithViewPager(viewPager);
        return v;
    }
}