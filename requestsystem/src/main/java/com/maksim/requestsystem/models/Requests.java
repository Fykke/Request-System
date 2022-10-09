package com.maksim.requestsystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//creating a table
@Entity
public class Requests { // probably should have called Request*

// All object values and methods. Database will have the same values for it's objects
    @Id   // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // will generate new (not used) value for id
    private Long id;
    private String component, name, addition;
    private String date;
    private boolean requestDone = false;

    public Requests(String component, String name, String addition, String date, boolean requestDone) { // constructor with values (parameters)
        this.component = component;
        this.name = name;
        this.addition = addition;
        this.date = date;
        this.requestDone = requestDone;
    }

    public Requests() {} // default constructors without parameters

    // getters and setters ->

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComponent() {
        return component;
    }

    public boolean isRequestDone() {
        return requestDone;
    }

    public void setRequestDone(boolean requestDone) {
        this.requestDone = requestDone;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}