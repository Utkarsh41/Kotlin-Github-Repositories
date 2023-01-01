package com.utkarsh.githubkotlin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguageItems {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("stargazers_count")
    @Expose
    private int  stargazers_count;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("forks_count")
    @Expose
    int forks_count;

    @SerializedName("owner")
    @Expose
    Owner owner;



    public LanguageItems(String name, int stargazers_count, String description, int forks_count,Owner owner) {
        this.name = name;
        this.stargazers_count = stargazers_count;
        this.description = description;
        this.forks_count = forks_count;
        this.owner = owner;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }
}
