package com.example.mehdi.footballapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Teams extends AppCompatActivity {
    private android.support.design.widget.FloatingActionButton addTeam;
    private ArrayList<Team> list;
    private RecyclerView recyclerView;
    TeamAdapter teamAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        addTeam = (android.support.design.widget.FloatingActionButton)findViewById(R.id.btnAddTeam);

        addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Teams.this, AddTeamPlayer.class);
                in.putExtra("id","a");
                startActivity(in);
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Team>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Equipe");
        ref.orderByChild("mail").equalTo(firebaseUser.getEmail()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    Team ii = dataSnapshot1.getValue(Team.class);
                    list.add(ii);

                }
                teamAdapter = new TeamAdapter(Teams.this,list);
                recyclerView.setAdapter(teamAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Teams.this,"Error ",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
