
package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.*;
import java.util.Objects;
import java.sql.SQLException;

/**
 * @author Lynn Weidman
 */

/** Class that takes you to the first screen- The Log In page. */
public class Main extends Application {
    public Main() throws SQLException {
    }
    //Shows the first screen with log in and user name and password.
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/user_log_in.fxml")));
        stage.setTitle("Log In");
        stage.setScene(new Scene(root, 600, 500.0));
        stage.show();
    }


    public static void main(String[] args) throws SQLException {

        JDBC.openConnection();

        UserUtil.getUser();
        AddAppointmentsUtil.getAllAppointments();

        launch(args);
        JDBC.closeConnection();

    }

}
