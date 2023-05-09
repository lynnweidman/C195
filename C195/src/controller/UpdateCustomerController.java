package controller;

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
import javafx.stage.Stage;
import model.*;
import util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

//import static util.CountryUtil.getTheCountry;

/** Class that allows updates to current customers. */
public class UpdateCustomerController implements Initializable {
    public TextField customerIdTxt;
    public TextField nameTxt;
    public TextField addressTxt;
    public TextField searchByCustomerName;
    @FXML
    private ComboBox<CountryModel> countryComboBox;
    @FXML
    private ComboBox<FirstLevelDivisionModel> firstLevelDivisionComboBox;
    public TextField customerPostalCode;
    public TextField customerPhone;
    public TableView addCustomerTable;
    public TableColumn tableCustomerID;
    public TableColumn tableCustomerName;
    public TableColumn tableAddress;
    public TableColumn tablePostalCode;
    public TableColumn tablePhone;
    public TableColumn tableDivisionID;
    public TableColumn tableCountryID;
    public Button saveUpdateButton;

    /**
     * Initializes the Update Customer page. Sets the table with current customer data from the database. Sets the countries combo box.
     *
     * @param url            url
     * @param resourceBundle resourceBundle
     */
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


        //Sets the countryComboBox;
        try {
            countryComboBox.setItems(CountryUtil.getAllCountries());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException n) {
            return;
        }
    }

    /**
     * Action event that sets the firstLevelDivisionComboBox based on the CountryID
     *
     * @param actionEvent actionEvent
     */
    public void countrySelected(ActionEvent actionEvent) {
        CountryModel c = countryComboBox.getValue();
        //countryTxt.setText(String.valueOf(countryComboBox.getValue()));

        try {
            firstLevelDivisionComboBox.setPromptText(null);
            firstLevelDivisionComboBox.setValue(null);
            firstLevelDivisionComboBox.setItems(FirstLevelDivisionsUtil.getAllDivisions(c.getCountryId()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException n) {
            return;
        }
    }

    /**
     * Action event to first delete customer appointments based on CustomerID, then delete the customer from the customers table
     * upon pressing the "Delete Customer Button".
     */
    public void deleteCustomerButtonPressed(ActionEvent actionEvent) throws SQLException {
        CustomerModel selectedCustomer = (CustomerModel) addCustomerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            Alert invalidEntry = new Alert(Alert.AlertType.ERROR);
            invalidEntry.setContentText("Must select a customer to be deleted.");
            invalidEntry.showAndWait();
            return;
        }

        int CustomerID = selectedCustomer.getCustomer_ID();
        CustomerUtil.deleteCustomerAppointments(CustomerID);
        CustomerUtil.deleteCustomer(CustomerID);
        addCustomerTable.setItems(CustomerUtil.getAllCustomers());
    }

    /**
     * Action event that populates the updatable fields with customer data. The data based on the selected customer from the
     * table once the "Select Customer To Update Button" is pushed. Also sets the country combo box to all countries, so the country and
     * first level divisions may be updated.
     *
     * @param actionEvent actionEvent
     */
    public void SelectCustomerToUpdateButtonPushed(ActionEvent actionEvent) throws SQLException {
        searchByCustomerName.clear();
        CustomerModel selectedCustomer = (CustomerModel) addCustomerTable.getSelectionModel().getSelectedItem();
        if (customerIdTxt.equals(null)) {
            Alert invalidEntry = new Alert(Alert.AlertType.ERROR);
            invalidEntry.setContentText("Must select a customer to be updated.");
            invalidEntry.showAndWait();
            return;
        }


        try {
            addCustomerTable.setItems(CustomerUtil.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            countryComboBox.setPromptText(String.valueOf(CountryUtil.getTheCountry(selectedCustomer.getCountry_ID())));
            firstLevelDivisionComboBox.setPromptText(String.valueOf(FirstLevelDivisionsUtil.getTheDivision(selectedCustomer.getDivision_ID())));
            firstLevelDivisionComboBox.setItems(FirstLevelDivisionsUtil.getAllDivisions(selectedCustomer.getCountry_ID()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException n) {
            Alert invalidEntry = new Alert(Alert.AlertType.ERROR);
            invalidEntry.setContentText("Must select a customer to be updated.");
            invalidEntry.showAndWait();
            return;
        }


        customerIdTxt.setText(String.valueOf(selectedCustomer.getCustomer_ID()));
        nameTxt.setText(selectedCustomer.getCustomer_Name());
        addressTxt.setText(selectedCustomer.getAddress());
        customerPostalCode.setText(selectedCustomer.getPostal_Code());
        countryComboBox.setItems(CountryUtil.getAllCountries());
        customerPhone.setText(selectedCustomer.getPhone());

    }

    /**
     * Action event that saves the updated customer data to the customer table in the database and the GUI once the
     * " Save Update Button is pushed". It does not save unless all fields are filled. There's a alert if all fields are
     * not filled.
     *
     * @param actionEvent actionEvent
     */
    public void saveUpdateButtonPushed(ActionEvent actionEvent) throws SQLException {

        if (customerIdTxt.getText().isEmpty()) {
            Alert invalidEntry = new Alert(Alert.AlertType.ERROR);
            invalidEntry.setContentText("Must select a customer to be updated.");
            invalidEntry.showAndWait();
            return;
        }

        if (nameTxt.getText().isEmpty() || addressTxt.getText().isEmpty() || customerPostalCode.getText().isEmpty() || customerPhone.getText().isEmpty()) {
            Alert invalidEntry = new Alert(Alert.AlertType.ERROR);
            invalidEntry.setContentText("All fields must be filled.");
            invalidEntry.showAndWait();
            return;
        }

        if (countryComboBox.getValue() == null && firstLevelDivisionComboBox.getValue() == null) {
            try {
                int Customer_ID = Integer.parseInt(customerIdTxt.getText());
                String Customer_Name = nameTxt.getText();
                String Address = addressTxt.getText();
                String Postal_Code = customerPostalCode.getText();
                String Phone = customerPhone.getText();
                String Created_By = "Lynn";
                Timestamp Last_Update = Timestamp.valueOf(LocalDateTime.now());
                String Last_Updated_By = "Lynn";


                CustomerUtil.updatePartialCustomerTable(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Created_By, Last_Update, Last_Updated_By);
                addCustomerTable.setItems(CustomerUtil.getAllCustomers());

                customerIdTxt.clear();
                nameTxt.clear();
                addressTxt.clear();
                countryComboBox.setPromptText(null);
                firstLevelDivisionComboBox.setPromptText(null);
                customerPostalCode.clear();
                customerPhone.clear();


            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        } else {
            try {
                int Customer_ID = Integer.parseInt(customerIdTxt.getText());
                String Customer_Name = nameTxt.getText();
                String Address = addressTxt.getText();
                String Postal_Code = customerPostalCode.getText();
                String Phone = customerPhone.getText();
                Timestamp Create_Date = Timestamp.valueOf(LocalDateTime.now());
                String Created_By = "Lynn";
                Timestamp Last_Update = Timestamp.valueOf(LocalDateTime.now());
                String Last_Updated_By = "Lynn";
                int Division_ID = firstLevelDivisionComboBox.getValue().getDbDivisionId();

                CustomerUtil.updateCustomerTable(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID);
                addCustomerTable.setItems(CustomerUtil.getAllCustomers());


                customerIdTxt.clear();
                nameTxt.clear();
                addressTxt.clear();
                countryComboBox.getSelectionModel().clearSelection();
                firstLevelDivisionComboBox.getSelectionModel().clearSelection();
                customerPostalCode.clear();
                customerPhone.clear();


            } catch (NullPointerException n) {
                Alert allFields = new Alert(Alert.AlertType.ERROR);
                allFields.setTitle("ERROR");
                allFields.setContentText("Must select a State/Province");
                allFields.showAndWait();
                return;
            }
        }
    }

    /**
     * Action event to take the user back to the Menu Page once the "Back To Menu Page"is pushed.
     *
     * @param actionEvent actionEvent
     */
    public void backToMenuPagePushed(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 500.0));
        stage.show();
    }

    public void onSearchByCustomerName(ActionEvent actionEvent) {
        String partialCustomerName = searchByCustomerName.getText().toLowerCase();
        ObservableList<CustomerModel> customerFound = CustomerUtil.lookUpCustomer(partialCustomerName);

        if (customerFound.size() == 0) {
            Alert custNotFound = new Alert(Alert.AlertType.INFORMATION);
            custNotFound.setTitle("INFORMATION");
            custNotFound.setContentText("No matches found");
            custNotFound.showAndWait();
        } else

            addCustomerTable.setItems(customerFound);
    }
}








