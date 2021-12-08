package com.assignment.ffbf;

public class UserModel {

    String name, email, username, password, uid, role;

    public UserModel() {
    }

    public UserModel(String name, String email, String username, String password, String uid, String role) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.uid = uid;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
