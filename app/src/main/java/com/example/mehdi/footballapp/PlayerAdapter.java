package com.example.mehdi.footballapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {

    Context context;
    ArrayList<Player> players;


    public PlayerAdapter(Context context, ArrayList<Player> players){
        this.context = context;
        this.players = players;
    }


    @NonNull
    @Override
    public PlayerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PlayerAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_players,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.Name.setText(players.get(i).getName());
        myViewHolder.Age.setText(players.get(i).getAge());
        myViewHolder.Physique.setText(players.get(i).getPhysique());
        myViewHolder.Team.setText(players.get(i).getNameq());

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name,Age,Physique,Team;
        FirebaseAuth firebaseAuth;
        FirebaseUser firebaseUser;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.name);
            Age = (TextView) itemView.findViewById(R.id.age);
            Physique  = (TextView) itemView.findViewById(R.id.physique);
            Team = (TextView) itemView.findViewById(R.id.team);


            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();

        }



    }





}
