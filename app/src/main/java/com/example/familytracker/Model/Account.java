package com.example.familytracker.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Account implements Parcelable {
    private String username;
    private String password;
    private String adminEmail;
    private ArrayList<Member> members;
    private int id;

    public Account(String username, String password, String adminEmail) {
        this.username = username;
        this.password = password;
        this.adminEmail = adminEmail;
    }

    public Account(Parcel parcel) {
        this.username = parcel.readString();
        this.password = parcel.readString();
        this.adminEmail = parcel.readString();
        this.members = parcel.readArrayList(ClassLoader.getSystemClassLoader());
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

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(adminEmail);
        dest.writeList(members);

    }
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        public Account createFromParcel(Parcel in) {

            return new Account(in);
        }

        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}