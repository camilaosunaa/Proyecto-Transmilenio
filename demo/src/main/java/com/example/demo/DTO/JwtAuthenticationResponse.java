package com.example.demo.DTO;

import com.example.demo.modelo.Rol;

public class JwtAuthenticationResponse {
    private String token;
    private String email;
    private Rol role;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String token, String email, Rol role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }
}
