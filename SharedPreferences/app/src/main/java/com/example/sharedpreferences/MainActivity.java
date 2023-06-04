package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextMessage;
    Button button;
    CheckBox checkBox;
    int count = 0;
    String name;
    String message;
    boolean isChecked;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        editTextMessage = findViewById(R.id.editTextMessage);
        button = findViewById(R.id.button);
        checkBox = findViewById(R.id.checkBox);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                button.setText(""+count);
            }
        });

        retrieveData();

    }

    @Override
    protected void onPause() {
        saveData();
        super.onPause();
    }

    public void saveData(){
        sharedPreferences = getSharedPreferences("saveData", Context.MODE_PRIVATE);
        name = editTextName.getText().toString();
        message = editTextMessage.getText().toString();
        if(checkBox.isChecked()){
            isChecked = true;
        }else{
            isChecked = false;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key name",name);
        editor.putString("key message",message);
        editor.putInt("key count",count);
        editor.putBoolean("key remember",isChecked);
        editor.commit();

        Toast.makeText(getApplicationContext(),"Your Data Saved",Toast.LENGTH_LONG).show();
    }

    public void retrieveData(){
        sharedPreferences = getSharedPreferences("saveData",MODE_PRIVATE);
        name = sharedPreferences.getString("key name", null);
        message = sharedPreferences.getString("key message",null);
        count = sharedPreferences.getInt("key count", 0);
        isChecked = sharedPreferences.getBoolean("key remember", false);

        editTextName.setText(name);
        editTextMessage.setText(message);
        button.setText(""+count);
        if(isChecked){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }
    }
}