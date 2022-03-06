package com.example.attendance_manager_try;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {

    EditText entered_Username;
    EditText entered_Password;
    CheckBox rememberMe;
    Button login;
    Button singUp;

    SharedPreferences sharedPreferences;

    Login_Model login_model = new Login_Model();

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entered_Username = findViewById(R.id.username);
        entered_Password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        singUp = findViewById(R.id.Signup);
        rememberMe = findViewById(R.id.Remember_Me);
        rememberMe.setChecked(false);

        login.setOnClickListener(view -> {
            String username = entered_Username.getText().toString().trim();
            String password = entered_Password.getText().toString().trim();
            Boolean remember = rememberMe.isChecked();

            firebaseDatabase  = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("login_credentials");
            databaseReference.child(username).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                            login_model.setUsername(snapshot.child("username").getValue().toString());
                            login_model.setPassword(snapshot.child("password").getValue().toString());
                            login_model.setRole(snapshot.child("role").getValue().toString());
                            login_model.setEnroll(snapshot.child("enroll").getValue().toString());
                        // Values Stored in Login Modal

                        if(remember) {
                            sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", login_model.getUsername());
                            editor.putString("password", login_model.getPassword());
                            editor.putString("role", login_model.getRole());
                            editor.putString("enroll", login_model.getEnroll());
                            editor.commit();
                        }
                        //Storing Values in Shared Preferences

                        int returnedValue = login_model.authenticate(username, password, Login_Activity.this);
                        if(returnedValue==1){
                            if (login_model.getRole().equals("F")) {
                                startActivity(new Intent(Login_Activity.this, Session_Create.class));
                                finish();
                            }
                            else if (login_model.getRole().equals("S")) {
                                startActivity(new Intent(Login_Activity.this, Student1.class).putExtra("username",login_model.getUsername()));
                                finish();
                            }
                        }

                    }else
                        Toast.makeText(Login_Activity.this, "Username Not Found!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) { } });
        });

        singUp.setOnClickListener(view -> {
            Toast.makeText(this, "SignUP", Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        if (!sharedPreferences.getString("username", "").equals("")) {
            login_model.role = sharedPreferences.getString("role", "");
            Toast.makeText(this, "Logged in Automatically", Toast.LENGTH_SHORT).show();
            if (login_model.getRole().equals("F")) {
                startActivity(new Intent(Login_Activity.this, Session_Create.class));
                finish();
            }
            else if (login_model.getRole().equals("S")) {
                startActivity(new Intent(Login_Activity.this, Student1.class));
                finish();
            }
        }
    }

}