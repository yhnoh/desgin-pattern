package com.example.singletonpattern;

public class App2 {
    public static void main(String[] args) {
        //실행환경 및 문맥 정보
        Runtime runtime = Runtime.getRuntime();

        System.out.println(runtime.maxMemory());
        System.out.println(runtime.freeMemory());
    }
}
