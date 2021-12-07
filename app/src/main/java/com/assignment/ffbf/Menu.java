package com.assignment.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {

    Button logOut;
    //FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        logOut = findViewById(R.id.logOut_btn);

        //fAuth = FirebaseAuth.getInstance();

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }


}