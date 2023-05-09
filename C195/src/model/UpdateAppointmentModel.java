package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;


/** Class with Constructors and Setters and Getters for Updating Appointments. */
public class UpdateAppointmentModel {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private Timestamp Start;
    private Timestamp End;
   // private Timestamp Create_Date;
    private String Created_By;
    //private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;
    private int year;
    private Month month;
    private int date;
    private int hour;
    private int minutes;
    private LocalDateTime ldtStart;
    private LocalDateTime ldtEnd;
    private LocalDateTime Create_Date;
    private LocalDateTime Last_Update;


/** Update Appointment Constructor.
 @param Appointment_ID Appointment_ID
 @param Title Title
 @param Description Description
 @param Location Location
 @param Type Type
 @param Create_Date Create_Date
 @param Created_By Created_By
 @param Last_Update Last_Update
 @param Last_Updated_By Last_Updated_By
 @param Customer_ID Customer_ID
 @param User_ID User_ID
 @param Contact_ID Contact_ID */

    public UpdateAppointmentModel(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime ldtStart, LocalDateTime ldtEnd, LocalDateTime Create_Date, String Created_By, LocalDateTime Last_Update, String Last_Updated_By, int Customer_ID, int User_ID, int Contact_ID) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.ldtStart = ldtStart;
        this.ldtEnd = ldtEnd;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;
    }

    public UpdateAppointmentModel(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime ldtStart, LocalDateTime ldtEnd, LocalDateTime Last_Update, String Last_Updated_By, int Customer_ID, int User_ID, int Contact_ID) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.ldtStart = ldtStart;
        this.ldtEnd = ldtEnd;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;
    }

/** Gets the Hours.
 @return hour */
    public int getHours() {
        return hour;
    }

    /** Gets the Appointment_ID
     @return Appointment_ID */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /** Sets the Appointment_ID
     @param appointment_ID appointment_ID*/
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    /**Gets the Title.
     @return Title */
    public String getTitle() {
        return Title;
    }

    /** Sets the Title.
     @param title title */
    public void setTitle(String title) {
        Title = title;
    }

    /** Gets the Description.
     @return Description */
    public String getDescription() {
        return Description;
    }

    /** Sets the Description.
     @param description description */
    public void setDescription(String description) {
        Description = description;
    }

    /** Gets the Location.
     @return Location */
    public String getLocation() {
        return Location;
    }

    /**Sets the Location.
     @param location location */
    public void setLocation(String location) {
        Location = location;
    }

/** Gets the Type.
 @return Type */
    public String getType() {
        return Type;
    }

    /**Sets the Type.
     @param type type */
    public void setType(String type) {
        Type = type;
    }

    /** Gets the Start.
     @return Start */
    public Timestamp getStart() {
        return Start;
    }

    /** Sets the Start.
     @param start start */
    public void setStart(Timestamp start) {
        Start = start;
    }

    /** Gets the End.
     @return End */
    public Timestamp getEnd() {
        return End;
    }

    /** Sets the End.
     @param end end */
    public void setEnd(Timestamp end) {
        End = end;
    }

/** Gets Created_By.
 @return Created_By */
    public String getCreated_By() {
        return Created_By;
    }

    /** Sets Created_By.
     @param created_By created_By */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

/**Gets Last_Updated_By.
 @return  Last_Updated_By */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /** Sets Last_Updated_By.
     @param last_Updated_By last_Updated_By */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /** Gets the Customer_ID.
     @return Customer_ID */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /** Sets the Customer_ID.
     @param customer_ID customer_ID */
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }


    /** Gets the Date.
     @return date */
    public int getDate() {
        return date;
    }

    /** Sets The Date.
     @param date date*/
    public void setDate(int date) {
        this.date = date;
    }

/** Gets the Minutes.
 @return minutes */
    public int getMinutes() {
        return minutes;
    }

    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(LocalDateTime last_Update) {
        Last_Update = last_Update;
    }


    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
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


}





