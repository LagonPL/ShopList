package com.example.shoplist.shoplist;

public class User {

    private String key;
    private String nickname;

    public User(String key, String nickname) {
        this.key = key;
        this.nickname = nickname;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String title) {
        this.key = key;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
