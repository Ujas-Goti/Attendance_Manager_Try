package com.example.attendance_manager_try;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class Student1 extends AppCompatActivity {
    TextView stenroll;
    DatabaseReference d1;
    Login_Model login_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student1);

       /* d1=FirebaseDatabase.getInstance().getReference().child("login_credentials");*/
        Toast.makeText(this,"hii", Toast.LENGTH_SHORT).show();
        /*d1.child(login_model.getUsername()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                *//*stenroll.setText(snapshot.child("enroll").toString());*//*
                Toast.makeText(Student1.this, "Hii", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }

   /* protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        if (!sharedPreferences.getString("username", "not").equals("not")) {
            login_model.username = sharedPreferences.getString("username", "not");
            login_model.password = sharedPreferences.getString("password", "not");
            login_model.role = sharedPreferences.getString("role", "not");
            login_model.enroll = sharedPreferences.getString("enroll", "not");
            Toast.makeText(this, "Logged in Automatically", Toast.LENGTH_SHORT).show();

        }
    }*/
}