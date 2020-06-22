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
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MyViewHolder> {

    Context context;
    ArrayList<Matche> matche;


    public MatchesAdapter(Context context,ArrayList<Matche> matche){
        this.context = context;
        this.matche = matche;
    }


    @NonNull
    @Override
    public MatchesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MatchesAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_matches,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.team1.setText(matche.get(i).getTeam1());
        myViewHolder.team2.setText(matche.get(i).getTeam2());
        myViewHolder.score1.setText(matche.get(i).getScore1());
        myViewHolder.score2.setText(matche.get(i).getScore2());
        myViewHolder.date.setText(matche.get(i).getDate());
        myViewHolder.Id.setText(matche.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return matche.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView team1,team2,score1,score2,date,Id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            team1 = (TextView) itemView.findViewById(R.id.team1);
            team2 = (TextView) itemView.findViewById(R.id.team2);
            score1  = (TextView) itemView.findViewById(R.id.score1);
            score2  = (TextView) itemView.findViewById(R.id.score2);
            date = (TextView) itemView.findViewById(R.id.date);
            Id = (TextView)itemView.findViewById(R.id.idd);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.MyDialogTheme);
                    builder.setTitle(R.string.app_name);
                    builder.setMessage("Choose your next action ?");
                    builder.setIcon(R.drawable.im5);
                    builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent in = new Intent(context, AddMatche.class);
                            in.putExtra("id",Id.getText().toString());
                            context.startActivity(in);
                           //((Matches)context).finish();


                        }
                    });
                    builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Matche");
                            databaseReference.child(Id.getText().toString())
                                    .removeValue();
                            Intent in = new Intent(context, PrincipalMenu.class);
                            context.startActivity(in);

                        }
                    });
                    builder.setCancelable(true);
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });
        }



    }





}
