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

public class Matches extends AppCompatActivity {
    private android.support.design.widget.FloatingActionButton addMatch;
    private ArrayList<Matche> list ;
    private RecyclerView recyclerView;
    MatchesAdapter matchesAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        addMatch = (android.support.design.widget.FloatingActionButton)findViewById(R.id.btnAddMatch);

        addMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Matches.this, AddMatche.class);
                in.putExtra("id","a");
                startActivity(in);
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Matche>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Matche");
        ref.orderByChild("email").equalTo(firebaseUser.getEmail()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    Matche i = dataSnapshot1.getValue(Matche.class);
                    list.add(i);

                }
                matchesAdapter = new MatchesAdapter(Matches.this,list);
                recyclerView.setAdapter(matchesAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Matches.this,"Something gone wrong",Toast.LENGTH_SHORT).show();

            }

        });

    }
}
