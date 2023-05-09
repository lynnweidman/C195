package controller;

import Lambda.Lambda2;
import Lambda.Lambda3;
import Lambda.LambdaInterface;
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
import javafx.stage.Stage;
import model.*;
import util.*;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/** Class that updates appointments in the database and the GUI. */
public class UpdateAnAppointmentController implements Initializable {

    public TextField appointmentIdTXT;
    public TextField titleTXT;
    public TextField descriptionTXT;
    public TextField locationTXT;
    public TextField typeTXT;
    public TextField customerIdTXT;
    public TextField userIdTXT;
    public TextField userNameTXT;
    public TableView appointmentsTable;
    public TableColumn appointmentsColumn;
    public TableColumn titleColumn;
    public TableColumn descriptionColumn;
    public TableColumn locationColumn;
    public TableColumn typeColumn;
    public TableColumn startColumn;
    public TableColumn endColumn;
    public TableColumn create_DateColumn;
    public TableColumn created_By;
    public TableColumn last_Update;
    public TableColumn last_Updated_By;
    public TableColumn customer_ID;
    public TableColumn user_ID;
    public TableColumn Contact_ID;
    public TextField customerNameTxt;
    public TextField customerTypeTxt;

    @FXML
    public DatePicker datePicker;
    @FXML
    public ComboBox<String> hourComboBox;
    @FXML
    public ComboBox<String> minutesComboBox;
    @FXML
    public DatePicker EndDatePicker;
    @FXML
    public ComboBox<String> endHourComboBox;
    @FXML
    public ComboBox<String> endMinuteComboBox;
    @FXML
    public ComboBox<ContactsModel> contactComboBox;
    private String userId;
    public static ObservableList<TypeCustomerModel> allCustomersWithType = FXCollections.observableArrayList();


    private final ObservableList<String> timeHour = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
    private final ObservableList<String> timeMinutes = FXCollections.observableArrayList("00", "15", "30", "45");
    private final ObservableList<String> emptyList = FXCollections.observableArrayList("");



    /**
     * Initializes the Update Appointments page and populates the appointments table in the GUI. The name combo box is
     * filled with a list of customer names.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            appointmentsColumn.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
            startColumn.setCellValueFactory(new PropertyValueFactory<>("ldtStart"));
            endColumn.setCellValueFactory(new PropertyValueFactory<>("ldtEnd"));
            create_DateColumn.setCellValueFactory(new PropertyValueFactory<>("Create_Date"));
            created_By.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
            last_Update.setCellValueFactory(new PropertyValueFactory<>("Last_Update"));

            last_Updated_By.setCellValueFactory(new PropertyValueFactory<>("Last_Updated_By"));
            customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            user_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
            Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));


            appointmentsTable.setItems(AddAppointmentsUtil.getAllAppointmentsLDT());
            hourComboBox.setItems(timeHour);
            minutesComboBox.setItems(timeMinutes);
            endHourComboBox.setItems(timeHour);
            endMinuteComboBox.setItems(timeMinutes);

            try {
                contactComboBox.setItems(ContactsUtil.getAllContacts());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }

    }

        /**
         * Action event that populates the updatable fields with the selected appointment upon pressing the "Select Appointment to
         * Update" button. One of the two radio buttons must be selected. They allow you to choose to update an appointment with or without
         * updating the date and time. If not updating the date and time, those fields are disabled. If updating the time, both must be reset.
         * There are Alerts if no appointment is selected or if neither radio button is selected.
         *
         * @param actionEvent actionEvent
         */
        public void selectAppointmentToUpdatePushed(ActionEvent actionEvent) throws SQLException, NumberFormatException {

       AddAppointmentsModel selectedAppointment = (AddAppointmentsModel) appointmentsTable.getSelectionModel().getSelectedItem();
        int firstLevelDivisionId = FirstLevelDivisionsUtil.getTheDivisionByCustomerId(selectedAppointment.getCustomer_ID());


            if (selectedAppointment == null) {

            Alert noSelectedAppointment = new Alert(Alert.AlertType.ERROR);
            noSelectedAppointment.setTitle("ERROR");
            noSelectedAppointment.setContentText("You have not selected an appointment to update");
            noSelectedAppointment.showAndWait();
        }


            appointmentIdTXT.setText(String.valueOf(selectedAppointment.getAppointment_ID()));
            customerNameTxt.setText(String.valueOf(NamesUtil.getCustomerName(selectedAppointment.getAppointment_ID())));
            userNameTXT.setText(String.valueOf(NamesUtil.getUserName(selectedAppointment.getAppointment_ID())));
            titleTXT.setText(selectedAppointment.getTitle());
            descriptionTXT.setText(selectedAppointment.getDescription());
            locationTXT.setText(selectedAppointment.getLocation());
            typeTXT.setText(selectedAppointment.getType());

            hourComboBox.setValue(String.valueOf(selectedAppointment.getLdtStart().getHour()));
            minutesComboBox.setValue(String.valueOf(selectedAppointment.getLdtStart().getMinute()));
              if ( minutesComboBox.getValue().equals("0")) {
                  minutesComboBox.setValue("00");
              }

            endHourComboBox.setValue(String.valueOf(selectedAppointment.getLdtEnd().getHour()));
            endMinuteComboBox.setValue(String.format(String.valueOf(selectedAppointment.getLdtStart().getMinute()), DateTimeFormatter.ofPattern("mm")));
               if ( endMinuteComboBox.getValue().equals("0")) {
                   endMinuteComboBox.setValue("00");
               }

            userIdTXT.setText(String.valueOf(selectedAppointment.getUser_ID()));
            customerIdTXT.setText(String.valueOf(selectedAppointment.getCustomer_ID()));
            datePicker.setValue(selectedAppointment.getLdtStart().toLocalDate());
            datePicker.setPromptText(selectedAppointment.getLdtStart().toLocalDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
            EndDatePicker.setValue(selectedAppointment.getLdtEnd().toLocalDate());
            EndDatePicker.setPromptText(selectedAppointment.getLdtStart().toLocalDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));


            for (int i = 0; i < contactComboBox.getItems().size(); i++) {
                ContactsModel c = contactComboBox.getItems().get(i);
                if (c.getContact_ID() == selectedAppointment.getContact_ID()) {

                    contactComboBox.setValue(c);
                }
            }

            if (firstLevelDivisionId < 55) {
                customerTypeTxt.setText("US Customer");
            }
            else {
                customerTypeTxt.setText("Non US Customer");
            }
        }



