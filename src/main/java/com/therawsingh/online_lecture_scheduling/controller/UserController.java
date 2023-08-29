package com.therawsingh.online_lecture_scheduling.controller;

import com.therawsingh.online_lecture_scheduling.entity.User;
import com.therawsingh.online_lecture_scheduling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public String userPage(){

        return "User Page";

    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ("User successfully added");
    }

    @GetMapping("/getRole/{id}")
    public String getRole(@PathVariable long id){

        return userRepository.findById(id).getRole();

    }

}
