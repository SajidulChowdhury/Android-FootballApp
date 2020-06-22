package com.example.mehdi.footballapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {

    Context context;
    ArrayList<Team> equipes;

    public TeamAdapter(Context context, ArrayList<Team> equipes){
        this.context = context;
        this.equipes = equipes;
    }


    @NonNull
    @Override
    public TeamAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TeamAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_teams,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.nameteam.setText(equipes.get(i).getName());
        myViewHolder.namePlayer1.setText(equipes.get(i).getPlayer1());
        myViewHolder.namePlayer2.setText(equipes.get(i).getPlayer2());
        myViewHolder.namePlayer3.setText(equipes.get(i).getPlayer3());
        myViewHolder.agePlayer1.setText(equipes.get(i).getAge1());
        myViewHolder.agePlayer2.setText(equipes.get(i).getAge2());
        myViewHolder.agePlayer3.setText(equipes.get(i).getAge3());
        myViewHolder.notePlayer1.setText(equipes.get(i).getPhy1());
        myViewHolder.notePlayer3.setText(equipes.get(i).getPhy2());
        myViewHolder.notePlayer3.setText(equipes.get(i).getPhy3());
        myViewHolder.idd.setText(equipes.get(i).getIdd());

    }

    @Override
    public int getItemCount() {
        return equipes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameteam,namePlayer1,agePlayer1,notePlayer1,namePlayer2,agePlayer2,notePlayer2,namePlayer3,agePlayer3,notePlayer3,idd;
        Button btnRemove,btnModifier;
        FirebaseAuth firebaseAuth;
        FirebaseUser firebaseUser;
        String email,nomEq;
        //TextView idd;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firebaseAuth=FirebaseAuth.getInstance();
            firebaseUser=firebaseAuth.getCurrentUser();

            nameteam = (TextView) itemView.findViewById(R.id.nameTeam1);
            namePlayer1 = (TextView) itemView.findViewById(R.id.namePlayer11);
            namePlayer2 = (TextView) itemView.findViewById(R.id.namePlayer21);
            namePlayer3 = (TextView) itemView.findViewById(R.id.namePlayer31);
            agePlayer1 = (TextView) itemView.findViewById(R.id.agePlayer11);
            agePlayer2 = (TextView) itemView.findViewById(R.id.agePlayer21);
            agePlayer3 = (TextView) itemView.findViewById(R.id.agePlayer31);
            notePlayer1 = (TextView) itemView.findViewById(R.id.notePlayer11);
            notePlayer1 = (TextView) itemView.findViewById(R.id.notePlayer21);
            notePlayer1 = (TextView) itemView.findViewById(R.id.notePlayer31);
            idd         = (TextView) itemView.findViewById(R.id.idd);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove1);
            btnModifier = (Button) itemView.findViewById(R.id.btnModifier1);
           // idd = (TextView) itemView.findViewById(R.id.idd);
            btnModifier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context, AddTeamPlayer.class);
                    in.putExtra("id",idd.getText().toString());
                    context.startActivity(in);
                }
            });
            final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Joueurs");
            databaseReference1.orderByChild("email").equalTo(firebaseUser.getEmail()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot datas: dataSnapshot.getChildren()){
                        email = datas.child("email").getValue().toString();
                        nomEq = datas.child("nomeq").getValue().toString();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyDialogTheme);
                    builder.setTitle(R.string.app_name);
                    builder.setMessage("Are you sure to delete this team? ");
                    builder.setIcon(R.drawable.im5);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Equipe");
                            databaseReference.child(idd.getText().toString())
                                    .removeValue();

                            String aide = firebaseUser.getEmail().replace(".", "");

                            databaseReference1.child(aide + nomEq + "1").removeValue();
                            databaseReference1.child(aide + nomEq + "2").removeValue();
                            databaseReference1.child(aide + nomEq + "3").removeValue();

                            Intent in = new Intent(context, PrincipalMenu.class);
                            context.startActivity(in);


                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {


                        }
                    });
                }

                });




        }



    }





}
