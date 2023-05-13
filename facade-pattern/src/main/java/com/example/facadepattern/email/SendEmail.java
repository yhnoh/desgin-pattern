package com.example.facadepattern.email;

import org.springframework.stereotype.Service;

@Service
public class SendEmail {

    public void sendEmail(String message){
        System.out.println(message);
    }
}
