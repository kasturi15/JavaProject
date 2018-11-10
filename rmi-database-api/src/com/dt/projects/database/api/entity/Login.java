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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kastu
 */
public class Login implements Externalizable{

    private final StringProperty staff_id = new SimpleStringProperty();
    private final StringProperty staff_pass = new SimpleStringProperty();

    public String getStaff_pass() {
        return staff_pass.get();
    }

    public void setStaff_pass(String value) {
        staff_pass.set(value);
    }

    public StringProperty staff_passProperty() {
        return staff_pass;
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
        out.writeObject(getStaff_pass());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setStaff_id((String) in.readObject());
        setStaff_pass((String) in.readObject()); 
    }
    
    
}

