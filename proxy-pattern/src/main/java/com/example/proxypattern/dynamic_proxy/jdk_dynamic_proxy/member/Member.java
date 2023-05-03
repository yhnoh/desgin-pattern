package com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.member;

public class Member {

    private long id;
    private String username;

    public Member(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
