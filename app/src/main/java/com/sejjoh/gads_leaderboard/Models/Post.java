package com.sejjoh.gads_leaderboard.Models;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("emailAddress")
    String emailAddress;
    @SerializedName("firstName")
    String firstName;
    @SerializedName("lastName")
    String lastName;
    @SerializedName("githubLink")
    String githubLink;

    public Post() {
    }

    public Post(String emailAddress, String firstName, String lastName, String githubLink) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.githubLink = githubLink;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    @Override
    public String toString() {
        return "Post{" +
                "emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", githubLink='" + githubLink + '\'' +
                '}';
    }
}
