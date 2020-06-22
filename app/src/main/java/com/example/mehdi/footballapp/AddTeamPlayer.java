package com.example.mehdi.footballapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class AddTeamPlayer extends AppCompatActivity {

    private EditText nameteam;
    private EditText nameplayer1,ageplayer1,physic1;
    private EditText nameplayer2,ageplayer2,physic2;
    private EditText nameplayer3,ageplayer3,physic3;
    private Button btnAjouter;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private String keys,keys1,keys2,keys3,keys4,keys5,keys6,keys7,keys8,keys9,keys10,keys11,keys12;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team_player);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nameplayer1 = (EditText)findViewById(R.id.namePlayer1);
        nameplayer2 = (EditText)findViewById(R.id.namePlayer2);
        nameplayer3 = (EditText)findViewById(R.id.namePlayer3);
        ageplayer1 = (EditText)findViewById(R.id.agePlayer1);
        ageplayer2 = (EditText)findViewById(R.id.agePlayer2);
        ageplayer3 = (EditText)findViewById(R.id.agePlayer3);
        physic1  = (EditText)findViewById(R.id.notePlayer1);
        physic2  = (EditText)findViewById(R.id.notePlayer2);
        physic3  = (EditText)findViewById(R.id.notePlayer3);
        nameteam  = (EditText)findViewById(R.id.nameTeam);
        btnAjouter = (Button) findViewById(R.id.btnAdd);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        Bundle extras = getIntent().getExtras();
        final String Id = extras.getString("id");

        DatabaseReference ref1=FirebaseDatabase.getInstance().getReference().child("Equipe");
        ref1.orderByChild("idd").equalTo(Id).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    keys=datas.child("age1").getValue().toString();
                    keys1=datas.child("age2").getValue().toString();
                    keys2=datas.child("age3").getValue().toString();
                    keys3=datas.child("idd").getValue().toString();
                    keys4=datas.child("jou1").getValue().toString();
                    keys5=datas.child("jou2").getValue().toString();
                    keys6=datas.child("jou3").getValue().toString();
                    keys7=datas.child("mail").getValue().toString();
                    keys8=datas.child("Sname").getValue().toString();
                    keys9=datas.child("phy1").getValue().toString();
                    keys10=datas.child("phy2").getValue().toString();
                    keys11=datas.child("phy3").getValue().toString();

                    //Glide.with(context).load(key2).into(image2);
                    ageplayer1.setText(keys);
                    ageplayer2.setText(keys1);
                    ageplayer3.setText(keys2);
                    nameplayer1.setText(keys4);
                    nameplayer2.setText(keys5);
                    nameplayer3.setText(keys6);
                    physic1.setText(keys9);
                    physic2.setText(keys10);
                    physic3.setText(keys11);
                    nameteam.setText(keys8);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        if(Id.equals("a")){

        }
        else
            btnAjouter.setText("Update");
            btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email   = firebaseUser.getEmail();
                String SnamePlayer1 = nameplayer1.getText().toString();
                String SnamePlayer2 = nameplayer2.getText().toString();
                String SnamePlayer3 = nameplayer3.getText().toString();
                String SagePlayer1 = ageplayer1.getText().toString();
                String SagePlayer2 = ageplayer2.getText().toString();
                String SagePlayer3 = ageplayer3.getText().toString();
                String SnoteJoueur1 = physic1.getText().toString();
                String SnoteJoueur2 = physic2.getText().toString();
                String SnoteJoueur3 = physic3.getText().toString();
                String Sname = nameteam.getText().toString();

                if(SnamePlayer1.isEmpty() || SnamePlayer2.isEmpty() || SnamePlayer3.isEmpty() || SagePlayer1.isEmpty() || SagePlayer2.isEmpty() || SagePlayer3.isEmpty() || SnoteJoueur1.isEmpty() || SnoteJoueur2.isEmpty() || SnoteJoueur3.isEmpty() || Sname.isEmpty())
                    Toast.makeText(getApplicationContext(),"one field or more are empty ",Toast.LENGTH_SHORT).show();
                else {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Equipe");
                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Joueurs");
                    String id = databaseReference.push().getKey();
                    if(Id.equals("a")){
                        Team equipe1 = new Team(email,Sname,SnamePlayer1,SnamePlayer2,SnamePlayer3,SagePlayer1,SagePlayer2,SagePlayer3,SnoteJoueur1,SnoteJoueur2,SnoteJoueur3,id);
                        databaseReference.child(id).setValue(equipe1);
                        String aide = firebaseUser.getEmail().replace(".","");
                        Player joueur11 = new Player( firebaseUser.getEmail(),Sname,SnamePlayer1, SagePlayer1, SnoteJoueur1);
                        databaseReference1.child(aide+Sname+"1").setValue(joueur11);
                        Player joueur22 = new Player( firebaseUser.getEmail(),Sname,SnamePlayer2, SagePlayer2, SnoteJoueur2);
                        databaseReference1.child(aide+Sname+"2").setValue(joueur22);
                        Player joueur33 = new Player( firebaseUser.getEmail(),Sname,SnamePlayer3, SagePlayer3, SnoteJoueur3);
                        databaseReference1.child(aide+Sname+"3").setValue(joueur33);



                    }
                    else{
                        Team matche = new Team(email,Sname,SnamePlayer1,SnamePlayer2,SnamePlayer3,SagePlayer1,SagePlayer2,SagePlayer3,SnoteJoueur1,SnoteJoueur2,SnoteJoueur3,Id);
                        databaseReference.child(Id).setValue(matche);
                        String aide = firebaseUser.getEmail().replace(".","");
                        Player joueur11 = new Player( firebaseUser.getEmail(),Sname,SnamePlayer1, SagePlayer1, SnoteJoueur1);
                        databaseReference1.child(aide+Sname+"1").setValue(joueur11);
                        Player joueur22 = new Player( firebaseUser.getEmail(),Sname,SnamePlayer2, SagePlayer2, SnoteJoueur2);
                        databaseReference1.child(aide+Sname+"2").setValue(joueur22);
                        Player joueur33 = new Player( firebaseUser.getEmail(),Sname,SnamePlayer3, SagePlayer3, SnoteJoueur3);
                        databaseReference1.child(aide+Sname+"3").setValue(joueur33);
                    }
                    finish();
                    Intent in = new Intent(AddTeamPlayer.this, PrincipalMenu.class);
                    startActivity(in);
                    finish();
                    Toast.makeText(getApplicationContext(),"Registration Successful   ",Toast.LENGTH_SHORT).show();
                }
            }
        });





        }
}