    /**
     * Saves updated appointment data in the appointments table in the database and the GUI. There's a radio button choice to update
     * appointments with a change of date and time or without a change of date and time. If date and time are not changed the original date
     * and time stay the same. If they choose the radio button to change the date and time they must both be reset. There are validations that
     * all fields are filled, the appointments are set within business hours (8:00am-10:00pm Mon-Fri EST), and there are no overlapping appointments.
     * The time is stored in the database as UTC.
     *
     * <p><b>There are 2 lambdas.
     * Lambda to check if there is already appointment scheduled at the same time. This lambda provides a very efficient way to
     * insert ldtStartDatePicker (the Start value from the datePicker) and ldtEndDatePicker (the End value from the datePicker) into
     * the parameters s and e and compare them to dbStart and dbEnd (the Start and End values from the database). Using this lambda
     * shortened my code and reduced many brackets.
     * <p>
     * Lambda to check if the requested appointment time is outside of business our per EST. This lambda provides a very efficient way to
     * insert estStartTime and estEndTime (the Start and End time collected from the datePicker) into
     * the parameters am and pm and compares them to startOfDay and endOfDay (the given 8:00 and 10:00 hrs respectively). Using this lambda
     * shortened my code and reduced many brackets.
     *
     * </b></p>
     *
     * @param actionEvent actionEvent
     */
    public void updateAppointmentButtonPressed(ActionEvent actionEvent) throws SQLException {
        AddAppointmentsModel selectedAppointment = (AddAppointmentsModel) appointmentsTable.getSelectionModel().getSelectedItem();

        if (appointmentIdTXT.getText().isEmpty()) {
            Alert noSelection = new Alert(Alert.AlertType.ERROR);
            noSelection.setContentText("You have not selected an appointment to update.");
            noSelection.showAndWait();
            return;

        }
        if (customerNameTxt.getText().isEmpty() || customerIdTXT.getText().isEmpty() || userNameTXT.getText().isEmpty() || userIdTXT.getText().isEmpty() || descriptionTXT.getText().isEmpty()
                || locationTXT.getText().isEmpty() || typeTXT.getText().isEmpty() || titleTXT.getText().isEmpty()) {
            Alert emptyField = new Alert(Alert.AlertType.ERROR);
            emptyField.setContentText("You cannot leave any fields empty.");
            emptyField.showAndWait();
            return;
        }


        //Get Start values.
        int selectedId = Integer.parseInt(appointmentIdTXT.getText());
        LocalDate date = datePicker.getValue();
        LocalTime timeHour = LocalTime.parse(hourComboBox.getValue() + ":" + minutesComboBox.getValue());
        LocalTime time = LocalTime.parse(timeHour + ":00");
        DayOfWeek day = date.getDayOfWeek();

        //Get End values.
        LocalDate endDate = EndDatePicker.getValue();
        LocalTime endTimeHour = LocalTime.parse(endHourComboBox.getValue() + ":" + endMinuteComboBox.getValue());
        LocalTime endTime = LocalTime.parse(endTimeHour + ":00");
        DayOfWeek endDay = endDate.getDayOfWeek();


        LocalDateTime startDateAndTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                timeHour.getHour(), timeHour.getMinute(), time.getSecond());

