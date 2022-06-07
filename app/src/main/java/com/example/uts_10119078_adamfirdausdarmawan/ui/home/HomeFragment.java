package com.example.uts_10119078_adamfirdausdarmawan.ui.home;
/*
    Nama : Adam Firdaus Darmawan
    Kelas : IF-2
    NIM : 10119078
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.uts_10119078_adamfirdausdarmawan.R;

public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}