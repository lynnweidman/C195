package controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.*;

import java.io.IOException;
import java.sql.SQLException;


public class AppointmentViewsController implements Initializable{

  @FXML  public DatePicker datePickerWithWeek;
    public RadioButton appointmentByMonthRadio;
    public ToggleGroup appointmentViewToggle;
    public RadioButton appointmentByWeekRadio;
    public TableView appointmentsViewTable;
    public TableColumn WeekColumn;
    public TableColumn AppointmentIdColumn;
    public TableColumn TitleColumn;
    public TableColumn DescriptionColumn;
    public TableColumn LocationColumn;
    public TableColumn ContactColumn;
    public TableColumn TypeColumn;
    public TableColumn StartColumn;
    public TableColumn EndColumn;
    public TableColumn CustomerIdColumn;
    public TableColumn UserIdColumn;
   @FXML public ComboBox<String> weekNumberComboBox;
   @FXML public ComboBox<String> monthComboBox;
    public Button viewMonth;
    public Button viewWeek;


    private final ObservableList<String> weekNumbers = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
             "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53");

    private final ObservableList<String> months = FXCollections.observableArrayList( "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        datePickerWithWeek.setShowWeekNumbers(true);
        weekNumberComboBox.setItems(weekNumbers);
        monthComboBox.setItems(months);
        datePickerWithWeek.getEditor().setDisable(true);//fixme not sure this works

        WeekColumn.setCellValueFactory(new PropertyValueFactory<>("Week"));
        AppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        ContactColumn.setCellValueFactory(new PropertyValueFactory<>("Contact_Name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        StartColumn.setCellValueFactory(new PropertyValueFactory<>("ldtStart"));
        EndColumn.setCellValueFactory(new PropertyValueFactory<>("ldtEnd"));
        CustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        UserIdColumn.setCellValueFactory(new PropertyValueFactory<>("User_ID"));

        if (appointmentByMonthRadio.isSelected()) {
           viewWeek.isDisabled();
        }
        if (appointmentByWeekRadio.isSelected()) {
            viewMonth.isDisabled();
        }
    }


        public void datePickerWithWeekSelected(ActionEvent actionEvent) {

        }

    public void appointmentByMonthRadioOn(ActionEvent actionEvent) {
        WeekColumn.setText("Month");
    }

    public void appointmentByWeekRadioOn(ActionEvent actionEvent) {
        WeekColumn.setText("Week");
    }

    public void viewMonthPressed(ActionEvent actionEvent) throws SQLException {

        if (appointmentByMonthRadio.equals(null)) {

            Alert invalidRadio = new Alert(AlertType.ERROR);
            invalidRadio.setContentText("Please choose the correct choice at the top to view appointments by month or week.");
            invalidRadio.showAndWait();
            return;
        }

        if (appointmentByWeekRadio.isSelected()) {
            Alert invalidRadio = new Alert(AlertType.ERROR);
            invalidRadio.setContentText("Please choose the correct choice at the top to view appointments by month or week.");
            invalidRadio.showAndWait();
            return;
        }
        if (appointmentByMonthRadio.isSelected()) {
                if (monthComboBox.getSelectionModel().isEmpty()) {
                    Alert invalidMonth = new Alert(AlertType.ERROR);
                    invalidMonth.setContentText("Please choose a month .");
                    invalidMonth.showAndWait();
                    return;
                } else {

                }
            int selectedMonthNumber = Integer.parseInt(monthComboBox.getValue());
            appointmentsViewTable.setItems(AppointmentViewsUtil.getAppointmentByMonth(selectedMonthNumber));
        }
        else {
            Alert invalidRadio = new Alert(AlertType.ERROR);
            invalidRadio.setContentText("Please choose the correct choice at the top to view appointments by month or week.");
            invalidRadio.showAndWait();
            return;
        }
    }

    public void viewWeekPressed(ActionEvent actionEvent) throws SQLException {
       // int selectedWeekNumber = Integer.parseInt(weekNumberComboBox.getValue());

        if (appointmentByWeekRadio.equals(null)) {
            Alert invalidRadio = new Alert(AlertType.ERROR);
            invalidRadio.setContentText("Please choose the correct choice at the top to view appointments by month or week.");
            invalidRadio.showAndWait();
            return;
        }

        if (appointmentByMonthRadio.isSelected()) {
            Alert invalidRadio = new Alert(AlertType.ERROR);
            invalidRadio.setContentText("Please choose the correct choice at the top to view appointments by month or week.");
            invalidRadio.showAndWait();
            return;
        }
        if (appointmentByWeekRadio.isSelected()) {
            if (weekNumberComboBox.getSelectionModel().isEmpty()) {
                Alert invalidMonth = new Alert(AlertType.ERROR);
                invalidMonth.setContentText("Please choose a week number.");
                invalidMonth.showAndWait();
                return;
            } else {

                int selectedWeekNumber = Integer.parseInt(weekNumberComboBox.getValue());
                appointmentsViewTable.setItems(AppointmentViewsUtil.getAppointmentByWeek(selectedWeekNumber));
            }
        }

        else {
            Alert invalidRadio = new Alert(AlertType.ERROR);
            invalidRadio.setContentText("Please choose the correct choice at the top to view appointments by month or week.");
            invalidRadio.showAndWait();
            return;
        }

    }


    public void monthComboBoxSelected(ActionEvent actionEvent) {
    }


    public void backToMenuPagePushed(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 500.0));
        stage.show();
    }
}

