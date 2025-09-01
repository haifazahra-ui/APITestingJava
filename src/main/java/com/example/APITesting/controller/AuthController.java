package com.example.APITesting.controller; 

import com.example.APITesting.dto.AuthResponse;
import com.example.APITesting.dto.LoginRequest;
import com.example.APITesting.dto.RegisterRequest;
import com.example.APITesting.entity.User;
import com.example.APITesting.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController // rest API controller
@RequestMapping("/api/auth") //base url path buat ngetest di postman
@CrossOrigin(origins = "*", maxAge = 3600) //cors configuration library buat ke FE
public class AuthController { 
    
    @Autowired // dependency injection (library spring buat manggil class lain)
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) { //validasi request body
        try {
            User user = userService.registerUser(registerRequest); //panggil method registerUser di UserService
            
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getId());
            userData.put("username", user.getUsername());
            userData.put("email", user.getEmail());
            userData.put("createdAt", user.getCreatedAt());
            // struksur data user yang mau dikirim ke postman
            return ResponseEntity.ok(
                new AuthResponse("User registered successfully!", true, userData)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new AuthResponse(e.getMessage(), false));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.loginUser(loginRequest);
            
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getId());
            userData.put("username", user.getUsername());
            userData.put("email", user.getEmail());
            
            return ResponseEntity.ok(
                new AuthResponse("Login successful!", true, userData)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new AuthResponse(e.getMessage(), false));
        }
    }
    
    @GetMapping("/users") //specific id kalau id banyak 
    public ResponseEntity<AuthResponse> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(
                new AuthResponse("Users retrieved successfully!", true, users)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new AuthResponse("Error retrieving users", false));
        }
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<AuthResponse> getUserById(@PathVariable Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            if (user.isPresent()) {
                return ResponseEntity.ok(
                    new AuthResponse("User found!", true, user.get())
                );
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new AuthResponse("Error retrieving user", false));
        }
    }
    
    @PutMapping("/users/{id}") //update user by id
    public ResponseEntity<AuthResponse> updateUser(@PathVariable Long id, 
                                                 @Valid @RequestBody RegisterRequest updateRequest) {
        try {
            User updatedUser = userService.updateUser(id, updateRequest);
            
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", updatedUser.getId());
            userData.put("username", updatedUser.getUsername());
            userData.put("email", updatedUser.getEmail());
            userData.put("createdAt", updatedUser.getCreatedAt());
            
            return ResponseEntity.ok(
                new AuthResponse("User updated successfully!", true, userData)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new AuthResponse(e.getMessage(), false));
        }
    }
}