package com.assignment.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    TextInputLayout regName, regEmail, regUsername, regPassword;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        //Hooks to all the form elements in the registration page
        regName         = findViewById(R.id.reg_name);
        regEmail        = findViewById(R.id.reg_email);
        regUsername     = findViewById(R.id.reg_username);
        regPassword     = findViewById(R.id.reg_password);
        regBtn          = findViewById(R.id.reg_btn);
        regToLoginBtn   = findViewById(R.id.login_screen);
        progressBar = findViewById(R.id.progressBar2);


        fAuth = FirebaseAuth.getInstance();
        //check for user login
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Menu.class));
            finish();
        }

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rootNode = FirebaseDatabase.getInstance();
                reference = FirebaseDatabase.getInstance().getReference();


                //Fetching from form
                String name = regName.getEditText().getText().toString().trim();
                String email = regEmail.getEditText().getText().toString().trim();
                String username = regUsername.getEditText().getText().toString().trim();
                String password = regPassword.getEditText().getText().toString().trim();
                //By default everyone registered this way is an user
                String role = "user";

                if(TextUtils.isEmpty(email)){
                    regEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    regEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(username)){
                    regEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    regPassword.setError("Email is required");
                    return;
                }
                if(password.length() < 6) {
                    regPassword.setError("Password must be at least 6 characters long");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //register user to firebase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            String id = fAuth.getCurrentUser().getUid();

                            UserModel userModel = new UserModel(name,email,username,password,id ,role);

                            reference.child("users").child(id).setValue(userModel);

                            Toast.makeText(Registration.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Menu.class));
                            finish();
                        } else {
                            Toast.makeText(Registration.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //

                //
            }
        });

        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });
    }



}