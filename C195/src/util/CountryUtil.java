package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CountryModel;

/** Class that gets country data from the database. */
public class CountryUtil {
    public static ObservableList<CountryModel> allCountries = FXCollections.observableArrayList();
    public static ObservableList<CountryModel> country = FXCollections.observableArrayList();
    public static ObservableList<CountryModel> countryName = FXCollections.observableArrayList();

    /**
     * Method that SELECTS ALL from the countries table. It sets the country_Id and country to an ObservableList.
     *
     * @return allCountries ObservableList containing the country_Id and country
     */
    public static ObservableList<CountryModel> getAllCountries() throws SQLException {
        allCountries.clear();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            int countryId = rs.getInt("Country_Id");
            String dbCountries = rs.getString("Country");
            CountryModel c = new CountryModel(countryId, dbCountries);
            allCountries.add(c);
        }
        return allCountries;

    }


    public static ObservableList<CountryModel> getTheCountry(int Country_ID) throws SQLException {
        country.clear();
        String sql = "SELECT * FROM countries WHERE Country_ID = ? ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, Country_ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String dbCountries = rs.getString("Country");
            CountryModel c = new CountryModel( dbCountries);
            country.add(c);
        }
        return country;

    }



}





