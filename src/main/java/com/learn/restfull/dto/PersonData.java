package com.learn.restfull.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PersonData {
    @NotEmpty(message = "Name is required")
    private String name ; 
    
    @NotEmpty(message = "Email is required")
    @Email
    private String email ;

    @NotEmpty(message = "Address is required")
    private String address ;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    
}
