package com.example.firstproject;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    String countries[];

    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        countries = getResources().getStringArray(R.array.countries);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,countries);
        listView.setAdapter(arrayAdapter);
    }
}