package com.example.pawfect_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.Authentication;

import java.security.Principal;

@SpringBootApplication
public class PawfectProjectApplication {
//
//    @Autowired
//    private EmailSenderService service;

    public static void main(String[] args) {
        SpringApplication.run(PawfectProjectApplication.class, args);
    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerMail(){
//        service.sendSimpleEmail("asmitakatel444@gmail.com","hI","tHIS IS EMAIL");
//    }
//


}
