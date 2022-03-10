package com.example.attendance_manager_try;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Login_Model {

    public String username;
    public String password;
    public String role;
    public String enroll;

    Login_Model() {
    }

    public Login_Model(String username, String password, String role, String enroll) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enroll = enroll;
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
    public String getEnroll() {
        return enroll;
    }
    public void setEnroll(String enroll) {
        this.enroll = enroll;
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

    public static void clearData(Context context,SharedPreferences sharedPreferences) {
        Toast.makeText(context, "Logged Out", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username","");
        editor.putString("password"," ");
        editor.putString("role","");
        editor.putString("enroll","");
        editor.putString("temp_username","");
        editor.putString("temp_password"," ");
        editor.putString("temp_role","");
        editor.putString("temp_enroll","");
        editor.apply();
    }
}