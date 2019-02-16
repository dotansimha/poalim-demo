package com.graphqljava.poalim.poalimdemo.entities;

public class User {
    private String id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;

    public User(String id, String email, String username, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }
}
