package com.example.mehdi.footballapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    private Button btnSignIn;
    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText email;
    private android.support.design.widget.TextInputEditText password;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        btnSignIn = (Button)findViewById(R.id.buttonGetConnected);
        btnSignUp = (Button)findViewById(R.id.buttonSubscribe);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        email = (EditText) findViewById(R.id.emailid);
        password = (android.support.design.widget.TextInputEditText) findViewById(R.id.passid);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectionUtilisateur();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SignIn.this,SignUp.class);
                startActivity(in);
            }
        });
    }

    public void ConnectionUtilisateur(){
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if(Email.equals("") || Password.equals(""))
        {Toast.makeText(this, "You should enter you mail and password", Toast.LENGTH_LONG).show();}
            else {
                mAuth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                finish();
                                Intent in=new Intent(getApplicationContext(), PrincipalMenu.class);
                                startActivity(in);
                            } else {
                                Toast.makeText(SignIn.this, "Error , please verify your connection or your email and password ",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
  public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

            startActivity(new Intent(SignIn.this, PrincipalMenu.class));
        } else {
        }
    }
}
