package com.example.hibernate.entity;

import javax.persistence.Embeddable;

//will import/embed this class into Student
@Embeddable
public class Address {
    private String line1;
    private String line2;
    private String city;

    public Address(String line1, String line2, String city) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
    }

    protected Address() {
    }
}
