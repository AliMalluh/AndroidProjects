package com.example.senddata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText weight,height;
    Button calcualte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FirstFragment firstFragment = new FirstFragment();

        weight = findViewById(R.id.editTextText);
        height = findViewById(R.id.editTextText2);
        calcualte = findViewById(R.id.button);

        calcualte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                int userWeight = Integer.parseInt(weight.getText().toString());
                int userHeight = Integer.parseInt(height.getText().toString());

                bundle.putInt("Weight",userWeight);
                bundle.putInt("Height",userHeight);

                firstFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.frame, firstFragment);
                fragmentTransaction.commit();

            }
        });
    }
}