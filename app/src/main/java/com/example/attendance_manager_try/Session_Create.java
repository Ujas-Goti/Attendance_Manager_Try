package com.example.attendance_manager_try;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class Session_Create extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    TextView time_Show;
    Button time_Set;
    Button submitSession;
    EditText sessionName;
    EditText duration;
    Spinner semester;
    Spinner subject;

    Session_Model session_model;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    int day,month,year,hour,minute;
    int final_day,final_month,final_year,final_hour,final_minute;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_create);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        time_Set = findViewById(R.id.TimeSetter);
        time_Show = findViewById(R.id.time_Shower);
        sessionName = findViewById(R.id.Session_Name);
        duration = findViewById(R.id.Duration);
        semester = findViewById(R.id.Semester);
        subject = findViewById(R.id.Subject);

        time_set();

        submitSession = findViewById(R.id.Submit_Session);
        submitSession.setOnClickListener(view -> {
            String SessionStr = semester.getSelectedItem().toString().replace("Sem-","")+ "_" + subject.getSelectedItem().toString();
            String Semester = semester.getSelectedItem().toString().replace("Sem-","");
            String Subject = subject.getSelectedItem().toString();
            String StartTime = "5";
            int Duration = Integer.parseInt(duration.getText().toString()) * 60; //in Seconds

            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("Attendance_Session");
            session_model = new Session_Model
                    (sessionName.getText().toString(),
                            Semester,
                            Subject,
                            StartTime,
                            Duration);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseReference.child(SessionStr).setValue(session_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Session_Create.this, "Session Created Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) { }});
        });

        time_Set.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(Session_Create.this,Session_Create.this,year,month,day);
            datePickerDialog.show();
        });
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
            sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username","");
            editor.putString("password"," ");
            editor.putString("role","");
            editor.putString("enroll","");
            editor.commit();
            startActivity(new Intent(Session_Create.this,Login_Activity.class));
    }

    public void time_set() {
        time_Show.setText("Starts At :" + Calendar.HOUR_OF_DAY+ ":" + Calendar.MINUTE);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        final_year = i;
        final_month = i1;
        final_day = i2;

        Calendar cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(Session_Create.this,Session_Create.this,hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        final_hour = i;
        final_minute = i1;
        time_Show.setText("Starts At : " + final_hour + ":" + final_minute);
    }
}