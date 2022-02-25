package com.example.attendance_manager_try;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SharedElementCallback;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {

    private EditText entered_Username;
    private EditText entered_Password;
    private Button login;
    private Login_Modal login_Modal;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entered_Username = findViewById(R.id.username);
        entered_Password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(view -> { // Login Button Listener
            int isAuthenticated = 2;
            String username = entered_Username.getText().toString();
            String password = entered_Password.getText().toString();

         try {
             firebaseDatabase  = FirebaseDatabase.getInstance();
             databaseReference = firebaseDatabase.getReference().child("login_credentials").child(username);
             databaseReference.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
//                     login_Modal = snapshot.getValue(Login_Modal.class);
                     Toast.makeText(getApplicationContext(), (String) snapshot.child("username").getValue(), Toast.LENGTH_SHORT).show();
//                     Toast.makeText(Login_Activity.this, login_Modal.getUsername(), Toast.LENGTH_SHORT).show();
                 }
                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {

                 }
             });
             Toast.makeText(this, login_Modal.getUsername(), Toast.LENGTH_SHORT).show();
         }catch (Exception ex) {
             Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
         }
        });
    }


}