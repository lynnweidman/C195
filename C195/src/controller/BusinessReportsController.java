package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** Class that creates table to display custom business reports. */
public class BusinessReportsController implements Initializable {

    public TableView appointmentsByTypeAndMonthTable;
    public TableColumn numberColumn;
    public TableColumn typeAppointmentsColumn;
    public TableColumn monthColumn;
    public TableView contactScheduleTable;
    public TableColumn contactColumn;
    public TableColumn titleColumn;
    public TableColumn appointmentIdColumn;
    public TableColumn typeScheduleColumn;
    public TableColumn startColumn;
    public TableColumn endColumn;
    public TableColumn customerIdColumn;
    public TableColumn totalColumn;
    public TableView appointmentsPerDayTable;
    public TableColumn appointmentsNumberCol;
    public TableColumn appointmentsDateCol;

    /** Initializes the Business Reports page. Provides buttons to populate tables with current data from the database
     @param url url
     @param resourceBundle resourceBundle */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        totalColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        typeAppointmentsColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));

        contactColumn.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        typeScheduleColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("ldtStart"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("ldtStart"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));

        appointmentsNumberCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
        appointmentsDateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));

    }

    /** Action event to populate the "Appointments By Type And Month Table".
     @param actionEvent actionEvent */
    public void totalTypeReportPushed(ActionEvent actionEvent) {
        try {
            appointmentsByTypeAndMonthTable.setItems(BusinessReportsUtil.getAppointmentByTypeAndMonth());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** Action event to populate the "Contact Schedule Table".
     @param actionEvent actionEvent */
    public void contactSchedulePushed(ActionEvent actionEvent) {
        try {
            contactScheduleTable.setItems(BusinessReportsUtil.getContactSchedule());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    /** Action event to populate the "Number Of Appointments Per Day" table.
     @param actionEvent actionEvent */
    public void numberOfAppointmentsPerDayPushed(ActionEvent actionEvent) {

        try {
            appointmentsPerDayTable.setItems(BusinessReportsUtil.getNumberOfAppointmentsPerDay());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** Action event that switches the user to the "Menu Page" page.
     @param actionEvent actionEvent */
    public void backToMenuPagePushed(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 500.0));
        stage.show();
    }
}
