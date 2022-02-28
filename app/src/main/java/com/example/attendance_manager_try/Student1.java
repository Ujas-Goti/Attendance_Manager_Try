package com.example.attendance_manager_try;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Student1 extends AppCompatActivity {
    EditText stenroll;
    DatabaseReference d1;
    Login_Modal login_modal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student1);

        d1= FirebaseDatabase.getInstance().getReference().child("login_credentials");
        d1.child(login_modal.getUsername()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stenroll.setText(snapshot.child("enroll").toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        if (!sharedPreferences.getString("username", "not").equals("not")) {
            login_modal.username = sharedPreferences.getString("username", "not");
            login_modal.password = sharedPreferences.getString("password", "not");
            login_modal.role = sharedPreferences.getString("role", "not");
            login_modal.enroll = sharedPreferences.getString("enroll", "not");
            Toast.makeText(this, "Logged in Automatically", Toast.LENGTH_SHORT).show();

        }
    }
}