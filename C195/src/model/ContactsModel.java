package model;

/** Class that contains Constructors and Getters and Setters for contact Ids and contacts names. */
public class ContactsModel {
    private int Contact_ID;
    private String Contact_Name;
    private int Appointment_ID;

/** Constructor to supply Contact_ID and Contact_Name.
 * @param Contact_ID Contact_ID
 * @param Contact_Name Contact_Name */
    public ContactsModel(int Contact_ID, String Contact_Name) {
        this.Contact_ID = Contact_ID;
        this.Contact_Name = Contact_Name;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    /** Converts Contact_Name to String.
     * @return Contact_Name */
    public String getContact_Name() {
        return Contact_Name;
    }

    /** Setter for Contact_Name.
     * @param contact_Name contact_Name*/
    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    /** Converts Contact_Name to String.
     * @return Contact_Name */
    public String toString() {
        return Contact_Name;
    }

    /** Gets Appointment_ID.
     * @return Appointment_ID */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /** Sets Appointment_ID.
     * @param appointment_ID appointment_ID */
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }
}


