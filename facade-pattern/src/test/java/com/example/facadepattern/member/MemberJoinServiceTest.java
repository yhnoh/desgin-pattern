package com.example.facadepattern.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberJoinServiceTest {

    @Autowired
    MemberJoinService memberJoinService;

    @Test
    public void joinTest(){
        memberJoinService.join("id3", "password3");
    }
}