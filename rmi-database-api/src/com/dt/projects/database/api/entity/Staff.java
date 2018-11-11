/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.api.entity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kastu
 */
public class Staff implements Externalizable{

    private final StringProperty staff_id = new SimpleStringProperty();
    private final StringProperty staff_name = new SimpleStringProperty();
    private final StringProperty staff_pass = new SimpleStringProperty();
    private final StringProperty staff_email = new SimpleStringProperty();
    private final IntegerProperty staff_no = new SimpleIntegerProperty();
    private final IntegerProperty salary = new SimpleIntegerProperty();

    public int getSalary() {
        return salary.get();
    }

    public void setSalary(int value) {
        salary.set(value);
    }

    public IntegerProperty salaryProperty() {
        return salary;
    }
    

    public int getStaff_no() {
        return staff_no.get();
    }

    public void setStaff_no(int value) {
        staff_no.set(value);
    }

    public IntegerProperty staff_noProperty() {
        return staff_no;
    }
    

    public String getStaff_email() {
        return staff_email.get();
    }

    public void setStaff_email(String value) {
        staff_email.set(value);
    }

    public StringProperty staff_emailProperty() {
        return staff_email;
    }
    

    public String getStaff_pass() {
        return staff_pass.get();
    }

    public void setStaff_pass(String value) {
        staff_pass.set(value);
    }

    public StringProperty staff_passProperty() {
        return staff_pass;
    }
    

    public String getStaff_name() {
        return staff_name.get();
    }

    public void setStaff_name(String value) {
        staff_name.set(value);
    }

    public StringProperty staff_nameProperty() {
        return staff_name;
    }
    

    public String getStaff_id() {
        return staff_id.get();
    }

    public void setStaff_id(String value) {
        staff_id.set(value);
    }

    public StringProperty staff_idProperty() {
        return staff_id;
    }
    
    

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getStaff_id());
        out.writeObject(getStaff_name());
        out.writeObject(getStaff_pass());
        out.writeObject(getStaff_email());
        out.writeInt(getStaff_no());
        out.writeInt(getSalary());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setStaff_id((String)in.readObject());
        setStaff_name((String)in.readObject());
        setStaff_pass((String)in.readObject());       
        setStaff_email((String)in.readObject());
        setStaff_no(in.readInt());
        setSalary(in.readInt());
    }
    
}
