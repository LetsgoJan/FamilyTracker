package com.example.myapplication.data.model;

import java.util.List;

public class Account {
    private String Username;
    private String Password;
    private List<Member> members; //kan wss weg omdat je members altijd via controller aanvraag
    private int id;

    public Account(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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
}
