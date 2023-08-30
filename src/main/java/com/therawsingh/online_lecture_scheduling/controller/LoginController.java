package com.therawsingh.online_lecture_scheduling.controller;

import com.therawsingh.online_lecture_scheduling.entity.LoginDTO;
import com.therawsingh.online_lecture_scheduling.entity.User;
import com.therawsingh.online_lecture_scheduling.repository.UserRepository;
import com.therawsingh.online_lecture_scheduling.service.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    //@CrossOrigin({"http://localhost:5173/","http://localhost:5173/?", "http://localhost:5173"})
    //@PermitAll


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginDTO login){

        System.out.println("inside login");


        User user = userRepository.findByName(login.getUsername());
        Collection<GrantedAuthority> authorities;


        if(user== null){
            throw new UsernameNotFoundException("User not found");
        }
        else{
            String[] roles = user.getRole().split(",");
            authorities = new ArrayList<>();
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), authorities);
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            if(authentication.isAuthenticated()){
                return ResponseEntity.ok(authentication.getPrincipal());
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

        }
    }
}
