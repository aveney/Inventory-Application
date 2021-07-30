package com.InventoryApplication.InventoryAuditApplication.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    // Instance variables
    private String name;
    private Long entryCount = 0L;

    // Establish a one-to-many relationship with the entries
    @OneToMany(mappedBy = "user")
    private List<Entry> entryList;

    // Constructors
    public User() {
    }

    public User(String name, List<Entry> entryList) {
        this.name = name;
        this.entryList = entryList;
    }

    public User(String name, Long entryCount) {
        this.name = name;
        this.entryCount = entryCount;
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", entryCount=" + entryCount +
                ", entryList=" + entryList +
                '}';
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(Long entryCount) {
        this.entryCount = entryCount;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}
