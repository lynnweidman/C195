package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/** This class works with the appointments table in the database. */

public class UpdateAppointmentUtil {

    public static ObservableList<UpdateAppointmentModel> allAppointments = FXCollections.observableArrayList();


/** This method takes updated appointment information from the GUI and puts it into the appointments table in the database.
 @param Appointment_ID updates the appointments table where the appointment_ID matches
 @param Title input from title
 @param Description input from description
 @param Location input from location
 @param Type input from type
 @param Start input from start
 @param End  input from end
 @param Customer_ID input from customer_Id
 @param User_ID  input from user_Id
 @param Contact_ID  input from Contact_ID */
    public static void updateAppointmentsTable(int Appointment_ID, String Title, String Description, String
            Location, String Type, Timestamp Start, Timestamp End, Timestamp Last_Update, String Last_Updated_By, int Customer_ID, int User_ID, int Contact_ID)
            throws SQLException {
        allAppointments.clear();
        String sql = "UPDATE  appointments " +
                "SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                "WHERE Appointment_ID = ? ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        try {
            ps.setString(1, Title);
            ps.setString(2, Description);
            ps.setString(3, Location);
            ps.setString(4, Type);
            ps.setTimestamp(5, Start);
            ps.setTimestamp(6, End);
            ps.setTimestamp(7, Last_Update);
            ps.setString(8, Last_Updated_By);
            ps.setInt(9, Customer_ID);
            ps.setInt(10, User_ID);
            ps.setInt(11, Contact_ID);
            ps.setInt(12, Appointment_ID);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }



/** This method deletes appointments that match the given Appointment_ID
 @param Appointment_ID deletes the appointment where the appointment_ID matches */
    public static void deleteAppointment(int Appointment_ID) throws SQLException {

        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, Appointment_ID);
        ps.executeUpdate();
    }
}




