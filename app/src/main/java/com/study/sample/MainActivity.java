package com.study.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TO change the color of Action Bar
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));

        textView=findViewById(R.id.textView);
       logoutbtn=findViewById(R.id.logout_button);


        // Click on Logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,Login.class));
                FirebaseAuth.getInstance().signOut();
                finish();
           }
        });


    }
}