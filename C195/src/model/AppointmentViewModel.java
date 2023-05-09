package model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/** Class to have Constructors and Getters and Setters to view appointments by week or month. */
public class AppointmentViewModel {

    private int Week;
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Contact_Name;
    private String Type;
    private LocalDateTime ldtStart;
    private LocalDateTime ldtEnd;
    private int Customer_ID;
    private int User_ID;

/** Constructor to view appointments by week or month.
 * @param Week Week
 * @param Appointment_ID Appointment_ID
 * @param Title Title
 * @param Description Description
 * @param Location Location
 * @param Contact_Name Contact_Name
 * @param Type Type
 * @param ldtStart ldtStart
 * @param ldtEnd ldtEnd
 * @param Customer_ID Customer_ID
 * @param User_ID User_ID */
    public AppointmentViewModel(int Week, int Appointment_ID, String Title, String Description, String Location, String Contact_Name, String Type, LocalDateTime ldtStart, LocalDateTime ldtEnd, int Customer_ID, int User_ID) {
        this.Week = Week;
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Contact_Name = Contact_Name;
        this.Type = Type;
        this.ldtStart = ldtStart;
        this.ldtEnd = ldtEnd;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;

    }

    public int getWeek() {
        return Week;
    }

    public void setWeek(int Week) {
        Week = Week;
    }

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public LocalDateTime getLdtStart() {
        return ldtStart;
    }

    public void setLdtStart(LocalDateTime ldtStart) {
        this.ldtStart = ldtStart;
    }

    public LocalDateTime getLdtEnd() {
        return ldtEnd;
    }

    public void setLdtEnd(LocalDateTime ldtEnd) {
        this.ldtEnd = ldtEnd;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }


}
