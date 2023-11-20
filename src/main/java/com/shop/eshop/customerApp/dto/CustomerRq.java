package com.shop.eshop.customerApp.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRq {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String city;

    public CustomerRq(Long id,
                      String firstName,
                      String middleName,
                      String lastName,
                      String phoneNumber,
                      String email,
                      String city) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
    }

    public CustomerRq(String firstName, String middleName, String lastName, String phoneNumber, String email, String city) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
    }
}
