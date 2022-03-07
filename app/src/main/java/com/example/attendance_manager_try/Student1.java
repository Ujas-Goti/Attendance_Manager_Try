package com.example.attendance_manager_try;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Student1 extends AppCompatActivity {

    TextView studentEnroll;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Login_Model login_model;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        studentEnroll = findViewById(R.id.studentEnroll);
        login_model = new Login_Model();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("login_credentials");
        setUsername();
        databaseReference.child(login_model.getUsername()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    login_model.setUsername(snapshot.child("username").getValue().toString());
                    login_model.setPassword(snapshot.child("password").getValue().toString());
                    login_model.setRole(snapshot.child("role").getValue().toString());
                    login_model.setEnroll(snapshot.child("enroll").getValue().toString());
                    studentEnroll.setText(login_model.getEnroll());
                }catch (NullPointerException e) {
                    Toast.makeText(Student1.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setUsername() {
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        Intent intent = getIntent();
        login_model.setUsername(intent.getStringExtra("username"));
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        if(!sharedPreferences.getString("username", "").equals("")){
            String username = sharedPreferences.getString("username","");
            login_model.setUsername(username);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.logout:
                logout(); break;
            default:
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void logout() {
        Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username","");
        editor.putString("password"," ");
        editor.putString("role","");
        editor.apply();
        startActivity(new Intent(Student1.this,Login_Activity.class));
    }

}