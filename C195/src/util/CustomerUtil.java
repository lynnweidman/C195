package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/** This class works with customer data from the customer and first_level_division tables. */
public class CustomerUtil {
    public static ObservableList<CustomerModel> addCustomer = FXCollections.observableArrayList();


    /** Method gets customer's data from the customer and first_level_division table and adds it to the addCustomer ObservableList.
     @return addCustomer used to populate customer table in the GUI. */
    public static ObservableList getAllCustomers() throws SQLException {
        addCustomer.clear();
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, cu.Division_ID, fld.Country_ID FROM client_schedule.customers cu\n" +
                "INNER JOIN first_level_divisions fld ON\n" +
                "cu.Division_Id = fld.Division_Id;";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            int Customer_ID = rs.getInt("Customer_ID");
            String Customer_Name = rs.getString("Customer_Name");
            String Address = rs.getString("Address");
            String Postal_Code = rs.getString("Postal_Code");
            String Phone = rs.getString("Phone");
            int Division_ID = rs.getInt("Division_ID");
            int Country_ID = rs.getInt("Country_ID");
            CustomerModel addC = new CustomerModel(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID, Country_ID);
            addCustomer.add(addC);
        }
        return addCustomer;

    }



/** Method to get the highest Customer_ID from the customers table. Used to set a unique Id for new customers.
 @return  ++customer_ID */
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


/** Method deletes customers from the customers table based on Customer_ID. */
    public static void deleteCustomer(int Customer_ID) throws SQLException {

        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, Customer_ID);
        ps.executeUpdate();
    }

    /** Method updates the customer data based on input from the GUI. It uses the Customer_ID to identify which customer to update.
     @param Customer_ID  input customer_id used to identify the customer.
     @param Customer_Name input customer_Name
     @param Address input address
     @param Postal_Code input postal_Code
     @param Phone  input phone
     @param Create_Date input create_date
     @param Created_By input created_By
     @param Last_Update input last_update
     @param Last_Updated_By input last_updated_by
     @param Division_ID input division_ID */

    public static void updateCustomerTable(int Customer_ID, String Customer_Name, String Address, String
            Postal_Code, String Phone, Timestamp Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Division_ID)
            throws SQLException {

        String sql = "UPDATE  customers " +
                "SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? " +
                "WHERE Customer_ID = ? ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        try {

            ps.setString(1, Customer_Name);
            ps.setString(2, Address);
            ps.setString(3, Postal_Code);
            ps.setString(4, Phone);
            ps.setTimestamp(5, Create_Date);
            ps.setString(6, Created_By);
            ps.setTimestamp(7, Last_Update);
            ps.setString(8, Last_Updated_By);
            ps.setInt(9, Division_ID);
            ps.setInt(10, Customer_ID);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void updatePartialCustomerTable(int Customer_ID, String Customer_Name, String Address, String
            Postal_Code, String Phone, String Created_By, Timestamp Last_Update, String Last_Updated_By)
            throws SQLException {

        String sql = "UPDATE  customers " +
                "SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ? " +
                "WHERE Customer_ID = ? ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        try {

            ps.setString(1, Customer_Name);
            ps.setString(2, Address);
            ps.setString(3, Postal_Code);
            ps.setString(4, Phone);
            ps.setString(5, Created_By);
            ps.setTimestamp(6, Last_Update);
            ps.setString(7, Last_Updated_By);
            ps.setInt(8, Customer_ID);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    /** Method deletes customer appointment from the appointments table based on Customer_ID. */
    public static void deleteCustomerAppointments(int Customer_ID) throws SQLException {

        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, Customer_ID);
        ps.executeUpdate();
    }


    public static ObservableList<CustomerModel> lookUpCustomer(String customerName) {
        ObservableList<CustomerModel> customerFound = FXCollections.observableArrayList();

            for (CustomerModel theCustomer : addCustomer ) {
                if (theCustomer.getCustomer_Name().toLowerCase().contains(customerName)) {

                    customerFound.add(theCustomer);
                }
        }
            return customerFound;
    }

}



















