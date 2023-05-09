package controller;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AddAppointmentsModel;
import model.UserModel;
import util.AddAppointmentsUtil;
import util.UserUtil;

/**
 * @author Lynn Weidman
 */

/** Class to allow users to log in with user name and password. */
public class UserController implements Initializable {
    public Button enterButton;
    public TextField userNameTxt;
    public TextField passwordTxt;
    public Label zoneIdLabel;
    public AnchorPane LogInPage;
    public Label logInLabel;
    public Label userNamLabel;
    public Label passwordLabel;


    /**
     * Initializes the page. Displays the Zone of the users location.
     *
     * @param rb       rb
     * @param location location
     */
    @Override
    public void initialize(URL location, ResourceBundle rb) {

        //Locale.setDefault(new Locale("fr"));// FIXME CHANGED LANGUAGE ON COMPUTER FOR TESTING
        rb = ResourceBundle.getBundle("Lang_language", Locale.getDefault());

        enterButton.setText(rb.getString("enter"));
        logInLabel.setText(rb.getString("logIn"));
        userNamLabel.setText(rb.getString("userName"));
        passwordLabel.setText(rb.getString("password"));

        zoneIdLabel.setText(String.valueOf(ZoneId.systemDefault()));
    }

    /**
     * Action Event that Validates the User_Name and Password from what is stored in the database and updates the login log. Checks if there is
     * an appointment scheduled within 15 minutes of log in time at the zoneID of where the user is located. There's an alert for login success or failure, and
     * within how many minutes the appointment is if applicable, then Loads the Menu Page at successful login.
     *
     * @param actionEvent actionEvent
     * @throws Exception
     */
    @FXML
    void enterButtonPressed(ActionEvent actionEvent) throws Exception, SQLException {
        ResourceBundle rb = ResourceBundle.getBundle("Lang_language", Locale.getDefault());

        LocalDateTime localDateTimePlus15Min = LocalDateTime.now().plusMinutes(16);
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        ZonedDateTime zonedLocalTime = localDateTimeNow.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedTimePlus15Min = localDateTimePlus15Min.atZone(ZoneId.systemDefault());
        ZonedDateTime utcOffsetNow = zonedLocalTime.withZoneSameInstant(ZoneOffset.UTC);
        ZonedDateTime utcOffsetPlus15 = zonedTimePlus15Min.withZoneSameInstant(ZoneOffset.UTC);

        LocalDateTime logOnNowTime = utcOffsetNow.toLocalDateTime();
        LocalDateTime logOnPlus15 = utcOffsetPlus15.toLocalDateTime();


        LocalDateTime thisStart = null;
        int appointmentID = 0;
        boolean within15Minutes = false;
       // boolean userFound = false;


            String user_Name = userNameTxt.getText();
            String password = passwordTxt.getText();
            ObservableList<UserModel> userFound = UserUtil.getUserID(user_Name, password);
            //String userFound = String.valueOf(UserUtil.getUserID(user_Name, password));
            //System.out.println(userFound);
            // boolean validUser = UserUtil.checkLogin(userName, userPassword);
            /*for (UserModel validUser : UserUtil.userID) {
                    int validID = validUser.getUser_ID();
                   if (validID > 0 ) {
                       userFound = true;
                   }
            }*/

            if (userFound.size() == 0)
             {
                FileWriter fileWriterInvalidUser = new FileWriter("login_activity.txt", true);
                PrintWriter logInFileInvalidUser = new PrintWriter(fileWriterInvalidUser);
                logInFileInvalidUser.println("user: " + "\"" + user_Name + "\"" + " logged in at: " + Timestamp.valueOf(LocalDateTime.now()) + " -login not successful.");
                logInFileInvalidUser.flush();

                if (rb.getLocale().getLanguage().equals("fr")) {
                    Alert notValidFr = new Alert(Alert.AlertType.ERROR);
                    notValidFr.setContentText(rb.getString("incorrectPassword"));
                    notValidFr.showAndWait();
                    return;
                }
               else {

                    Alert notValid = new Alert(Alert.AlertType.ERROR);
                    notValid.setContentText("Name or password incorrect");
                    notValid.showAndWait();
                    return;
                }
            } else {

                FileWriter fileWriter = new FileWriter("login_activity.txt", true);
                PrintWriter logInFile = new PrintWriter(fileWriter);
                logInFile.println("user: " + "\"" + user_Name + "\"" + " logged in at: " + Timestamp.valueOf(LocalDateTime.now()) + " -login successful.");
                logInFile.flush();
                //logInFile.flush();


                for (AddAppointmentsModel appointments : AddAppointmentsUtil.allAppointments) {

                    LocalDateTime dbStart = appointments.getLdtStart();

                    if (dbStart.isBefore(logOnPlus15) && dbStart.isAfter(logOnNowTime)) {
                        appointmentID = appointments.getAppointment_ID();
                        thisStart = dbStart;

                        within15Minutes = true;

                    }
                }


                if (within15Minutes) {
                    ZonedDateTime utcStart = thisStart.atZone(ZoneId.of("UTC"));
                    ZonedDateTime zoneStart = utcStart.withZoneSameInstant(ZoneId.systemDefault());
                    LocalDateTime ldtStart = zoneStart.toLocalDateTime();

                    Long withinMinutes = ChronoUnit.MINUTES.between(logOnNowTime.minusMinutes(1), thisStart);

                    if (rb.getLocale().getLanguage().equals("fr")) {
                        Alert appointmentAlertFr = new Alert(Alert.AlertType.CONFIRMATION);
                        appointmentAlertFr.setContentText(rb.getString("successWithApmt1") + " \n" + rb.getString("successWithApmt2") + appointmentID + " " + rb.getString("successWithApmt3")
                                + withinMinutes + " " + rb.getString("successWithApmt4") + "\n" + rb.getString("successWithApmt5") + " " + ldtStart.toLocalDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
                                + rb.getString("successWithApmt6") + ldtStart.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) + ".");
                        appointmentAlertFr.showAndWait();

                    } else {
                        Alert appointmentAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        appointmentAlert.setContentText("Successful Login." + " \n" + "AppointmentID- " + appointmentID + " has an appointment in "
                                + withinMinutes + " minutes " + "\n" + "on " + ldtStart.toLocalDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + " at " + ldtStart.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) + ".");
                        appointmentAlert.showAndWait();


                    }
                } else {  //if not within 15 min.

                    if (rb.getLocale().getLanguage().equals("fr")) {
                        Alert noUpcomingAppointmentAlertFr = new Alert(Alert.AlertType.CONFIRMATION);
                        noUpcomingAppointmentAlertFr.setContentText(rb.getString("successW/oApmt"));
                        noUpcomingAppointmentAlertFr.showAndWait();

                    } else {

                        Alert noUpcomingAppointmentAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        noUpcomingAppointmentAlert.setContentText("Successful Login." + " There are no upcoming appointments.");
                        noUpcomingAppointmentAlert.showAndWait();

                    }
                }
                //  } fixme
            }
       /* else {  // if valid user = false.
            FileWriter fileWriterInvalidUser = new FileWriter("login_activity.txt", true);
            PrintWriter logInFileInvalidUser = new PrintWriter(fileWriterInvalidUser);
            logInFileInvalidUser.println("user: " + "\"" + userName + "\"" + " logged in at: " + Timestamp.valueOf(LocalDateTime.now()) + " -login not successful.");
            logInFileInvalidUser.flush();

            if (rb.getLocale().getLanguage().equals("fr")) {
                Alert notValidFr = new Alert(Alert.AlertType.ERROR);
                notValidFr.setContentText(rb.getString("incorrectPassword"));
                notValidFr.showAndWait();
                return;
            } else {

                Alert notValid = new Alert(Alert.AlertType.ERROR);
                notValid.setContentText("Name or password incorrect");
                notValid.showAndWait();
                return;
            }
        }*/



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 500, 500.0));
        stage.setTitle("Menu Page");
        stage.show();


    }
}




































