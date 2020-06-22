package com.example.mehdi.footballapp;

public class Team {

  private  String mail,name,player1,player2,player3,age1,age2,age3,phy1,phy2,phy3,idd;

    public Team() {
    }


    public Team(String mail, String name, String player1, String player2, String player3, String age1, String age2, String age3, String phy1, String phy2, String phy3, String idd) {
        this.mail = mail;
        this.name = name;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.age1 = age1;
        this.age2 = age2;
        this.age3 = age3;
        this.phy1 = phy1;
        this.phy2 = phy2;
        this.phy3 = phy3;
        this.idd = idd;
    }

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

    public String getName() { return name; }

    public void setName(String nom) { this.name = name; }

    public String getPlayer1() { return player1; }

    public void setPlayer1(String player1) { this.player1 = player1; }

    public String getPlayer2() { return player2; }

    public void setPlayer2(String player2) { this.player2 = player2; }

    public String getPlayer3() { return player3; }

    public void setPlayer3(String player3) { this.player3 = player3; }

    public String getAge1() { return age1; }

    public void setAge1(String age1) { this.age1 = age1; }

    public String getAge2() { return age2; }

    public void setAge2(String age2) { this.age2 = age2; }

    public String getAge3() { return age3; }

    public void setAge3(String age3) { this.age3 = age3; }

    public String getPhy1() { return phy1; }

    public void setPhy1(String phy1) { this.phy1 = phy1; }

    public String getPhy2() { return phy2; }

    public void setPhy2(String phy2) { this.phy2 = phy2; }

    public String getPhy3() { return phy3; }

    public void setPhy3(String phy3) { this.phy3 = phy3; }

    public String getIdd() { return idd; }

    public void setIdd(String idd) { this.idd = idd; }
}
