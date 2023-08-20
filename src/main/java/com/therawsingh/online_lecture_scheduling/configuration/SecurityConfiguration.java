package com.therawsingh.online_lecture_scheduling.configuration;

import com.therawsingh.online_lecture_scheduling.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return new UserService();

    }

    @Bean
    UserConte

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> {
                auth.requestMatchers("/instructor/**").authenticated().anyRequest().hasRole("ROLE_ADMIN");
                auth.requestMatchers("/course/**").authenticated().anyRequest().hasRole("ROLE_ADMIN");
                auth.requestMatchers("/lecture/assignLecture/**").authenticated().anyRequest().hasRole("ROLE_ADMIN");
            auth.requestMatchers("/lecture/getLectures/**").authenticated().anyRequest().hasRole("ROLE_LECTURER");
                auth.requestMatchers("/user/**").permitAll();}).formLogin().and().build();
    }

}


