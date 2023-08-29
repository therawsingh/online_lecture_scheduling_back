package com.therawsingh.online_lecture_scheduling.service;

import com.therawsingh.online_lecture_scheduling.entity.User;
import com.therawsingh.online_lecture_scheduling.impl.UserDetailsImpl;
import com.therawsingh.online_lecture_scheduling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user =  userRepository.findByName(username);

        return user.map(UserDetailsImpl::new).orElseThrow(()-> new UsernameNotFoundException("User not found " + username));

    }
}
