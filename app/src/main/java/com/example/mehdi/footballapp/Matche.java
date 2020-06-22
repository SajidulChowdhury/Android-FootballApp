package com.example.mehdi.footballapp;

public class Matche {
    public String id,team1, team2, score1, score2, email, date  ;

    public Matche(){

    }

    public Matche(String id,String team1, String team2,String score1, String score2, String email, String date) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.score1  = score1;
        this.score2  = score2;
        this.email   = email;
        this.date    = date;

    }

    public String getTeam1() { return team1; }

    public void setTeam1(String team1) { this.team1 = team1; }

    public String getTeam2() { return team2; }

    public void setTeam2(String team2) { this.team2 = team2; }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
