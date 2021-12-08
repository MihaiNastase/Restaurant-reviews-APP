package com.assignment.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextInputLayout Email, role;
    TextView fullName, userName;

    FirebaseAuth fAuth;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //hooks
        fullName = findViewById(R.id.full_name);
        userName = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        role = findViewById(R.id.role);

        //getting data from database
        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            String uid = fAuth.getCurrentUser().getUid();
            userRef = FirebaseDatabase.getInstance().getReference("users");

            Query getUser = userRef.orderByChild("uid").equalTo(uid);
            getUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        Toast.makeText(Profile.this, "Data loading", Toast.LENGTH_SHORT).show();
                        UserModel user = new UserModel();
                        for(DataSnapshot ds : snapshot.getChildren()){
                            user =ds.getValue(UserModel.class);
                        }
                        fullName.setText(user.name);
                        userName.setText(user.username);
                        Email.getEditText().setText(user.email);
                        role.getEditText().setText(user.role);
                    } else {
                        Toast.makeText(Profile.this, uid, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}