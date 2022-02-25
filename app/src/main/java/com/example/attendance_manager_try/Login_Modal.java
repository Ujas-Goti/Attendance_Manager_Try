package com.example.attendance_manager_try;

import android.util.Log;
import android.widget.Toast;

public class Login_Modal {

    private String username;
    private String password;

    Login_Modal(){}
    Login_Modal(String username, String password){
        this.username = username;
        this.password = password;
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

    public int authenticate(String username,String password){
        if(this.username == username){
             if(this.password == password)
                return 1; //Authenticated
             else
                return 0; //Incorrect Password
        }else
             return -1; //Incorrect Username
    }


}
