package com.example.userguidescreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainScreen extends Activity {
    private Button btnSignOut;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main_screen);
        super.onCreate(savedInstanceState);
        btnSignOut = findViewById(R.id.btn_signout);
        mAuth = FirebaseAuth.getInstance();
        btnSignOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(MainScreen.this,LogInActivity.class));
            }
        });
    }
}
