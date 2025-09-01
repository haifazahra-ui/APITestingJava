package com.example.APITesting.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest { //buat request register db
    @NotBlank
    @Size(min = 3, max = 50)
    private String username; //username ga boleh kosong
    
    @NotBlank
    @Size(max = 100)
    @Email
    private String email; //email ga boleh kosong dan harus valid email format
    
    @NotBlank
    @Size(min = 6, max = 100)
    private String password; //password ga boleh kosong dan minimal 6 karakter
    
    // Constructors
    public RegisterRequest() {} //default constructor
    
    public RegisterRequest(String username, String email, String password) { //parameterized constructor
        this.username = username; //this buat manggil variable diatas
        this.email = email; //this buat manggil variable diatas
        this.password = password; //this buat manggil variable diatas
        //getter & setter
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
