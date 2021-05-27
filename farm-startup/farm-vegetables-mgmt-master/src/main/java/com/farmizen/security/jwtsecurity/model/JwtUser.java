package com.farmizen.security.jwtsecurity.model;

public class JwtUser {
    private String userName;
    private String id;
    private String role;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
