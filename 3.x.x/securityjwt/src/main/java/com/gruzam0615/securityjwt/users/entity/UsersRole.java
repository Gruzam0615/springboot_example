package com.gruzam0615.securityjwt.users.entity;

public enum UsersRole {

    CLIENT("client"),
    ADMIN("admin");

    private String role;
   
    UsersRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}