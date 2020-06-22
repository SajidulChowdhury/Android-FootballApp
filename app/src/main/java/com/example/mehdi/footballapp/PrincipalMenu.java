package com.example.mehdi.footballapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PrincipalMenu extends AppCompatActivity {
    private Button btnMatches,btnTeams,btnPlayers,btnSearch;
    private android.support.design.widget.FloatingActionButton signout;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnTeams = (Button)findViewById(R.id.btnTeams);
        btnMatches = (Button)findViewById(R.id.btnMatches);
        btnPlayers = (Button)findViewById(R.id.btnPlayers);

        signout   = (android.support.design.widget.FloatingActionButton)findViewById(R.id.btnDeconnection);

     signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalMenu.this,R.style.MyDialogTheme);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Are you sure to sign out?");
                builder.setIcon(R.drawable.im5);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseAuth.getInstance().signOut();
                        Intent in = new Intent(PrincipalMenu.this,SignIn.class);
                        startActivity(in);
                        finish();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

     btnMatches.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent in = new Intent(PrincipalMenu.this,Matches.class);
             startActivity(in);
         }
     });
     btnTeams.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent in = new Intent(PrincipalMenu.this, Teams.class);
             startActivity(in);
         }
     });
     btnPlayers.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent in = new Intent(PrincipalMenu.this, Players.class);
             startActivity(in);
         }
     });
     /*
     btnSearch.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent in = new Intent(PrincipalMenu.this, SearchTeam.class);
             startActivity(in);
         }
     });

      */

    }
}
