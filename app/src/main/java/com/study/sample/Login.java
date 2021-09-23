package com.study.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText memail, mpassword;
    Button loginbtn;
    TextView msignupbtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //This line hides the action bar
        getSupportActionBar().hide();

        memail=findViewById(R.id.edit_text_email);
        mpassword=findViewById(R.id.edit_text_password);
        loginbtn=findViewById(R.id.button_login);
        msignupbtn=findViewById(R.id.signupbtn);
        fAuth=FirebaseAuth.getInstance();

        // Click on login button
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= memail.getText().toString().trim();
                String password=mpassword.getText().toString().trim();

                // Check is email box empty
                if (TextUtils.isEmpty(email)){
                    memail.setError("Email is Required.");
                    return;
                }

                // Check is password box empty
                if (TextUtils.isEmpty(password)){
                    mpassword.setError("Password is required.");
                    return;
                }

                // The password length must be greter than 5 characters
                if (password.length()<5){
                    mpassword.setError("Password must be greater than 5 characters");
                    return;
                }

                // Call data from firebase for login
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


         // On click SignUp (Don't have an account)
        msignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
                finish();
            }
        });


    }
}