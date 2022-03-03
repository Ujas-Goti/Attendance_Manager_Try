package com.example.attendance_manager_try;

import android.content.Context;
import android.widget.Toast;

public class Login_Model {

    public String username;
    public String password;
    public String role;
    public String enroll;

    public String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    Login_Model() {
    }

    Login_Model(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getUsername() {
        return username.toString();
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password.toString();
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Login_Model getData(Login_Model login_model) { return login_model; }

    public int authenticate(String username, String password, Context context) {

        if (this.username.equals(username)) {
            if (this.password.equals(password)) {
                Toast.makeText(context, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                return 1;
            } else {
                Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show();
                return 0;
            }
        } return -1;
    }
}