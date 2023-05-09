package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;

/** Class to have Constructors and Getters and Setters to generate business reports */
public class BusinessReportsModel {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Contact;
    private String Type;
    private LocalDateTime ldtStart;
    private LocalDateTime ldtEnd;
    private Timestamp End;
    private String Created_By;
    private String Last_Updated_By;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;
    private int Number;
    private Month month;
    private String date;

    /** Constructor for getting the contact schedule.
     @param Contact Contact
     @param Appointment_ID Appointment_ID
     @param Title Title
     @param Type Type
     @param Description Description
     @param ldtStart ldtStart
     @param ldtEnd ldtEnd
     @param Customer_ID Customer_ID */
    public BusinessReportsModel(String Contact, int Appointment_ID, String Title, String Type, String Description, LocalDateTime ldtStart, LocalDateTime ldtEnd, int Customer_ID) {

        this.Contact = Contact;
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Type = Type;
        this.ldtStart = ldtStart;
        this.ldtEnd = ldtEnd;
        this.Customer_ID = Customer_ID;

    }

    /** Constructor for getting Appointments By Type And Month.
     @param Number Number
     @param  Type Type
     @param month month */
    public BusinessReportsModel(int Number, String Type, Month month) {

        this.Number = Number;
        this.Type = Type;
        this.month = month;
    }

    /** Constructor for getting number of appointments by date.
     @param Number Number
      @param  date date */
    public BusinessReportsModel(int Number, String date ) {

        this.Number= Number;
        this.date = date;

    }

    /**  Gets the Appointment_ID
     * @return Appointment_ID */
    public int getAppointment_ID() {
        return Appointment_ID;
    }
 /** Sets the Appointment_ID
  @param appointment_ID appointment_ID */
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    /**Gets the Title.
     * @return Title */
    public String getTitle() {
        return Title;
    }

    /** Sets the Title.
     @param title  title */
    public void setTitle(String title) {
        Title = title;
    }

    /** Gets Description.
     * @return Description */
    public String getDescription() {
        return Description;
    }

    /** Sets Description.
     * @param  description description */
    public void setDescription(String description) {
        Description = description;
    }

    /** Gets Location.
     * @return Location */
    public String getLocation() {
        return Location;
    }

    /** Sets Location.
     * @param location location */
    public void setLocation(String location) {
        Location = location;
    }

    /** Gets Type.
     * @return Type */
    public String getType() {
        return Type;
    }

    /** Sets Type.
     * @param type type */
    public void setType(String type) {
        Type = type;
    }


 /** returns ldtStart.
  * @return ldtStart */
    public LocalDateTime getLdtStart() {
        return ldtStart;
    }

    /**sets ldtStart.
     * @param ldtStart ldtStart */
    public void setLdtStart(LocalDateTime ldtStart) {
        this.ldtStart = ldtStart;
    }

    /** Returns ldtEnd.
     * @return  ldtEnd */
    public LocalDateTime getLdtEnd() {
        return ldtEnd;
    }

    /** Sets ldtEnd.
     * @param ldtEnd ldtEnd */
    public void setLdtEnd(LocalDateTime ldtEnd) {
        this.ldtEnd = ldtEnd;
    }

    /** Gets End.
     * @return End */
    public Timestamp getEnd() {
        return End;
    }

    /** Sets End.
     * @param end end */
    public void setEnd(Timestamp end) {
        End = end;
    }

    /** GEts Created_By.
     * @return Created_By */
    public String getCreated_By() {
        return Created_By;
    }

    /** Sets Created_By.
     * @param created_By created_By */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

/** Gets Last_Updated_By.
 * @return Last_Updated_By */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /** Sets Last_Updated_By.
     * @param last_Updated_By last_Updated_By */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /** Gets Customer_ID.
     * @return Customer_ID */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /** Sets Customer_ID.
     * @param customer_ID  customer_ID*/
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /** Gets User_ID.
     * @return User_ID */
    public int getUser_ID() {
        return User_ID;
    }

    /** Sets User_ID.
     * @param user_ID user_ID */
    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    /** Gets Contact_ID.
     * @return Contact_ID */
    public int getContact_ID() {
        return Contact_ID;
    }

    /** Sets Contact_ID.
     * @param contact_ID contact_ID */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    /**Gets Number.
     * @return Number */
    public int getNumber() {
        return Number;
    }

    /** Sets Number.
     * @param number number */
    public void setNumber(int number) {
        Number = number;
    }

    /** Gets Contact.
     * @return Contact */
    public String getContact() {
        return Contact;
    }

    /** Sett Contact.
     * @param contact contact*/
    public void setContact(String contact) {
        Contact = contact;
    }

    /** Gets month.
     * @return month*/
    public Month getMonth() {
        return month;
    }

    /** Sets month.
     * @param month month*/
    public void setMonth(Month month) {
        this.month = month;
    }

    /** Gets date.
     * @return date */
    public String getDate() {
        return date;
    }

    /** Sets date.
     * @param date date */
    public void setDate(String date) {
        this.date = date;
    }
}
