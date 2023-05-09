package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AddAppointmentsModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/** Class used to get appointments and increment the appointment_ID from the database. */
public class AddAppointmentsUtil {

    public static ObservableList<AddAppointmentsModel> allAppointments = FXCollections.observableArrayList();
    public static ObservableList<AddAppointmentsModel> allAppointmentsLDT = FXCollections.observableArrayList();


/** Method to get the Max appointment_ID. Used to auto increment the appointment_ID for the next appointment created.
 @return ++appointment_ID */
    public static int getAppointment_ID() throws SQLException {
        int appointment_ID = 0;
        String sql = "SELECT Max(Appointment_ID) FROM appointments;";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
            appointment_ID = (rs.getInt(1));
        }

        return ++appointment_ID;
    }



/** Method to SELECT ALL appointments from the appointments table in the database.
 @return allAppointments ObservableList */
    public static ObservableList getAllAppointments() throws SQLException {
        allAppointments.clear();
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {

            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Location = rs.getString("Location");
            String Type = rs.getString("Type");

            Timestamp Start = rs.getTimestamp("Start");
            LocalDateTime ldtStart = Start.toLocalDateTime();

            Timestamp End = rs.getTimestamp("End");
            LocalDateTime ldtEnd = End.toLocalDateTime();

            Timestamp Create_Date = rs.getTimestamp("Create_Date");
            ZonedDateTime zonedCreateDate = Create_Date.toLocalDateTime().atZone(ZoneId.systemDefault());

            String Created_By = rs.getString("Created_By");

            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            ZonedDateTime zonedLastUpdate = Last_Update.toLocalDateTime().atZone(ZoneId.systemDefault());

            String Last_Updated_By = rs.getString("Last_Updated_By");
            int Customer_ID = rs.getInt("Customer_ID");
            int User_ID = rs.getInt("User_ID");
            int Contact_ID = rs.getInt("Contact_ID");
            AddAppointmentsModel addA = new AddAppointmentsModel(Appointment_ID, Title, Description, Location, Type, ldtStart, ldtEnd, zonedCreateDate, Created_By, zonedLastUpdate, Last_Updated_By, Customer_ID, User_ID, Contact_ID);

            allAppointments.add(addA);
        }
        return allAppointments;
    }

    public static ObservableList getAllAppointmentsLDT() throws SQLException {
        allAppointmentsLDT.clear();
        String sql = "SELECT * FROM appointments ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {

            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Location = rs.getString("Location");
            String Type = rs.getString("Type");

            Timestamp Start = rs.getTimestamp("Start");
            ZonedDateTime utcStart = Start.toLocalDateTime().atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedStart = utcStart.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime ldtStart = zonedStart.toLocalDateTime();

            Timestamp End = rs.getTimestamp("End");
            ZonedDateTime utcEnd = End.toLocalDateTime().atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedEnd = utcEnd.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime ldtEnd = zonedEnd.toLocalDateTime();

            Timestamp CreateDate = rs.getTimestamp("Create_Date");
            ZonedDateTime utcCreateDate = CreateDate.toLocalDateTime().atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedCreateDate = utcCreateDate.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime Create_Date = zonedCreateDate.toLocalDateTime();

            String Created_By = rs.getString("Created_By");

            Timestamp LastUpdate = rs.getTimestamp("Last_Update");
            ZonedDateTime utcLastUpdate = LastUpdate.toLocalDateTime().atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedLastUpdate = utcLastUpdate.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime Last_Update = zonedLastUpdate.toLocalDateTime();

            String Last_Updated_By = rs.getString("Last_Updated_By");
            int Customer_ID = rs.getInt("Customer_ID");
            int User_ID = rs.getInt("User_ID");
            int Contact_ID = rs.getInt("Contact_ID");
            AddAppointmentsModel addA = new AddAppointmentsModel(Appointment_ID, Title, Description, Location, Type, ldtStart, ldtEnd, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID);

            allAppointmentsLDT.add(addA);
        }
        return allAppointmentsLDT;
    }

}

