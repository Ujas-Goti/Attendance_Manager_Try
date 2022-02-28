package com.example.attendance_manager_try;

public class Login_Modal {

    public String username;
    public String password;

    Login_Modal(){}
    Login_Modal(String username, String password){
        this.username = username;
        this.password = password;
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

    public Login_Modal getData(Login_Modal login_modal){
        return login_modal;
    }

//    public int authenticate(String username,String password){
//        if(this.username == username){
//             if(this.password == password)
//                return 1; //Authenticated
//             else
//                return 0; //Incorrect Password
//        }else
//             return -1; //Incorrect Username
//    }

}
