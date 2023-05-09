package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ContactsModel;
import model.NamesModel;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class works with retrieving customer/contact/user names or IDs from the database. */
public class NamesUtil {

    public static ObservableList<NamesModel> CustomerNamesAndID = FXCollections.observableArrayList();
    public static ObservableList<NamesModel> customerName = FXCollections.observableArrayList();
    public static ObservableList<NamesModel> contact_id = FXCollections.observableArrayList();
    public static ObservableList<NamesModel> userName = FXCollections.observableArrayList();
    public static ObservableList<NamesModel> allUsers= FXCollections.observableArrayList();

    /** This method gets the customer name from the database based on the matching appointment_ID.
     It uses a JOIN from the appointments and customers tables.
     @param Appointment_ID input of the appointment_Id
     @return customerName */
    public static ObservableList<NamesModel> getCustomerName(int Appointment_ID) throws SQLException {
        customerName.clear();
        String sql = "SELECT a.Appointment_ID, a.Customer_ID, c.Customer_Name " +
                "FROM appointments a " +
                "JOIN customers c " +
                "ON a.Customer_ID = c.Customer_ID " +
                "WHERE Appointment_ID = ? ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, Appointment_ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String customer_Name = rs.getString("Customer_Name");
            NamesModel name = new NamesModel(customer_Name);
            customerName.add(name);
        }
        return customerName;
    }

    /** This method gets the user name from the database based on the matching appointment_ID.
     It uses a JOIN from the appointments and users tables.
     @param Appointment_ID input of the appointment_Id
     @return userName */
    public static ObservableList<NamesModel> getUserName(int Appointment_ID) throws SQLException {
        userName.clear();
        String sql = "SELECT a.Appointment_ID, a.User_ID, u.User_Name " +
                "FROM appointments a " +
                "JOIN users u " +
                "ON a.User_ID = u.User_ID " +
                "WHERE Appointment_ID = ? ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, Appointment_ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String user_Name = rs.getString("User_Name");
            NamesModel user = new NamesModel(user_Name);
            userName.add(user);
        }
        return userName;
    }

/** This method SELECTS the highest customer_Id from the customers table.
 It is used to generate the next customerId number for making a new customer and giving them a unique Id.
 @return ++customer_Id */
    public static int getCustomer_ID() throws SQLException {
        int customer_ID = 0;
        String sql = "SELECT Max(Customer_ID) FROM customers;";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
            customer_ID = (rs.getInt(1));
        }

        return ++customer_ID;
    }

/** This method SELECTS the Customer_IDs and Customer_Names from the customers table.
 * It is used to find the customer name by the customerId or vice/versa.
 @return CustomerNamesAndID ObservableList */
    public static ObservableList<NamesModel> getCustomerNamesAndID() throws SQLException {
        CustomerNamesAndID.clear();
        String sql = "SELECT Customer_ID, Customer_Name FROM customers  ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            NamesModel nId = new NamesModel(customerId, name);
            CustomerNamesAndID.add(nId);

        }
        return CustomerNamesAndID;
    }
}


