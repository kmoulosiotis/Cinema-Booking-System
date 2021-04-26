package models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cinema {

    private SimpleIntegerProperty id, screen;
    private SimpleStringProperty name, address;
    private SimpleFloatProperty adult, child, student, family;

    public Cinema(int id, String name, int screen, String address, float adult, float child, float student, float family){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.screen = new SimpleIntegerProperty(screen);
        this.address = new SimpleStringProperty(address);
        this.adult = new SimpleFloatProperty(adult);
        this.child = new SimpleFloatProperty(child);
        this.student = new SimpleFloatProperty(student);
        this.family = new SimpleFloatProperty(family);
    }

    public Cinema(String name, int screen, String address, float adult, float child, float student, float family){
        this.name = new SimpleStringProperty(name);
        this.screen = new SimpleIntegerProperty(screen);
        this.address = new SimpleStringProperty(address);
        this.adult = new SimpleFloatProperty(adult);
        this.child = new SimpleFloatProperty(child);
        this.student = new SimpleFloatProperty(student);
        this.family = new SimpleFloatProperty(family);
    }

    public Cinema(){}


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getScreen() {
        return screen.get();
    }

    public SimpleIntegerProperty screenProperty() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen.set(screen);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public float getAdult() {
        return adult.get();
    }

    public SimpleFloatProperty adultProperty() {
        return adult;
    }

    public void setAdult(float adult) {
        this.adult.set(adult);
    }

    public float getChild() {
        return child.get();
    }

    public SimpleFloatProperty childProperty() {
        return child;
    }

    public void setChild(float child) {
        this.child.set(child);
    }

    public float getStudent() {
        return student.get();
    }

    public SimpleFloatProperty studentProperty() {
        return student;
    }

    public void setStudent(float student) {
        this.student.set(student);
    }

    public float getFamily() {
        return family.get();
    }

    public SimpleFloatProperty familyProperty() {
        return family;
    }

    public void setFamily(float family) {
        this.family.set(family);
    }
}
