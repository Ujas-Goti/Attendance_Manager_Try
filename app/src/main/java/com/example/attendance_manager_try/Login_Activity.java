package com.example.attendance_manager_try;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        login.setOnClickListener(view -> { // Login Listener
            String username = entered_Username.getText().toString();
            String password = entered_Password.getText().toString();
            if(username.equals("") || password.equals("")){
                Toast.makeText(this, "Please Fill All the Feilds", Toast.LENGTH_SHORT).show();
            }
            login_Modal = new Login_Modal(username,password);

            firebaseDatabase  = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("login_credentials");
            Boolean is_Correct = authenticate_Data(login_Modal);
        });
    }

    private Boolean authenticate_Data(Login_Modal login_modal) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap: snapshot.getChildren()) {
                    if()
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return false;
    }


}