package com.example.firebaseexample.models;

public class Post {

    private String userID;
    private String name;
    private String author;

    public Post() {
    }

    public Post(String userID, String name, String author) {
        this.userID = userID;
        this.name = name;
        this.author = author;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}