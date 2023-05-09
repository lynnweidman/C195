package util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import model.UserModel;


/** This class is used to get all users from the database. */
public class UserUtil {
    public static ObservableList<UserModel> user = FXCollections.observableArrayList();
    public static ObservableList<UserModel> allUsers = FXCollections.observableArrayList();
    public static ObservableList<UserModel> userByID = FXCollections.observableArrayList();
    public static ObservableList<UserModel> userID = FXCollections.observableArrayList();

    /**
     * This method SELECTS ALL from the users database. It adds all users to the allUser ObservableList.
     */

    public static void getUser() throws SQLException {

        String sql = "SELECT * from users";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int dbID = rs.getInt("User_ID");
            String dbName = rs.getString("User_Name");
            String dbPassword = rs.getString("Password");
            UserModel u = new UserModel(dbID, dbName, dbPassword);
            user.add(u);
        }
    }


    public static ObservableList<UserModel> getAllUsers() throws SQLException {
        allUsers.clear();
        String sql = "SELECT * from users";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userId = rs.getInt("User_ID");
            String dbName = rs.getString("User_Name");
            String dbPassword = rs.getString("Password");

            UserModel u = new UserModel(userId, dbName, dbPassword);
            allUsers.add(u);

        }
        return allUsers;
    }

    /**
     * This method gets the user name from the database based on the matching appointment_ID.
     * It uses a JOIN from the appointments and users tables.
     *
     * @param Appointment_ID input of the appointment_Id
     * @return userName
     */
    public static ObservableList<UserModel> getUserByID(int Appointment_ID) throws SQLException {
        userByID.clear();
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
            UserModel u = new UserModel(user_Name);
            userByID.add(u);
        }
        return userByID;
    }

    public static ObservableList<UserModel> getUserID(String user_Name, String password) throws SQLException {
        userID.clear();
        String sql = "SELECT * from users WHERE User_Name= ? and Password = ? ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, user_Name);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("User_ID");
            String name = rs.getString("User_Name");
            String pw = rs.getString("Password");

            UserModel u = new UserModel(id, name, pw);
            userID.add(u);
        }
        return userID;
    }
}



























