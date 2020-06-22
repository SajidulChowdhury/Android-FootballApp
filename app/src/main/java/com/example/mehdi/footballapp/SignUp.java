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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.text.TextUtils.isEmpty;

public class SignUp extends AppCompatActivity {
    //Declaration des deux boutons de connection et d'inscription
    private Button btnGetConnected;
    private Button btnSubscribe;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private EditText passid;
    private EditText emailid;
    private EditText copassid;
    private EditText nameid;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //On a mis cette commande desous pour éviter la rotation quand le bouton de rotation est activé sur le telephone
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //ici on va chercher les boutons a utiliser par leur id
        btnGetConnected = (Button)findViewById(R.id.buttonGetConnected);
        btnSubscribe = (Button)findViewById(R.id.buttonSubscribe);
        emailid = (EditText) findViewById(R.id.emailid);
        passid =(EditText) findViewById(R.id.passid);
        copassid =(EditText) findViewById(R.id.copassid);
        nameid=(EditText)findViewById(R.id.nameid);
        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Utilisateur");

        btnGetConnected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent a comme rôle d'aller d'une activite a une autre
                Intent in = new Intent(SignUp.this,SignIn.class);
                startActivity(in);
            }
        });
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnregistrementUtilisateur();
            }
        });
    }

    public void EnregistrementUtilisateur(){
        final String name      = nameid.getText().toString().trim();
        final String Email     = emailid.getText().toString().trim();
        String Password  = passid.getText().toString().trim();
        String Password1 = copassid.getText().toString().trim();
        if(isEmpty( name))
        {
            Toast.makeText(this, "Enter Your Name", Toast.LENGTH_SHORT).show();
            return;}
        if(isEmpty( Password1))
        {
            Toast.makeText(this, "Enter your confirmation password", Toast.LENGTH_SHORT).show();
            return;}

        if (!Password1.equals(Password))
        {
            Toast.makeText(this, "your password don't match ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isEmpty(Email)){
            Toast.makeText(this, "Enter your mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isEmpty(Password)){
            Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user= new User(name,Email);
                            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(SignUp.this, "Successful",
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent in= new Intent(getApplicationContext(), PrincipalMenu.class);
                                    startActivity(in);
                                }
                            });

                        }else{
                            Toast.makeText(SignUp.this, "Error, mail already exist or verify your internet connexion",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
