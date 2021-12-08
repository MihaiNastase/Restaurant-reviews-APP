package com.assignment.ffbf;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {

    //Variables
    TextView toRestaurants, toStreetFood, toForum, toProfile, logOut;

    //FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //logOut = findViewById(R.id.logOut_btn);
        //hooks
        toRestaurants = findViewById(R.id.to_restaurants);
        toStreetFood = findViewById(R.id.to_street_food);
        toForum = findViewById(R.id.to_forum);
        toProfile = findViewById(R.id.to_profile);
        logOut = findViewById(R.id.logout);



        //fAuth = FirebaseAuth.getInstance();

        //Route from here to the other activities:
        toRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Restaurants.class));
                //do not finish activity so it can return back here
            }
        });

        toStreetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StreetFood.class));
            }
        });

        toForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Forum.class));
            }
        });

        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }


}