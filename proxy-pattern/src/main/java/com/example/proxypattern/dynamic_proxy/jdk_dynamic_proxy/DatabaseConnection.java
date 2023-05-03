package com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy;

public class DatabaseConnection {

    public void connect() {
        System.out.println("데이터 베이스에 연결 되었습니다.");
    }

    public void close() {
        System.out.println("데이터 베이스 연결 종료되었습니다.");
    }
}