        LocalDateTime endDateAndTime = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(),
                endTimeHour.getHour(), endTimeHour.getMinute(), endTime.getSecond());

        //convert start and end from UTC to Timestamp to Insert into the database.
        Timestamp startDatePicker = TimeZoneUtil.getStartUTC(date, timeHour, time);
        Timestamp endDatePicker = TimeZoneUtil.getStartUTC(endDate, endTimeHour, endTime);
        LocalDateTime ldtStartDatePicker = startDatePicker.toLocalDateTime();
        LocalDateTime ldtEndDatePicker = endDatePicker.toLocalDateTime();

        //Converting datePicker to zones and LocalDateTime.
        ZonedDateTime zonedStart = startDateAndTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedEnd = endDateAndTime.atZone(ZoneId.systemDefault());

        ZonedDateTime estStart = zonedStart.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime thisLDTinESTStart = estStart.toLocalDateTime();

        ZonedDateTime estEnd = zonedEnd.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime thisLDTinESTEnd = estEnd.toLocalDateTime();

        LocalTime estStartTime = LocalTime.of(thisLDTinESTStart.getHour(), thisLDTinESTStart.getMinute());
        LocalTime estEndTime = LocalTime.of(thisLDTinESTEnd.getHour(), thisLDTinESTEnd.getMinute());


        LocalTime startOfDay = LocalTime.of(8, 0);
        LocalTime endOfDay = LocalTime.of(20, 0);


        for (AddAppointmentsModel apps : AddAppointmentsUtil.allAppointments) {

            LocalDateTime dbStart = apps.getLdtStart();
            LocalDateTime dbEnd = apps.getLdtEnd();
            int getDatabaseId = apps.getAppointment_ID();

            //Lambda to check if there is already appointment scheduled at the same time.//
               /* Lambda2 checkOverlap = (s, e) -> (s.isAfter(dbStart) && s.isBefore(dbEnd)) || (s.isAfter(dbStart) && s.isBefore(dbEnd) && e.isAfter(dbEnd) || (s.isBefore(dbStart) && e.isAfter(dbStart) && e.isBefore(dbEnd)) || (s.equals(dbStart)));
                if (checkOverlap.checkOverLapHours(ldtStartDatePicker, ldtEndDatePicker)) {

                    Alert invalidTime = new Alert(Alert.AlertType.ERROR);
                    invalidTime.setContentText("That time overlaps with an already scheduled appointment.");
                    updatedDateTimeTXT.clear();
                    updateEndDateTime.clear();
                    invalidTime.showAndWait();
                    System.out.println(ldtStartDatePicker + " ldtStartDatePicker" + ldtEndDatePicker + " ldtEndDatePicker"  + dbStart);

                    return;
                }*/

            Lambda2 checkOverlap = (s, e) -> (s.isAfter(dbStart) && s.isBefore(dbEnd)) || (s.isAfter(dbStart) && s.isBefore(dbEnd) && e.isAfter(dbEnd) || (s.isBefore(dbStart) && e.isAfter(dbStart) && e.isBefore(dbEnd)) || (s.equals(dbStart)));
            if (checkOverlap.checkOverLapHours(ldtStartDatePicker, ldtEndDatePicker))
                if (selectedId != getDatabaseId) {

                    Alert invalidTime = new Alert(Alert.AlertType.ERROR);
                    invalidTime.setContentText("That time overlaps with an already scheduled appointment.");
                    invalidTime.showAndWait();
                   // System.out.println(ldtStartDatePicker + " ldtStartDatePicker" + ldtEndDatePicker + " ldtEndDatePicker" + dbStart);

                    return;
                }
        }

        //Lambda to check if the appointment times are reasonable. ex. end is not before start.
        Lambda3 checkReasonable = (s, e) -> (e.isBefore(s) || (s.isAfter(e) || (s.equals(e) || (e.equals(s) || (s.isBefore(LocalDateTime.now(ZoneId.systemDefault())))))));
        if (checkReasonable.checkReasonableHours(ldtStartDatePicker, ldtEndDatePicker)) {
            Alert invalidTime = new Alert(Alert.AlertType.ERROR);
            invalidTime.setContentText("Make sure the start time is not after the end time.\nMake sure the end time is not before the start time.\nMake sure the start time is not before today.\nMake sure the start and end time are not the same.");
            invalidTime.showAndWait();

            return;
        }

        //Lambda to check if the requested appointment time is outside of business our per EST.

        LambdaInterface checkTime = (am, pm) -> am.isBefore(startOfDay) || pm.isAfter(endOfDay);
        if (checkTime.checkBusinessHours(estStartTime, estEndTime)) {
            Alert invalidTime = new Alert(Alert.AlertType.ERROR);
            invalidTime.setContentText("Appointment time must be within business hours of 8:00am - 10:00pm.");
            invalidTime.showAndWait();
            return;
        }


        //Code to not allow schedules on the weekend.
        if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY) || endDay.equals(DayOfWeek.SATURDAY) || endDay.equals(DayOfWeek.SUNDAY)) {

            Alert invalidTime = new Alert(Alert.AlertType.ERROR);
            invalidTime.setContentText("Appointment time must be within business days of Monday - Friday.");
            invalidTime.showAndWait();
            return;
        }


        if (datePicker.getValue() == null) {
            Alert invalidTime = new Alert(Alert.AlertType.ERROR);
            invalidTime.setContentText("Must choose Date,Hour and Minutes to update an appointment.");
            invalidTime.showAndWait();
            return;
        }

        if (hourComboBox.getValue() == null) {
            Alert invalidTime = new Alert(Alert.AlertType.ERROR);
            invalidTime.setContentText("Must choose Date,Hour and Minutes to update an appointment.");
            invalidTime.showAndWait();
            return;
        }

        if (minutesComboBox.getValue() == null) {
            Alert invalidTime = new Alert(Alert.AlertType.ERROR);
            invalidTime.setContentText("Must choose Date,Hour and Minutes to update an appointment.");
            invalidTime.showAndWait();
            return;
        }


        if (endHourComboBox.getValue() == null) {
            Alert invalidTime = new Alert(Alert.AlertType.ERROR);
            invalidTime.setContentText("Must choose End Date,Hour and Minutes  to update an appointment.");
            invalidTime.showAndWait();
            return;
        }
        if (endMinuteComboBox.getValue() == null) {
            Alert invalidTime = new Alert(Alert.AlertType.ERROR);
            invalidTime.setContentText("Must choose End Date,Hour and Minutes  to update an appointment.");
            invalidTime.showAndWait();
            return;
        }
        if (EndDatePicker.equals(null)) {
            Alert invalidTime = new Alert(Alert.AlertType.ERROR);
            invalidTime.setContentText("Must choose End Date,Hour and Minutes  to update an appointment.");
            invalidTime.showAndWait();
            return;
        }
        try {


            int Appointment_ID = Integer.parseInt((appointmentIdTXT.getText()));
            String Title = titleTXT.getText();
            String Description = descriptionTXT.getText();
            String Location = locationTXT.getText();
            String Type = typeTXT.getText();
            Timestamp start = startDatePicker;
            Timestamp end = endDatePicker;

            Timestamp Last_Update = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println(userNameTXT.getText());
            String Last_Updated_By = userNameTXT.getText();
            int Customer_ID = Integer.parseInt(customerIdTXT.getText());
            int User_ID = Integer.parseInt(userIdTXT.getText());
            int Contact_ID = contactComboBox.getSelectionModel().getSelectedItem().getContact_ID();

            String customerType = customerTypeTxt.getText();

            UpdateAppointmentUtil.updateAppointmentsTable(Appointment_ID, Title, Description,
                    Location, Type, start, end, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID);


            appointmentsTable.setItems(AddAppointmentsUtil.getAllAppointmentsLDT());

            //Adding to the TypeCustomerModel- polymorphic example.
            TypeCustomerModel customersWithType = new TypeCustomerModel(Appointment_ID, Title, Description, Location, Type, start.toLocalDateTime(), end.toLocalDateTime(), Last_Update.toLocalDateTime(), Last_Updated_By, Customer_ID, User_ID, Contact_ID, customerType);
            allCustomersWithType.add(customersWithType);

            userNameTXT.clear();
            customerNameTxt.clear();
            appointmentIdTXT.clear();
            titleTXT.clear();
            descriptionTXT.clear();
            locationTXT.clear();
            typeTXT.clear();
            datePicker.setValue(null);
            EndDatePicker.setValue(null);
            hourComboBox.setValue(null);
            minutesComboBox.setValue(null);
            endHourComboBox.setValue(null);
            endMinuteComboBox.setValue(null);
            userIdTXT.clear();
            customerIdTXT.clear();
            contactComboBox.setValue(null);
            customerTypeTxt.clear();

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }



    /**
     * Deletes the selected appoointment upon pressing the "Delete Appointment Button" There are alerts if no appointment is selected.
     *
     * @param actionEvent actionEvent
     */
    public void deleteAppointmentButtonPressed(ActionEvent actionEvent) throws SQLException {
        AddAppointmentsModel selectedAppointment = (AddAppointmentsModel) appointmentsTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {

            Alert noSelectedAppointment = new Alert(Alert.AlertType.ERROR);
            noSelectedAppointment.setTitle("ERROR");
            noSelectedAppointment.setContentText("You have not selected an appointment to cancel");
            noSelectedAppointment.showAndWait();

        } else {
            try {
                int appointmentID = selectedAppointment.getAppointment_ID();
                String appointmentType = selectedAppointment.getType();
                Alert deleteAlert = new Alert(Alert.AlertType.WARNING);
                deleteAlert.setTitle("WARNING");
                deleteAlert.setContentText("Are you sure you want to cancel Appointment ID: " + appointmentID + " of Type: " + appointmentType + "?");
                deleteAlert.showAndWait();

            } catch (NullPointerException n) {
                Alert noSelectedAppointment = new Alert(Alert.AlertType.ERROR);
                noSelectedAppointment.setTitle("ERROR");
                noSelectedAppointment.setContentText("You have not selected an appointment to cancel");
                noSelectedAppointment.showAndWait();
            }

            int appointmentID = selectedAppointment.getAppointment_ID();
            UpdateAppointmentUtil.deleteAppointment(appointmentID);
            Alert deletedAppt = new Alert(Alert.AlertType.CONFIRMATION);
            deletedAppt.setContentText("Appointment deleted");
            deletedAppt.showAndWait();

            System.out.println("Appointment deleted");
            //appointmentsTable.setItems(UpdateAppointmentUtil.getAllAppointmentsLDT());//fixme
            appointmentsTable.setItems(AddAppointmentsUtil.getAllAppointmentsLDT());

        }
    }


    /**
     * Action event that switches the user to the "Menu Page" page.
     *
     * @param actionEvent actionEvent
     */
    public void goBackToMenuPageButtonPressed(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 900.0));
        stage.show();
    }

    public void contactComboBoxSelected(ActionEvent actionEvent) {

    }


    public void pressToGetValidUserIDPressed(ActionEvent actionEvent) throws SQLException {

        ObservableList<UserModel> u = UserUtil.getAllUsers();
        String userName = userNameTXT.getText();
        boolean match = false;

        for (int i = 0; i < u.size(); i++) {
            UserModel user = u.get(i);
            if (userName.equals(user.getUserName())) {
                match = true;
                userIdTXT.setText(String.valueOf(user.getUser_ID()));
                break;

            } else {
                Alert invalidUserName = new Alert(Alert.AlertType.ERROR);
                invalidUserName.setContentText("Must enter a valid User Name.");
                invalidUserName.showAndWait();
                return;

            }
        }
    }
}









