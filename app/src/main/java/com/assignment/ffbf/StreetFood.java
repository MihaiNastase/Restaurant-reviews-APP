package com.assignment.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StreetFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_food);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}