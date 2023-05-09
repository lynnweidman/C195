package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class TypeCustomerModel extends UpdateAppointmentModel {
    private String type;
    private static ObservableList<TypeCustomerModel> customerWithType = FXCollections.observableArrayList();


    public TypeCustomerModel(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime ldtStart, LocalDateTime ldtEnd, LocalDateTime Last_Update, String Last_Updated_By, int Customer_ID, int User_ID, int Contact_ID, String type) {
        super(Appointment_ID, Title, Description, Location, Type, ldtStart, ldtEnd, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void addCustomerWithType(TypeCustomerModel customerType) {
        customerWithType.add(customerType);
    }


}

