package com.example.attendance_manager_try;

public class Session_Model {
    String semester;
    String subject;
    String session_Name;
    String startTime;
    int duration;

    Session_Model(){}

    Session_Model(String session_Name,String semester,String subject,String startTime,int duration){
        this.session_Name = session_Name;
        this.semester = semester;
        this.subject = subject;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSession_Name() {
        return session_Name;
    }

    public void setSession_Name(String session_Name) {
        this.session_Name = session_Name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration( int duration) {
        this.duration = duration;
    }
}
