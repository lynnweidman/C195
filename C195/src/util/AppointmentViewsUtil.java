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

/**Class that pulls data out of the database to create unique tables used for business reporting. */
public class AppointmentViewsUtil {

    public static ObservableList<AppointmentViewModel> allAppointmentsByWeek = FXCollections.observableArrayList();
    public static ObservableList<AppointmentViewModel> allAppointmentsByMonth = FXCollections.observableArrayList();

/** Method that gets appointments by week. It displays the week number as "Week", Appointment_ID, Title, Description, Location, Contact_Name, Type,
 Start, End, .Customer_ID, and User_ID. It pulls data from the appointments table and lists the data for the week number entered.
 @param WeekNum the input week number
 @return allAppointmentsByWeek ObservableList used to set the Appointments By Week table. */
    public static ObservableList<AppointmentViewModel> getAppointmentByWeek(int WeekNum) throws SQLException {
        allAppointmentsByWeek.clear();
        String sql = "SELECT  WEEK(Start) AS Week, a.Appointment_ID, a.Title, a.Description, a.Location, c.Contact_Name, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID " +
                "FROM appointments a " +
                "JOIN contacts c " +
                "ON a.Contact_ID = c.Contact_ID " +
                 "WHERE WEEK(Start) = ? ";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, WeekNum);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            int Week = rs.getInt("Week");
            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Location = rs.getString("Location");
            String Contact_Name = rs.getString("Contact_Name");
            String Type = rs.getString("Type");

            Timestamp Start = rs.getTimestamp("Start");
            ZonedDateTime utcStart = Start.toLocalDateTime().atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedStart = utcStart.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime ldtStart = zonedStart.toLocalDateTime();


            Timestamp End = rs.getTimestamp("End");
            ZonedDateTime utcEnd = End.toLocalDateTime().atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedEnd = utcEnd.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime ldtEnd = zonedEnd.toLocalDateTime();

            int Customer_ID = rs.getInt("Customer_ID");
            int User_ID = rs.getInt("User_ID");

            AppointmentViewModel w = new AppointmentViewModel(Week, Appointment_ID, Title, Description, Location, Contact_Name, Type, ldtStart, ldtEnd,  Customer_ID, User_ID);

            allAppointmentsByWeek.add(w);
        }
        return allAppointmentsByWeek;

    }

    /** Method that gets appointments by month. It displays the month number as "Month", Appointment_ID, Title, Description, Location, Contact_Name, Type,
     Start, End, .Customer_ID, and User_ID. It pulls data from the appointments table and list the data for the week number entered.
     @param monthNum the input month number
     @return allAppointmentsByMonth ObservableList used to set the Appointments By Month table. */
    public static ObservableList<AppointmentViewModel> getAppointmentByMonth(int monthNum) throws SQLException {
        allAppointmentsByMonth.clear();
        String sql = "SELECT  month(Start) AS Month, a.Appointment_ID, a.Title, a.Description, a.Location, c.Contact_Name, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID " +
                "FROM appointments a " +
                "JOIN contacts c " +
                "ON a.Contact_ID = c.Contact_ID " +
                "WHERE month(Start) = ? ";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, monthNum);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            int Month = rs.getInt("Month");
            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Location = rs.getString("Location");
            String Contact_Name = rs.getString("Contact_Name");
            String Type = rs.getString("Type");

            Timestamp Start = rs.getTimestamp("Start");
            ZonedDateTime utcStart = Start.toLocalDateTime().atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedStart = utcStart.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime ldtStart = zonedStart.toLocalDateTime();


            Timestamp End = rs.getTimestamp("End");
            ZonedDateTime utcEnd = End.toLocalDateTime().atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedEnd = utcEnd.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime ldtEnd = zonedEnd.toLocalDateTime();

            int Customer_ID = rs.getInt("Customer_ID");
            int User_ID = rs.getInt("User_ID");

            AppointmentViewModel m = new AppointmentViewModel(Month, Appointment_ID, Title, Description, Location, Contact_Name, Type, ldtStart, ldtEnd,  Customer_ID, User_ID);

            allAppointmentsByMonth.add(m);
        }
        return allAppointmentsByMonth;

    }

}
