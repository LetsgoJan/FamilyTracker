package com.example.myapplication.data.model;

import java.util.Date;

public class Member {
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String profilePicture;
    private String city;
    private String streetAndNumber;
    private String zipCode;

    public Member(String firstName,
                  String lastName,
                  Date birthDay,
                  String profilePicture,
                  String city,
                  String streetAndNumber,
                  String zipCode)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.profilePicture = profilePicture;
        this.city = city;
        this.streetAndNumber = streetAndNumber;
        this.zipCode = zipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
