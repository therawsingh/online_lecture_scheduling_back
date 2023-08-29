package com.therawsingh.online_lecture_scheduling.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(){

        System.out.println("inside home");

        return "Home Page";

    }

}
