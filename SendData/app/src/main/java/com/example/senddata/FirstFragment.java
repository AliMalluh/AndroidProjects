package com.example.senddata;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    TextView result;
    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        result = view.findViewById(R.id.textView3);

        Bundle bundle = getArguments();
        int userWeight = bundle.getInt("Weight");
        int userHeight = bundle.getInt("Height");

        double userBmi = (userWeight*1000)/(userHeight*userWeight);

        result.setText("Your BMI is: "+userBmi);

        return view;
    }
}