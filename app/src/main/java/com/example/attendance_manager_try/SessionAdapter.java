package com.example.attendance_manager_try;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.MyViewHolder>{

    ArrayList<Session_Model> arrayList;
    Context context;

    public SessionAdapter(ArrayList<Session_Model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Session_Model session_model = arrayList.get(position);
        holder.subjectName.setText(session_model.getSubject());
        holder.startTime.setText(session_model.getStartTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(context).inflate(R.layout.session,parent,false);
        return new MyViewHolder(v);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView subjectName;
        TextView startTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subject_name);
            startTime = itemView.findViewById(R.id.st_time);
        }
    }
}