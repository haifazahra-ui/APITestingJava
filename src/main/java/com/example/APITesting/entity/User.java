package com.example.APITesting.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity //GPA entity
@Table(name = "users") //nama table di db
public class User {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long id; //data type harus Long, int, Integer, long
    
    @NotBlank 
    @Size(max = 50) //maksimal length
    @Column(unique = true) //unique di db
    private String username; //field di db local variable di java cuma bisa digunkan di satu file ada juga global bisa buat semua gunanya buat privasi buat komuniaksi atara json dan butuh getter and setter buat komunikasi antara BE dan API
    
    @NotBlank
    @Size(max = 100)
    @Email
    @Column(unique = true)
    private String email;
    
    @NotBlank
    @Size(min = 6) //minimal length
    private String password;
    
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;
    
    // Constructors
    public User() {
        this.createdAt = java.time.LocalDateTime.now(); //set createdAt to current time
    }
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = java.time.LocalDateTime.now();
    }
    
    // Getters and Setters untuk mengambil dan menaruh data
    public Long getId() { return id; } //getter buat ngambil data
    public void setId(Long id) { this.id = id; } //setter buat naro data ke table/modified
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }
}
