package com.hospital.frontend.model;

public class LoginResponse {
    private String token;
    private String role;
    private String nextPage;
    private String message;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getNextPage() { return nextPage; }
    public void setNextPage(String nextPage) { this.nextPage = nextPage; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
