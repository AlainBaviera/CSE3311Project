package com.example.cse3311project;

public class User {
    String email, password, username, profession, utaid;

    public User(){

    }

    public User(String email, String password, String username, String profession, String utaid) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.profession = profession;
        this.utaid = utaid;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getUtaid() {
        return utaid;
    }

    public void setUtaid(String utaid) {
        this.utaid = utaid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
