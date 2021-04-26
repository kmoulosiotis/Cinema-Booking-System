package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Show {

    private SimpleIntegerProperty sID, cID, mID, screen, seats;
    private SimpleStringProperty cName, mName, time, date;

    public Show(int cID, int mID, String mName, String cName, int screen, String date, String time, int seats){
        this.cID = new SimpleIntegerProperty(cID);
        this.mID = new SimpleIntegerProperty(mID);
        this.mName = new SimpleStringProperty(mName);
        this.cName = new SimpleStringProperty(cName);
        this.screen = new SimpleIntegerProperty(screen);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.seats = new SimpleIntegerProperty(seats);
    }

    public Show(int sID, int cID, int mID, String mName, String cName, int screen, String date, String time, int seats){
        this.sID = new SimpleIntegerProperty(sID);
        this.cID = new SimpleIntegerProperty(cID);
        this.mID = new SimpleIntegerProperty(mID);
        this.mName = new SimpleStringProperty(mName);
        this.cName = new SimpleStringProperty(cName);
        this.screen = new SimpleIntegerProperty(screen);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.seats = new SimpleIntegerProperty(seats);
    }

    public Show(){
        this.sID = null;
        this.cID = null;
        this.mID = null;
        this.mName = null;
        this.cName = null;
        this.screen = null;
        this.date = null;
        this.time = null;
        this.seats = null;
    }

    public int getsID() {
        return sID.get();
    }

    public SimpleIntegerProperty sIDProperty() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID.set(sID);
    }

    public int getcID() {
        return cID.get();
    }

    public SimpleIntegerProperty cIDProperty() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID.set(cID);
    }

    public int getmID() {
        return mID.get();
    }

    public SimpleIntegerProperty mIDProperty() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID.set(mID);
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

    public int getSeats() {
        return seats.get();
    }

    public SimpleIntegerProperty seatsProperty() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats.set(seats);
    }

    public String getcName() {
        return cName.get();
    }

    public SimpleStringProperty cNameProperty() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName.set(cName);
    }

    public String getmName() {
        return mName.get();
    }

    public SimpleStringProperty mNameProperty() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName.set(mName);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
