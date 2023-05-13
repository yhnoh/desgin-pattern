package com.example.facadepattern.member;

public class Member {

    private String id;
    private String password;

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
