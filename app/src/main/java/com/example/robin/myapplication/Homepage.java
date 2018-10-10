package com.example.robin.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    String id;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        SharedPreferences prefs =getSharedPreferences("unique",MODE_PRIVATE);
        id  = prefs.getString("id", null);
        textView = findViewById(R.id.textfield);
        textView.setText("your customer id is "+id);

        }
}
