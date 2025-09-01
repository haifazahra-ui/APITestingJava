package com.example.APITesting.config; //rules dari rules developingnya

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // buat config class
@EnableWebSecurity //spring security web
public class SecurityConfig {
    
    @Bean //spring bean definition buat create manage sama control (memory management)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //security filter chain configuration
        return http
            .csrf().disable() //disable csrf (cross site request forgery) buat security jadi kalo masih mod dev dimatiin dulu biar bisa test di postman
            .cors().disable() //disable cors (cross origin resource sharing) biar bisa test di postman
            .authorizeHttpRequests()
            .anyRequest().permitAll() //permit all request (semua request diijinin)
            .and()
            .build();
    }
}