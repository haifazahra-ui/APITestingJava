package com.example.APITesting.dto;

public class AuthResponse { //buat response login & resgister db
    private String message;
    private boolean success;
    private Object data;
    
    // Constructors
    public AuthResponse() {}
    
    public AuthResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
    
    public AuthResponse(String message, boolean success, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }
    
    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}