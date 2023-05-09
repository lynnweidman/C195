package model;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/** Class with Constructors and Setters and Getters for Adding Appointments. */
public class AddAppointmentsModel {

    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime ldtStart;
    private LocalDateTime ldtEnd;
    private ZonedDateTime zonedCreateDate;
    private LocalDateTime Create_Date;
    private String Created_By;
    private ZonedDateTime zonedLastUpdate ;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;
    private String User_Name;

/** Add Appointments Constructor.
 @param Appointment_ID
 @param Title
 @param Description
 @param Location
 @param Type
 @param ldtStart
 @param ldtEnd
 @param zonedCreateDate
 @param Created_By
 @param zonedLastUpdate
 @param Last_Updated_By
 @param Customer_ID
 @param User_ID
 @param Contact_ID */
    public AddAppointmentsModel(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime ldtStart, LocalDateTime ldtEnd, ZonedDateTime zonedCreateDate, String Created_By, ZonedDateTime zonedLastUpdate, String Last_Updated_By, int Customer_ID, int User_ID, int Contact_ID) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.ldtStart = ldtStart;
        this.ldtEnd = ldtEnd;
        this.zonedCreateDate = zonedCreateDate;
        this.Created_By = Created_By;
        this.zonedLastUpdate = zonedLastUpdate;
        this.Last_Updated_By = Last_Updated_By;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;
    }

    public AddAppointmentsModel(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime ldtStart, LocalDateTime ldtEnd, LocalDateTime Create_Date, String Created_By, LocalDateTime Last_Update, String Last_Updated_By, int Customer_ID, int User_ID, int Contact_ID) {
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


/** Gets the appointmentID.
 @return  Appointment_ID */
    public int getAppointment_ID() {
        return Appointment_ID;
    }
/** Sets the appointmentID.
  @param appointment_ID appointment_ID  */
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    /** Gets the Title.
     @return Title */
    public String getTitle() {
        return Title;
    }

    /** Sets the Title.
     @param title title*/
    public void setTitle(String title) {
        Title = title;
    }

    /** Gets the Description.
     @return Description */
    public String getDescription() {
        return Description;
    }

 /** sets the Description.
  @param description description */
    public void setDescription(String description) {
        Description = description;
    }

/** Gets the Location.
 @return Location */
    public String getLocation() {
        return Location;
    }

 /** Sets the Location.
  @param location location */
    public void setLocation(String location) {
        Location = location;
    }


/** Gets the Type.
 @return  Type */
    public String getType() {
        return Type;
    }

    /** Sets the Type.
     @param type  type */
    public void setType(String type) {
        Type = type;
    }

    /** Gets the LocalDateTime Start.
     @return ldtStart */
    public LocalDateTime getLdtStart() {
        return ldtStart;
    }

/** /** Gets the LocalDateTime End.
 @return ldtEnd */
    public LocalDateTime getLdtEnd() {
        return ldtEnd;
    }

    /** Gets Created_By.
     @return Created_By */
    public String getCreated_By() {
        return Created_By;
    }

    /**Sets Created_By.
     @param created_By created_By*/
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

   /** Gets Last_Updated_By.
    @return  Last_Updated_By */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /** Sets Last_Updated_By.
     @param last_Updated_By last_Updated_By*/
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /** Gets the Customer_ID.
     @return Customer_ID */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /** Sets the Customer_ID.
     @param customer_ID customer_ID*/
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
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

    public ZonedDateTime getZonedCreateDate() {
        return zonedCreateDate;
    }

    public void setZonedCreateDate(ZonedDateTime zonedCreateDate) {
        this.zonedCreateDate = zonedCreateDate;
    }

    public void setLdtStart(LocalDateTime ldtStart) {
        this.ldtStart = ldtStart;
    }

    public void setLdtEnd(LocalDateTime ldtEnd) {
        this.ldtEnd = ldtEnd;
    }

    public ZonedDateTime getZonedLastUpdate() {
        return zonedLastUpdate;
    }

    public void setZonedLastUpdate(ZonedDateTime zonedLastUpdate) {
        this.zonedLastUpdate = zonedLastUpdate;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }
}
