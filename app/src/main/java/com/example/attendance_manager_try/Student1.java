package com.example.attendance_manager_try;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class Student1 extends AppCompatActivity {

    TextView studentEnroll;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    SessionAdapter adapter;
    ArrayList<Session_Model> arrayList;

    Login_Model login_model;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Attendance_Session");

        recyclerView=findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();
        adapter = new SessionAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Session_Model session_model = dataSnapshot.getValue(Session_Model.class);
                    arrayList.add(session_model);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { } });
        studentEnroll = findViewById(R.id.studentEnroll);
        login_model = new Login_Model();
        setUsername();
        studentEnroll.setText(login_model.getEnroll());
    }

    private void setUsername() {
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        if (!sharedPreferences.getString("temp_username", "").equals("")) {
            login_model.setUsername(sharedPreferences.getString("temp_username",""));
            login_model.setPassword(sharedPreferences.getString("temp_password",""));
            login_model.setRole(sharedPreferences.getString("temp_role",""));
            login_model.setEnroll(sharedPreferences.getString("temp_enroll",""));
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
                sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                Login_Model.clearData(this,sharedPreferences);
                startActivity(new Intent(Student1.this,Login_Activity.class));
                break;
            default:
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}