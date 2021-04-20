package com.example.ahar.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahar.Activity.RestaurantHomeActivity;
import com.example.ahar.Activity.RiderHomeActivity;
import com.example.ahar.R;

public class RiderHomeFragment extends Fragment {

    private Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rider_home, container, false);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((RiderHomeActivity)getActivity()).setToolbar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

}