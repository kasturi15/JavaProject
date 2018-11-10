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
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kastu
 */
public class Menu implements Externalizable{

    private final LongProperty item_id = new SimpleLongProperty();
    private final StringProperty item_name = new SimpleStringProperty();
    private final StringProperty item_type = new SimpleStringProperty();
    private final IntegerProperty item_price = new SimpleIntegerProperty();

    public int getItem_price() {
        return item_price.get();
    }

    public void setItem_price(int value) {
        item_price.set(value);
    }

    public IntegerProperty item_priceProperty() {
        return item_price;
    }

    public String getItem_type() {
        return item_type.get();
    }

    public void setItem_type(String value) {
        item_type.set(value);
    }

    public StringProperty item_typeProperty() {
        return item_type;
    }

    public String getItem_name() {
        return item_name.get();
    }

    public void setItem_name(String value) {
        item_name.set(value);
    }

    public StringProperty item_nameProperty() {
        return item_name;
    }
    
    public long getItem_id() {
        return item_id.get();
    }

    public void setItem_id(long value) {
        item_id.set(value);
    }

    public LongProperty item_idProperty() {
        return item_id;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getItem_id());
        out.writeObject(getItem_name());
        out.writeObject(getItem_type());
        out.writeInt(getItem_price());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setItem_id(in.readLong());
        setItem_name((String) in.readObject());
        setItem_type((String) in.readObject());
        setItem_price(in.readInt()); 
    }
    
}
