package com.example.mehdi.footballapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddMatche extends AppCompatActivity {
    private EditText nameTeam1, nameTeam2, score1, score2;
    private Button btnAdd,btnChoose;
    private TextView date;
    private Calendar c;
    private DatePickerDialog dpd;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private String keys,keys1,keys2,keys3,keys4,keys5,keys6;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matches);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nameTeam1 = (EditText)findViewById(R.id.nomTeam1);
        nameTeam2 = (EditText)findViewById(R.id.nomTeam2);
        score1     = (EditText) findViewById(R.id.scoreTeam1);
        score2     = (EditText) findViewById(R.id.scoreTeam2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnChoose = (Button) findViewById(R.id.btnchoose);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        date = (TextView)findViewById(R.id.date) ;

        Bundle extras = getIntent().getExtras();
        final String Id = extras.getString("id");
        DatabaseReference ref1=FirebaseDatabase.getInstance().getReference().child("Matche");
        ref1.orderByChild("id").equalTo(Id).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    keys=datas.child("equipe1").getValue().toString();
                    keys1=datas.child("equipe2").getValue().toString();
                    keys2=datas.child("score1").getValue().toString();
                    keys3=datas.child("score2").getValue().toString();
                    keys4=datas.child("email").getValue().toString();
                    keys5=datas.child("date").getValue().toString();
                    keys6 = datas.child("id").getValue().toString();
                    nameTeam1.setText(keys);
                    nameTeam2.setText(keys1);
                    score1.setText(keys2);
                    score2.setText(keys3);
                    date.setText(keys5);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        if(Id.equals("a")){

        }
        else
            btnAdd.setText("Modifier");
            btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email   = firebaseUser.getEmail();
                String namet1 = nameTeam1.getText().toString();
                String namet2 = nameTeam2.getText().toString();
                String scr1    = score1.getText().toString();
                String scr2    = score2.getText().toString();
                String date1   = date.getText().toString();
                if(namet1.isEmpty() || namet2.isEmpty() || scr1.isEmpty() || scr2.isEmpty() || date1.isEmpty())
                    Toast.makeText(getApplicationContext(),"One Field or more Are Empty",Toast.LENGTH_SHORT).show();
                else {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Matche");
                    String id = databaseReference.push().getKey();
                    if(Id.equals("a")){
                    Matche matche = new Matche(id,namet1,namet2,scr1,scr2,email,date1);
                    databaseReference.child(id).setValue(matche);}
                    else{
                        Matche matche = new Matche(keys6,namet1,namet2,scr1,scr2,email,date1);
                        databaseReference.child(keys6).setValue(matche);
                    }
                    finish();
                    Intent in = new Intent(AddMatche.this, PrincipalMenu.class);
                    startActivity(in);
                    Toast.makeText(getApplicationContext(),"Registration Successful  ",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(AddMatche.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText((month+1)+"/"+dayOfMonth+"/"+year);
                    }
                },day,month,year);
                dpd.show();
                }
        });




    }


}
