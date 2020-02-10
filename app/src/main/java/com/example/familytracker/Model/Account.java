package com.example.familytracker.Model;

import java.util.List;

public class Account {
    private String username;
    private String password;
    private String adminEmail;
    private List<Member> members; //kan wss weg omdat je members altijd via controller aanvraag
    private int id;

    public Account(String username, String password, String adminEmail) {
        this.username = username;
        this.password = password;
        this.adminEmail = adminEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public int getId(){
        return  id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getAdminEmail(){return adminEmail;}

    public void setAdminEmail(String email){adminEmail = email;}
}