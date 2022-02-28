package com.example.attendance_manager_try;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    Login_Modal login_modal = new Login_Modal();

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

        login.setOnClickListener(view -> {
            String username = entered_Username.getText().toString();
            String password = entered_Password.getText().toString();

            firebaseDatabase  = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("login_credentials");
            databaseReference.child(username).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        int i = 0;
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            if (i == 0) {
                                login_modal.setPassword(dataSnapshot.getValue().toString());
                                i++;
                            } else if (i == 1) {
                                login_modal.setUsername(dataSnapshot.getValue().toString());
                            }
                        }
                    }
                    login_modal.authenticate(username,password,Login_Activity.this);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) { } });
        });

        singUp.setOnClickListener(view -> Toast.makeText(Login_Activity.this,"SignUp Pressed", Toast.LENGTH_SHORT).show());
    }
}