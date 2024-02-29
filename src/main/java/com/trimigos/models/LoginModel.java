package com.trimigos.models;

public class LoginModel {
    // Add your login logic here
    public boolean login(String username, String password) {
        // Dummy logic
        return username.equals("admin") && password.equals("admin");
    }
}
