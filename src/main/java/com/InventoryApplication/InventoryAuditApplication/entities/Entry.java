package com.InventoryApplication.InventoryAuditApplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Entry {

    @Id
    @GeneratedValue
    private Long id;

    // Instance variables
    private String deviceType;
    private String deviceOwner;
    private Long deviceBarcode;
    private Long roomNumber;

    // Establish a many-to-one relationship with the user
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    // Constructors
    public Entry() {
    }

    public Entry(String deviceType, String deviceOwner, Long deviceBarcode, Long roomNumber) {
        this.deviceType = deviceType;
        this.deviceOwner = deviceOwner;
        this.deviceBarcode = deviceBarcode;
        this.roomNumber = roomNumber;
    }



    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceOwner() {
        return deviceOwner;
    }

    public void setDeviceOwner(String deviceOwner) {
        this.deviceOwner = deviceOwner;
    }

    public Long getDeviceBarcode() {
        return deviceBarcode;
    }

    public void setDeviceBarcode(Long deviceBarcode) {
        this.deviceBarcode = deviceBarcode;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
