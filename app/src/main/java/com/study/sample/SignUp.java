package com.study.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SignUp extends AppCompatActivity {
    EditText memail, mpassword, mconfirmpassword;
    TextView mloginbtn;
    Button signupbtn;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //This line hides the action bar
        getSupportActionBar().hide();

        memail=findViewById(R.id.edit_text_email);
        mpassword=findViewById(R.id.edit_text_password);
        mconfirmpassword=findViewById(R.id.edit_text_confirm_password);
        mloginbtn=findViewById(R.id.loginbtn);
        signupbtn=findViewById(R.id.button_signup);

        fAuth=FirebaseAuth.getInstance();

        // To Check is user already login or not
        if (fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }


        // On click Login (Already have an account)
        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });


        // Click on Signup button
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= memail.getText().toString().trim();
                String password=mpassword.getText().toString().trim();
                String confirmpassword=mconfirmpassword.getText().toString().trim();


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

                // Check is confirm password box empty
                if (TextUtils.isEmpty(confirmpassword)){
                    mconfirmpassword.setError("Confirm Password is required.");
                    return;
                }

                // To check password is same confirm password
                if (!password.equals(confirmpassword)){
                    mconfirmpassword.setError("Password not match");
                    return;
                }



                // Register the user in Firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(SignUp.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(SignUp.this, "Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });








    }
}