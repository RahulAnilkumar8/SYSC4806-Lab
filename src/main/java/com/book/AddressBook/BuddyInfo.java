package com.book.AddressBook;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String address;
    private String phoneNumber;


    public BuddyInfo(){
    }

    public BuddyInfo(String firstName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo(Long id, String firstName, String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the id of this Buddy.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id of this Buddy to the specified value.
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Transient
    public String toString() {
        String s;
        s = " " + this.getFirstName() + " \n Lives at " + this.getAddress() + " \n Reach this person at " + this.getPhoneNumber() + "\n ----------------------";
        return s;
    }
}
