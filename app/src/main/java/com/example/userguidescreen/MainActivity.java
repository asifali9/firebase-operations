package com.example.userguidescreen;

import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.FirebaseAnalyticsEvent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class MainActivity extends AppCompatActivity {


//    private TourGuide mTourGuide;
    private String stEmail,stPassword;
    private FirebaseAuth mAuth;
    private EditText etEmail,etPassword;
    private TextView tvAlreadyLogin;
    private Button btnCreateUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        File fle = Environment.getExternalStoragePublicDirectory(Environment.MEDIA_MOUNTED);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvAlreadyLogin = findViewById(R.id.tv_already_register);
        btnCreateUser = findViewById(R.id.btn_create_user);
        mAuth = FirebaseAuth.getInstance();
//        Crashlytics.getInstance().crash();
        etEmail.setFocusable(true);
        etEmail.requestFocus();

        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT)
                {
                    stEmail = etEmail.getText().toString();

                    Toast.makeText(MainActivity.this, "next", Toast.LENGTH_SHORT).show();

                }
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();


                    return true;
                }
                return false;
            }
        });
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stPassword = etPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(stEmail,stPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent(MainActivity.this,MainScreen.class));
                        }else
                        {
                            Toast.makeText(MainActivity.this, ""+
                                    task.getException().toString(), Toast.LENGTH_SHORT).show();
                            Log.d("test", "onComplete: "+task.getException().toString());
                        }
                    }
                });
            }
        });
        tvAlreadyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LogInActivity.class));
            }
        });
    }

}
