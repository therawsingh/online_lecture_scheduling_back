package com.therawsingh.online_lecture_scheduling.configuration;

import com.therawsingh.online_lecture_scheduling.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;

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
    AuthenticationManager authenticationManager(){
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }

    @Bean
    AuthenticationManagerResolver authenticationManagerResolver(){

        return new AuthenticationManagerResolver() {
            @Override
            public AuthenticationManager resolve(Object context) {
                return null;
            }
        };

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173/","http://localhost:5173/?", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    //.cors(Customizer.withDefaults())
    //auth.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{

        return http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {

                auth.requestMatchers("/addInstructor").hasRole("ADMIN");
                auth.requestMatchers("/course/**").hasRole("ADMIN");
                auth.requestMatchers("/lecture/assignLecture/**").hasRole("ADMIN");
                auth.requestMatchers("/lecture/getLectures/**").hasRole("LECTURER");
                auth.requestMatchers("/home").permitAll();
                auth.requestMatchers("/user","/addUser").permitAll();
                auth.requestMatchers("/login").permitAll();
                auth.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated();})

                .formLogin(formLogin -> {
                    formLogin.loginPage("http://localhost:5173/");
                    formLogin.loginProcessingUrl("/login");
                    formLogin.defaultSuccessUrl("/home", true);
                })

                .logout(logout -> logout.permitAll())
                .build();
    }
}


