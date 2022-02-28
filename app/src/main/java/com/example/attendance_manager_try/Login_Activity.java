package com.example.attendance_manager_try;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

    EditText entered_Username;
    EditText entered_Password;

    Button login;
    Button singUp;
    Login_Modal login_Modal = new Login_Modal();
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String real_Username;
    String real_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entered_Username = findViewById(R.id.username);
        entered_Password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        singUp = findViewById(R.id.Signup);

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
                            real_Password = dataSnapshot.getValue().toString(); i++;
                        }
                        else if (i == 1) {
                           real_Username = dataSnapshot.getValue().toString();
                            Toast.makeText(Login_Activity.this, real_Password, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) { } });
        });


        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login_Activity.this, real_Username, Toast.LENGTH_SHORT).show();

            }
        });


    }
}