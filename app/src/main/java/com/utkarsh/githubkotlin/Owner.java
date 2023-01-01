package com.utkarsh.githubkotlin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("avatar_url")
    @Expose
    String avatar_url;
    @SerializedName("login")
    @Expose
    String login;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Owner(String avatar_url, String login) {
        this.avatar_url = avatar_url;
        this.login = login;
    }
}
