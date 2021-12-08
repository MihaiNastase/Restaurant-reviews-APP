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

public class Login extends AppCompatActivity {


    TextInputLayout logEmail, logPassword;
    Button logIn, toRegistration;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        logEmail = findViewById(R.id.email);
        logPassword = findViewById(R.id.password);
        logIn = findViewById(R.id.login_btn);
        toRegistration = findViewById(R.id.registration_page);

        fAuth = FirebaseAuth.getInstance();
        //check for user login
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Menu.class));
            finish();
        }

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = logEmail.getEditText().getText().toString().trim();
                String password = logPassword.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    logEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    logPassword.setError("Password is required");
                    return;
                }

                //signing in in the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Menu.class));
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Could not Log In", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        toRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });



    }


}