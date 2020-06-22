package com.example.mehdi.footballapp;

public class Player {
    String email,nameq,name,age,physique;

    public Player() {
    }

    public Player(String email, String nameq, String name, String age, String physique) {
        this.email=email;
        this.nameq= nameq;
        this.name = name;
        this.age = age;
        this.physique = physique;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhysique() {
        return physique;
    }

    public void setPhysique(String physique) {
        this.physique = physique;
    }

    public String getNameq() {
        return nameq;
    }

    public void setNameq(String nameq) {
        this.nameq = nameq;
    }
}
