package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**Class that pulls data out of the database to create unique tables used for business reporting. */
public class BusinessReportsUtil {

    public static ObservableList<BusinessReportsModel> appointmentByTypeAndMonth = FXCollections.observableArrayList();
    public static ObservableList<BusinessReportsModel> contactSchedule= FXCollections.observableArrayList();
    public static ObservableList<BusinessReportsModel> numberOfAppointmentsPerDay= FXCollections.observableArrayList();

    /** Method that gets appointments by Type and Month from the appointments table. A new column is added called "Total",
     which counts how many of each "Type". It is grouped by Start which is renamed "Month", and ordered by date.
     @return appointmentByTypeAndMonth used to set the Appointment By Type And Month table in the GUI. */
    public static ObservableList getAppointmentByTypeAndMonth() throws SQLException {
        appointmentByTypeAndMonth.clear();
        String sql = "SELECT count(Type) AS Total, Type, Start as Date FROM appointments group by Type, month(Date) order by Start; ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {


            int total= rs.getInt("Total");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Date");
            LocalDate s = start.toLocalDateTime().toLocalDate();
            Month month = s.getMonth();

            BusinessReportsModel ntm = new BusinessReportsModel(total, type, month);

            appointmentByTypeAndMonth.add(ntm);
        }
        return appointmentByTypeAndMonth;

    }

/** Method that gets the schedule for each contact. It pulls data from the appointments table and contacts table based on matching Contact_ID,
  and is ordered by Contact_Name. It shows Contact_Name, Appointment_ID,Title, Type, Description, Start, End, and Customer_ID
 @return contactSchedule to set the Contact Schedule table in the GUI*/
    public static ObservableList getContactSchedule() throws SQLException {
        contactSchedule.clear();
        String sql =  "SELECT c.Contact_Name, a.Appointment_ID, a.Title, a.Type, a.Description, a.Start, a.End, a.Customer_ID " +
                "from appointments a " +
                "join contacts c " +
                "on a.Contact_ID = c.Contact_ID " +
                "order by Contact_Name ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            String contactName = rs.getString("Contact_Name");
            int appointment_ID= rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String type = rs.getString("Type");
            String description = rs.getString("Description");

            Timestamp Start = rs.getTimestamp("Start");
            ZonedDateTime utcStart = Start.toLocalDateTime().atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedStart = utcStart.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime ldtStart = zonedStart.toLocalDateTime();

            Timestamp End = rs.getTimestamp("End");
            ZonedDateTime utcEnd = End.toLocalDateTime().atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedEnd = utcEnd.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime ldtEnd = zonedEnd.toLocalDateTime();

            int customer_ID = rs.getInt("Customer_ID");

            BusinessReportsModel s = new BusinessReportsModel(contactName, appointment_ID, title, type, description, ldtStart, ldtEnd, customer_ID);

            contactSchedule.add(s);
        }
        return contactSchedule;

    }

   /** Method that gets the number of appointments per day. It pulls data from the appointments table and creates a new
    column called Number which counts all appointments grouped by Start (renamed as Date), and ordered by Date.
    @return numberOfAppointmentsPerDay which sets the  Number Of Appointments Per Day table in the GUI. */
    public static ObservableList getNumberOfAppointmentsPerDay() throws SQLException {
        numberOfAppointmentsPerDay.clear();
        String sql =  "SELECT  count(Start) as Number, Start as Date  FROM appointments group by DATE(Start) order by Number ; ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {

            int Number= rs.getInt("Number");
            Timestamp date = rs.getTimestamp("Date");
            ZonedDateTime zonedStart = date.toLocalDateTime().atZone(ZoneId.systemDefault());
            String Date = zonedStart.format(DateTimeFormatter.ofPattern("MM:dd:yyyy"));

            BusinessReportsModel d = new BusinessReportsModel(Number, Date);

            numberOfAppointmentsPerDay.add(d);
        }
        return numberOfAppointmentsPerDay;

    }
}
