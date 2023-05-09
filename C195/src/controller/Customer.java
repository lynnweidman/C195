package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
//import jdk.internal.org.objectweb.asm.TypeReference;
import javafx.stage.Stage;
import model.*;
import util.*;


/** Class that populates the customer table in the GUI based on data in the customer table in the database. */
public class Customer implements Initializable {
    public TextField customerIdTxt;
    public TextField nameTxt;
    public TextField addressTxt;
    public Button saveNewButton;
    @FXML
    private ComboBox<CountryModel> countryComboBox;
    @FXML
    private ComboBox<FirstLevelDivisionModel> firstLevelDivisionComboBox;
    public TextField customerPostalCode;
    public TextField customerPhone;
    public TableColumn tableCustomerID;
    public TableView addCustomerTable;
    public TableColumn tableCustomerName;
    public TableColumn tableAddress;
    public TableColumn tablePostalCode;
    public TableColumn tablePhone;
    public TableColumn tableDivisionID;
    public TableColumn tableCountryID;


/** Initializes the Add Customer fxml page and populated the customer table based on the customer database.
 The customer ID is auto incremented as 1 higher than the highest in the database. The country combo box is set
 to all countries.
 @param url  url
 @param resourceBundle resourceBundle */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableCustomerID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        tableCustomerName.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        tableAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        tablePostalCode.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        tablePhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        tableDivisionID.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
        tableCountryID.setCellValueFactory(new PropertyValueFactory<>("Country_ID"));


        try {
            addCustomerTable.setItems(CustomerUtil.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            customerIdTxt.setText(String.valueOf(CustomerUtil.getCustomer_ID()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //Sets the countryComboBox;
        try {
            countryComboBox.setItems(CountryUtil.getAllCountries());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** Action event that sets the firstLevelDivisionComboBox based on the countryID
     @param actionEvent actionEvent */
    public void countrySelected(ActionEvent actionEvent) {
        CountryModel c = countryComboBox.getValue();

        try {
            firstLevelDivisionComboBox.setItems(FirstLevelDivisionsUtil.getAllDivisions(c.getCountryId()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        catch (NullPointerException n) {
        return;
    }
    }



/** Action event that saves the input from the fields to the customer table in the database the the GUI upon pressing
 the "Save Button"
 @param actionEvent actionEvent */
    public void saveButtonPressed(ActionEvent actionEvent) throws SQLException {

        if (firstLevelDivisionComboBox.getItems() == null || countryComboBox.getItems() == null || customerIdTxt.getText().isEmpty()
                || nameTxt.getText().isEmpty() || addressTxt.getText().isEmpty() || customerPostalCode.getText().isEmpty() || customerPhone.getText().isEmpty()) {
            Alert invalidEntry = new Alert(Alert.AlertType.ERROR);
            invalidEntry.setContentText("All fields must be filled.");
            invalidEntry.showAndWait();
            return;
        }


        try {
            String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(customerIdTxt.getText()));
            ps.setString(2, nameTxt.getText());
            ps.setString(3, addressTxt.getText());
            ps.setString(4, customerPostalCode.getText());
            ps.setString(5, customerPhone.getText());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(7,   "admin");
            ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(9, "admin");
            ps.setInt(10, firstLevelDivisionComboBox.getValue().getDbDivisionId());
            ps.execute();

            addCustomerTable.setItems(CustomerUtil.getAllCustomers());

            customerIdTxt.setText(String.valueOf(CustomerUtil.getCustomer_ID()));
            nameTxt.clear();
            addressTxt.clear();
            customerPostalCode.clear();
            customerPhone.clear();

        } catch (NullPointerException n) {
            Alert allFields = new Alert(Alert.AlertType.ERROR);
            allFields.setTitle("ERROR");
            allFields.setContentText("All fields must be filled.");
            allFields.showAndWait();
            return;
        }
    }


    /** Action event to take the user back to the Menu Page once the "Back To Menu Page"is pushed.
     @param actionEvent actionEvent */
    public void backToMenuPagePushed(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 500.0));
        stage.show();
    }
}











































