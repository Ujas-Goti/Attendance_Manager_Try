package com.example.attendance_manager_try;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    public Login_Modal login_Modal = new Login_Modal("Meet","123");
    public Login_Modal temp = new Login_Modal();
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entered_Username = findViewById(R.id.username);
        entered_Password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(view -> {
            String username = entered_Username.getText().toString();
            String password = entered_Password.getText().toString();

            firebaseDatabase  = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("login_credentials");
            databaseReference.child(username).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int i = 0;
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (i == 0) {
                            temp.setUsername(dataSnapshot.getValue().toString()); i++; Toast.makeText(Login_Activity.this, "Successful", Toast.LENGTH_SHORT).show(); }
                        else if (i == 1) {
                            temp.setPassword(dataSnapshot.getValue().toString());
                            Toast.makeText(Login_Activity.this, "Password Done", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) { } });
        });

    }
}