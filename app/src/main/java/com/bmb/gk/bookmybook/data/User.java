package com.bmb.gk.bookmybook.data;

/**
 * Created by Gautam on 08/04/17.
 */

public class User {

    private String emailId;
    private String firstName;
    private String lastName;
    private String defaultAddress;

    public String getEmailId() {
        return emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
