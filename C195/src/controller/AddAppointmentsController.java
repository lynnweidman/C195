package controller;
import java.net.URL;
import java.sql.ResultSet;
import java.time.*;
import java.util.ResourceBundle;
import Lambda.Lambda2;
import Lambda.Lambda3;
import Lambda.LambdaInterface;
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
import model.*;
import model.ContactsModel;
import util.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class AddAppointmentsController implements Initializable {


    public ComboBox<NamesModel> nameComboBox;
    public TextField appointmentIdTXT;
    public TextField titleTXT;
    public TextField descriptionTXT;
    public TextField locationTXT;
    public TextField contactTXT;
    public TextField typeTXT;
    public TextField startTXT;
    public TextField endTXT;
    public TextField customerIdTXT;
    public TextField userIdTXT;
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
    public TableColumn contact_ID;
    @FXML
    public DatePicker datePicker;
    @FXML
    public ComboBox<ContactsModel> contactComboBox;
    public javafx.scene.control.TextField userNameTXT;
    public ComboBox<String> timeComboBox;
    public ComboBox<String> minutesComboBox;
    public Button checkUserName;
    @FXML
    public DatePicker endDatePicker;
    @FXML
    public ComboBox<String> endHourCombo;
    @FXML
    public ComboBox<String> endMinutesCombo;


    private final ObservableList<String> timeHours = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
    private final ObservableList<String> timeMinutes = FXCollections.observableArrayList("00", "15", "30", "45");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set the appointment ID and auto increment it.
        try {
            appointmentIdTXT.setText(String.valueOf(AddAppointmentsUtil.getAppointment_ID()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//Set the names combo box with names from the appointments database.
        try {
            nameComboBox.setItems(NamesUtil.getCustomerNamesAndID());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (NullPointerException n) {
            //n.printStackTrace();
        }

        try {
            contactComboBox.setItems(ContactsUtil.getAllContacts());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        timeComboBox.setItems(timeHours);
        minutesComboBox.setItems(timeMinutes);
        endHourCombo.setItems(timeHours);
        endMinutesCombo.setItems(timeMinutes);


        appointmentsColumn.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("ldtStart"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("ldtEnd"));
        create_DateColumn.setCellValueFactory(new PropertyValueFactory<>("zonedCreateDate"));
        created_By.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
        last_Update.setCellValueFactory(new PropertyValueFactory<>("zonedLastUpdate"));

        last_Updated_By.setCellValueFactory(new PropertyValueFactory<>("Last_Updated_By"));
        customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        user_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));


        try {
            appointmentsTable.setItems(AddAppointmentsUtil.getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void checkUserNameButtonPressed(ActionEvent actionEvent) throws SQLException {

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


    /** Adds appointment to the appointments table in the database and the GUI. Validates that no fields are empty. Converts time to UTC.
     Validates that appointments are made within business hours (8:00am and 10:00pm Mon-Fri EST), and that there are no overlapping
     appointments.
     <p><b>There are 2 lambdas.
     Lambda to check if there is already appointment scheduled at the same time. This lambda provides a very efficient way to
     insert ldtStartDatePicker (the Start value from the datePicker) and ldtEndDatePicker (the End value from the datePicker) into
     the parameters s and e and compare them to dbStart and dbEnd (the Start and End values from the database). Using this lambda
     shortened my code and reduced many brackets.

     Lambda to check if the requested appointment time is outside of business our per EST. This lambda provides a very efficient way to
     insert estStartTime and estEndTime (the Start and End time collected from the datePicker) into
     the parameters am and pm and compares them to startOfDay and endOfDay (the given 8:00 and 10:00 hrs respectively). Using this lambda
     shortened my code and reduced many brackets.

     </b></p>
     @param actionEvent actionEvent */
    public void addAppointmentButtonPressed(ActionEvent actionEvent) throws SQLException, IOException {

        if (userIdTXT.getText().isEmpty()) {
            Alert invalidEntry = new Alert(AlertType.ERROR);
            invalidEntry.setContentText("Must enter a valid User Name the press the \"Press to get Valid User Id button\".");
            invalidEntry.showAndWait();
            return;
        }

        if (nameComboBox == null || customerIdTXT.getText().isEmpty()) {
            Alert invalidEntry = new Alert(AlertType.ERROR);
            invalidEntry.setContentText("Must select a Customer Name from the dropdown box.");
            invalidEntry.showAndWait();
            return;
        }

        if (contactComboBox == null) {
            Alert invalidEntry = new Alert(AlertType.ERROR);
            invalidEntry.setContentText("Must select a Contact Name from the dropdown box.");
            invalidEntry.showAndWait();
            return;
        }

        if (datePicker.getValue() == null) {
            Alert invalidTime = new Alert(AlertType.ERROR);
            invalidTime.setContentText("Must select a Start Date from the DatePicker.");
            invalidTime.showAndWait();
            return;
        }

        if (datePicker.getValue().isBefore(LocalDate.now())) {
            Alert invalidTime = new Alert(AlertType.ERROR);
            invalidTime.setContentText("Cannot choose a Start Date in the past.");
            invalidTime.showAndWait();
            return;
        }

        if (timeComboBox.getValue() == null) {
            Alert invalidTime = new Alert(AlertType.ERROR);
            invalidTime.setContentText("Must choose both Start Hour and Start Minutes to make an appointment.");
            invalidTime.showAndWait();
            return;
        }

        if (minutesComboBox.getValue() == null) {
            Alert invalidTime = new Alert(AlertType.ERROR);
            invalidTime.setContentText("Must choose both Start Hour and Start Minutes to make an appointment.");
            invalidTime.showAndWait();
            return;
        }

        if (endDatePicker.getValue() == null) {
            Alert invalidTime = new Alert(AlertType.ERROR);
            invalidTime.setContentText("Must select an End Date from the DatePicker.");
            invalidTime.showAndWait();
            return;
        }

        if (endDatePicker.getValue().isBefore(LocalDate.now())) {
            Alert invalidTime = new Alert(AlertType.ERROR);
            invalidTime.setContentText("Cannot choose an  End Date in the past.");
            invalidTime.showAndWait();
            return;
        }

        if (endHourCombo.getValue() == null) {
            Alert invalidTime = new Alert(AlertType.ERROR);
            invalidTime.setContentText("Must choose both End Hour and End Minutes to make an appointment.");
            invalidTime.showAndWait();
            return;
        }

        if (endMinutesCombo.getValue() == null) {
            Alert invalidTime = new Alert(AlertType.ERROR);
            invalidTime.setContentText("Must choose both End Hour and End Minutes to make an appointment.");
            invalidTime.showAndWait();
            return;
        }


        if (titleTXT.getText().isEmpty() || descriptionTXT.getText().isEmpty() || locationTXT.getText().isEmpty() || typeTXT.getText().isEmpty()) {
            Alert invalidEntry = new Alert(AlertType.ERROR);
            invalidEntry.setContentText("All fields must be filled.");
            invalidEntry.showAndWait();
            return;
        }
// Getting the Start date and time values.
        LocalDate date = datePicker.getValue();
        LocalTime timeHour = LocalTime.parse(timeComboBox.getValue() + ":" + minutesComboBox.getValue());
        LocalTime time = LocalTime.parse(timeHour + ":00");
        DayOfWeek day = date.getDayOfWeek();


        //Getting the End date and time values.
        LocalDate endDate = endDatePicker.getValue();
        LocalTime endTimeHour = LocalTime.parse(endHourCombo.getValue() + ":" + endMinutesCombo.getValue());
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

        //Convert Create_Date, and Last_Update into utc for database.
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedNow = now.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZone = zonedNow.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtNow = utcZone.toLocalDateTime();


        LocalTime startOfDay = LocalTime.of(8, 0);
        LocalTime endOfDay = LocalTime.of(20, 0);


        for (AddAppointmentsModel apps : AddAppointmentsUtil.allAppointments) {
            LocalDateTime dbStart = apps.getLdtStart();
            LocalDateTime dbEnd = apps.getLdtEnd();

/*Lambda to check if there is already appointment scheduled at the same time. This lambda provides a very efficient way to
 insert ldtStartDatePicker (the Start value from the datePicker) and ldtEndDatePicker (the End value from the datePicker) into
 the parameters s and e and compare them to dbStart and dbEnd (the Start and End values from the database). Using this lambda
 shortened my code and reduced many brackets.*/
            Lambda2 checkOverlap = (s, e) -> (s.isAfter(dbStart) && s.isBefore(dbEnd)) || (s.isAfter(dbStart) && s.isBefore(dbEnd) && e.isAfter(dbEnd) || (s.isBefore(dbStart) && e.isAfter(dbStart) && e.isBefore(dbEnd)) || (s.equals(dbStart)));
            if (checkOverlap.checkOverLapHours(ldtStartDatePicker, ldtEndDatePicker)) {
                //timeCollision = true;
                Alert invalidTime = new Alert(AlertType.ERROR);
                invalidTime.setContentText("That time overlaps with an already scheduled appointment.");
                invalidTime.showAndWait();

                return;
            }
        }


/*Lambda to check if the requested appointment time is outside of business our per EST. This lambda provides a very efficient way to
 insert estStartTime and estEndTime (the Start and End time collected from the datePicker) into
 the parameters am and pm and compares them to startOfDay and endOfDay (the given 8:00 and 10:00 hrs respectively). Using this lambda
shortened my code and reduced many brackets.*/

        LambdaInterface checkTime = (am, pm) -> am.isBefore(startOfDay) || pm.isAfter(endOfDay);
        if (checkTime.checkBusinessHours(estStartTime, estEndTime)) {
            Alert invalidTime = new Alert(AlertType.ERROR);
            invalidTime.setContentText("Appointment time must be within business hours of 8:00am - 10:00pm.");
            invalidTime.showAndWait();
            return;
        }

        //Lambda to check if the appointment times are reasonable. ex. end is not before start.
        Lambda3 checkReasonable = (s, e) -> (e.isBefore(s) || s.isAfter(e)  ||  s.equals(e) || e.equals(s)  || s.isBefore(LocalDateTime.now(ZoneId.systemDefault())));
        if (checkReasonable.checkReasonableHours(ldtStartDatePicker, ldtEndDatePicker))
        {
            Alert invalidTime = new Alert(Alert.AlertType.ERROR);
            invalidTime.setContentText("Make sure the start time is not after the end time.\n Make sure the end time is not before the start time. \n Make sure the start time is not before today.\nMake sure the start and end time are not the same.");
            invalidTime.showAndWait();

            return;
        }


        //Not allowing to schedule on the weekend.
        if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) {

            Alert invalidTime = new Alert(AlertType.ERROR);
            invalidTime.setContentText("Appointment time must be within business days of Monday - Friday.");
            invalidTime.showAndWait();
            return;
        } else {

            //Inserting into the appointments table in database and GUI
            String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(appointmentIdTXT.getText()));
            ps.setString(2, titleTXT.getText());
            ps.setString(3, descriptionTXT.getText());
            ps.setString(4, locationTXT.getText());
            ps.setString(5, typeTXT.getText());
            ps.setTimestamp(6, startDatePicker);
            ps.setTimestamp(7, endDatePicker);
            ps.setTimestamp(8, Timestamp.valueOf(ldtNow));
            ps.setString(9, userNameTXT.getText());
            ps.setTimestamp(10, Timestamp.valueOf(ldtNow));
            ps.setString(11, String.valueOf(userNameTXT.getText()));
            ps.setInt(12, Integer.parseInt(customerIdTXT.getText()));
            ps.setInt(13, Integer.parseInt(userIdTXT.getText()));
            ps.setInt(14, contactComboBox.getValue().getContact_ID());
            ps.execute();


            appointmentsTable.setItems(AddAppointmentsUtil.getAllAppointments());
            appointmentIdTXT.setText(String.valueOf(AddAppointmentsUtil.getAppointment_ID()));
            userNameTXT.clear();
            titleTXT.clear();
            descriptionTXT.clear();
            locationTXT.clear();
            typeTXT.clear();
            userIdTXT.clear();
            customerIdTXT.clear();

        }


        Alert appointmentSuccess = new Alert(AlertType.CONFIRMATION);
        appointmentSuccess.setTitle("CONFIRMATION");
        appointmentSuccess.setContentText("You have successfully made your appointment");
        appointmentSuccess.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 500.0));
        stage.setTitle("Menu Page");
        stage.show();

    }

/** Action event that sets the Customer ID based on the customer name. */
    public void nameSelectedComboBox(ActionEvent actionEvent) throws IOException {
        try {
            int selectedName = nameComboBox.getValue().getId();
            customerIdTXT.setText(String.valueOf(selectedName));
        } catch (NullPointerException n) {

        }
    }


    /** Action event that switches the user to the "Update Appointment" page.
     @param actionEvent actionEvent */
        public void updateAppointmentButtonPushed (ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/updateAnAppointment.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 500, 500.0));
            stage.show();
        }

    /** Action event that switches the user to the "Menu Page" page.
     @param actionEvent actionEvent */
        public void backToMenuPagePushed (ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menuPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 500, 500.0));
            stage.show();

        }
    }








