package util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisionModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is used to get the first_level_divisions (states) from the database. */
public class FirstLevelDivisionsUtil {

    public static ObservableList<FirstLevelDivisionModel> allDivisions = FXCollections.observableArrayList();
    public static ObservableList<FirstLevelDivisionModel> theDivision = FXCollections.observableArrayList();
    public static ObservableList<FirstLevelDivisionModel> theDivisionByCustomerId = FXCollections.observableArrayList();

    /** Method to get the first_level_divisions based on matching countryId from the first_level_divisions table.
     @return  allDivisions based on the input of a countryId. */
    public static ObservableList<FirstLevelDivisionModel> getAllDivisions(int countryId) throws SQLException {
     allDivisions.clear();
        String sql = "SELECT * " +
                "FROM first_level_divisions " +
                "WHERE Country_Id = ?";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int dbDivisionId= rs.getInt("Division_Id");
            String dbDivisions = rs.getString("Division");
            FirstLevelDivisionModel d = new FirstLevelDivisionModel(dbDivisionId, dbDivisions );
            allDivisions.add(d);
        }
        return allDivisions;
    }

    /** Method to get the division based on division_id.
     * @param Division_ID Division_ID */
 public static ObservableList<FirstLevelDivisionModel> getTheDivision (int Division_ID) throws SQLException {
        theDivision.clear();
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, Division_ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String division = rs.getString("Division");
            FirstLevelDivisionModel d = new FirstLevelDivisionModel(division);
            theDivision.add(d);
        }
        return theDivision;

    }


    public static int getTheDivisionByCustomerId (int Customer_ID) throws SQLException {

        int division = 0;
        String sql = "SELECT Division_ID FROM customers WHERE Customer_ID = ?";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, Customer_ID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            division = rs.getInt("Division_ID");

        }
        return division;

    }

}








