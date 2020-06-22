package com.example.mehdi.footballapp;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Players extends AppCompatActivity {
    private ArrayList<Player> list ;
    private RecyclerView recyclerView;
    PlayerAdapter joueursAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Player>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Joueurs");
        ref.orderByChild("email").equalTo(firebaseUser.getEmail()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    Player i = dataSnapshot1.getValue(Player.class);
                    list.add(i);

                }
                joueursAdapter = new PlayerAdapter(Players.this,list);
                recyclerView.setAdapter(joueursAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Players.this,"Something gone wrong",Toast.LENGTH_SHORT).show();

            }

        });

    }
}
