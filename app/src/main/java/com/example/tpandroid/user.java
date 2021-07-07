package com.example.tpandroid;

public class user {
    private String Phone;
    private String Email;

    private  String Name;
    private String LastName;
    public user() {
    }


    public user(String name, String lastName, String email, String phone ) {
        this.Name=name;
        this.LastName=lastName;
        this.Email=email;
        this.Phone=phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



}
