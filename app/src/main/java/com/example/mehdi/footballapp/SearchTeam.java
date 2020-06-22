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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchTeam extends AppCompatActivity {
    private ArrayList<Matche> list ;
    private RecyclerView recyclerView;
    MatchesAdapter matchesAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private Button btnchercher;
    private EditText editchercher;
    private int x=0;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_team);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnchercher =(Button)findViewById(R.id.btnchercher);
        editchercher = (EditText)findViewById(R.id.editchercher);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        btnchercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editchercher.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"You should enter a Team Name",Toast.LENGTH_SHORT).show();
                }else{
                if(x==0){
                String aide = editchercher.getText().toString();
                recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchTeam.this));
                list = new ArrayList<Matche>();


                DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Matche");
                ref.orderByChild("equipe1").equalTo(aide).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                            Matche i = dataSnapshot1.getValue(Matche.class);
                            list.add(i);

                        }
                        matchesAdapter = new MatchesAdapter(SearchTeam.this,list);
                        recyclerView.setAdapter(matchesAdapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(SearchTeam.this,"Something gone wrong",Toast.LENGTH_SHORT).show();

                    }

                });
                ref.orderByChild("equipe2").equalTo(aide).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                            Matche i = dataSnapshot1.getValue(Matche.class);
                            list.add(i);

                        }
                        matchesAdapter = new MatchesAdapter(SearchTeam.this,list);
                        recyclerView.setAdapter(matchesAdapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(SearchTeam.this,"Something gone wrong",Toast.LENGTH_SHORT).show();

                    }

                });
                    btnchercher.setText("Research");
                    x++;
            }else{

                    x = 0;
                    finish();
                    Intent in = new Intent(SearchTeam.this, SearchTeam.class);
                    startActivity(in);
                }

            }
            }
        });



    }
}
