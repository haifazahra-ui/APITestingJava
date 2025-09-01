package com.example.APITesting.service;

import com.example.APITesting.dto.LoginRequest;
import com.example.APITesting.dto.RegisterRequest;
import com.example.APITesting.entity.User;
import com.example.APITesting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //spring service component
public class UserService { //buat logic register & login db
    @Autowired // dependency injection (library spring buat manggil class lain)
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder; //bycrypt password encoder
    
    public User registerUser(RegisterRequest registerRequest) { //register user
        // Check if username exists
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username is already taken!"); // buat nampilin error
        } 
        
        // Check if email exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email is already in use!"); 
        }
        
        // Create new user
        User user = new User(
            registerRequest.getUsername(), 
            registerRequest.getEmail(),
            passwordEncoder.encode(registerRequest.getPassword())
        ); // encode password biar ga plain text
        
        return userRepository.save(user); //simpan user ke db
    }
    
    public User loginUser(LoginRequest loginRequest) { //login user
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found!"); //kalau user ga ada di db
        }
        
        User user = userOptional.get();
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password!"); //kalau password ga sesuai
        }
        
        return user;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll(); //ngambil semua user di db
    }
    
    public Optional<User> getUserById(Long id) { //ngambil user by id
        return userRepository.findById(id);
    }
    
    public User updateUser(Long id, RegisterRequest updateRequest) { //update user by id
        Optional<User> userOptional = userRepository.findById(id); //cari user by id
        
        if (userOptional.isEmpty()) { 
            throw new RuntimeException("User not found!"); //kalau user ga ada di db
        }
        
        User user = userOptional.get(); //ngambil user dari optional
        // Check if new username is taken by another user
        
        // Update fields
        user.setUsername(updateRequest.getUsername()); //update username
        user.setEmail(updateRequest.getEmail()); //update email
        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) { //update password kalau ga kosong
            user.setPassword(passwordEncoder.encode(updateRequest.getPassword())); //encode password biar ga plain text
        }
        
        return userRepository.save(user); //simpan user ke db
    }
}