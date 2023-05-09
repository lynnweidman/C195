
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class with a Constructor and Setters and Getters for adding and updating Customers. */
public class CustomerModel {
  private  int Customer_ID;
  private  String Customer_Name;
  private String Address;
  private  String Postal_Code;
  private String Phone;
  private int Division_ID;
  private int Country_ID;

/** The Constructor for Customers.
 * @param Customer_ID Customer_ID
 * @param Customer_Name Customer_Name
 * @param Address Address
 * @param Postal_Code Postal_Code
 * @param Phone Phone
 * @param Division_ID Division_ID
 * @param Country_ID Country_ID */
    public CustomerModel(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, int Division_ID, int Country_ID) {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Division_ID = Division_ID;
        this.Country_ID = Country_ID;
    }
    public CustomerModel(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone) {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
    }

    public CustomerModel (String Customer_Name) {
        this.Customer_Name = Customer_Name;
    }

/** Gets the Customer_ID
 @return Customer_ID*/
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /** Sets the Customer_ID.
     @param customer_ID customer_ID */
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /**Gets the Customer Name.
     @return Customer_Name */
    public String getCustomer_Name() {
        return Customer_Name;
    }

/** Gets the Address.
 @return Address*/
    public String getAddress() {
        return Address;
    }

/** Gets the Postal_Code.
 @return Postal_Code */
    public String getPostal_Code() {
        return Postal_Code;
    }

    /**Gets the Phone.
     @return  Phone */
    public String getPhone() {
        return Phone;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getDivision_ID() {
        return Division_ID;
    }

    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }
}

