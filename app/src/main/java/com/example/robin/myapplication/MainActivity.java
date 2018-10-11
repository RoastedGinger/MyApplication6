package com.example.robin.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity{

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_page);
        textView = findViewById(R.id.icon);
        LoginActivity login = new LoginActivity();
  //      Typeface face = Typeface.createFromAsset(getAssets(),"font/Bilbo Swash Caps.ttf");
  //      textView.setTypeface(face);
        SharedPreferences prefs = getSharedPreferences("user",MODE_PRIVATE);
        String restoredText = prefs.getString("status", null);
        if (restoredText != null) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.enter_from_left);
        fragmentTransaction.add(R.id.cre,login,"login666");
        fragmentTransaction.commit();

    }



    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
            //moveTaskToBack(true);
        }
    }

}
