package model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** Class with a Constructor and Setter and Getters for the User Log In page. */
public class UserModel {
    private int user_ID;
    private String user_Name;
    private String userPassword;
    //private LocalDateTime logOnDateTime;


    /**
     * Constructor for the User Log In
     *
     * @param user_ID      user_ID
     * @param user_Name    user_Name
     * @param userPassword userPassword
     */
    public UserModel(int user_ID, String user_Name, String userPassword) {
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.userPassword = userPassword;
    }

    public UserModel(String user_Name) {
        this.user_Name = user_Name;
    }

    public UserModel(int user_ID, String user_Name) {
        this.user_ID = user_ID;
        this.user_Name = user_Name;

    }

    public UserModel(int user_ID) {
        this.user_ID = user_ID;
    }



   /* public UserModel(NamesModel selectedItem) {
    }

    public UserModel(UserModel selectedItem) {
    }*/

    /**
     * Gets the User_ID
     *
     * @return user_ID
     */
    public int getUser_ID() {
        return user_ID;
    }

    /**
     * Gets the UserName.
     *
     * @return user_Name
     */
    public String getUserName() {
        return user_Name;
    }

    /**
     * Gets the User Password.
     *
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

/*
    public void setLogOnDateTime(LocalDateTime logOnDateTime) {
        this.logOnDateTime = logOnDateTime;
    }*/


    /**
     * Sets the User Name to String.
     *
     * @return user_Name
     */
    public String toString() {
        return user_Name;
    }
}

/** Sets the LogOnDateTime to LocalDateTime
 * @return logOnDateTime */
   /* public LocalDateTime localDateTime() {
        return logOnDateTime ;
    }

}*/




