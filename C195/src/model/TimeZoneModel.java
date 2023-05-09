package model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZonedDateTime;

/** Class to have a Constructor and Getters and Setters to convert time to UTC. */
public class TimeZoneModel {
    private Timestamp start ;
    private int appointmentID;
    private LocalDate date;
    private LocalTime timeHour;
    private LocalTime time;


    public TimeZoneModel(int appointmentID, Timestamp start) {
        this.appointmentID = appointmentID;
        this.start= start;
    }

    public TimeZoneModel(LocalDate date, LocalTime timeHour, LocalTime time) {
        this.date = date;
        this.timeHour = timeHour;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(LocalTime timeHour) {
        this.timeHour = timeHour;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }



    public TimeZoneModel(Timestamp start) {
        this.start= start;
    }


    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

}
