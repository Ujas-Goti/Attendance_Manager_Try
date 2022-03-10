package com.example.attendance_manager_try;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_Attendance extends AppCompatActivity {

    DatabaseReference databaseReference;
    ArrayList<String>  spinnerArray;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_session);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Attendance_Temp");
        spinner = findViewById(R.id.Session_Spinner);
        spinnerArray = new ArrayList<>();
        setSpinnerData();

        Spinner spinner = findViewById(R.id.Session_Spinner);
        RecyclerView recyclerView = findViewById(R.id.sessionRecyclerView);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { } });
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
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                Login_Model.clearData(this, sharedPreferences);
                startActivity(new Intent(View_Attendance.this,Login_Activity.class));
                break;
            default:
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void setSpinnerData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinnerArray.add("------------");
                for (DataSnapshot data: snapshot.getChildren()) {
                    spinnerArray.add(data.getKey());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(View_Attendance.this,android.R.layout.simple_spinner_item, spinnerArray);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { } });
    }
}