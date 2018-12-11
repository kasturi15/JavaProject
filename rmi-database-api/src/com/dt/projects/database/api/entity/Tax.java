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

/**
 *
 * @author kastu
 */
public class Tax implements Externalizable{

    private final IntegerProperty tax_id = new SimpleIntegerProperty();
    private final IntegerProperty gst = new SimpleIntegerProperty();

    public int getGst() {
        return gst.get();
    }

    public void setGst(int value) {
        gst.set(value);
    }

    public IntegerProperty gstProperty() {
        return gst;
    }
    
    public int getTax_id() {
        return tax_id.get();
    }

    public void setTax_id(int value) {
        tax_id.set(value);
    }

    public IntegerProperty tax_idProperty() {
        return tax_id;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getTax_id());
        out.writeInt(getGst());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setTax_id(in.readInt());
        setGst(in.readInt()); 
    }
    
    
    
    
}
