package com.hospital.frontend.model;

public class StaffRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role;
    private String position;
    private Integer ssn;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public Integer getSsn() { return ssn; }
    public void setSsn(Integer ssn) { this.ssn = ssn; }
}