package com.example.APITesting.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest { //buat request login db
    @NotBlank
    private String username; //username ga boleh kosong
    
    @NotBlank
    private String password; //password ga boleh kosong
    
    // Constructors
    public LoginRequest() {} 
    
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}