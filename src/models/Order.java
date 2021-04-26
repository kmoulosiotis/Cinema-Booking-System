package models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {

    private SimpleIntegerProperty id, screen, tickets;
    private SimpleStringProperty movieName, cinemaName, date, time, category, orderDatetime;
    private SimpleFloatProperty amount;

    public Order(int id, String movieName, String cinemaName, int screen, String date, String time, String category, int tickets, float amount, String orderDatetime){
        this.id = new SimpleIntegerProperty(id);
        this.movieName = new SimpleStringProperty(movieName);
        this.cinemaName = new SimpleStringProperty(cinemaName);
        this.screen = new SimpleIntegerProperty(screen);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.category = new SimpleStringProperty(category);
        this.tickets = new SimpleIntegerProperty(tickets);
        this.amount = new SimpleFloatProperty(amount);
        this.orderDatetime = new SimpleStringProperty(orderDatetime);
    }

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

    public int getTickets() {
        return tickets.get();
    }

    public SimpleIntegerProperty ticketsProperty() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets.set(tickets);
    }

    public String getMovieName() {
        return movieName.get();
    }

    public SimpleStringProperty movieNameProperty() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName.set(movieName);
    }

    public String getCinemaName() {
        return cinemaName.get();
    }

    public SimpleStringProperty cinemaNameProperty() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName.set(cinemaName);
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

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getOrderDatetime() {
        return orderDatetime.get();
    }

    public SimpleStringProperty orderDatetimeProperty() {
        return orderDatetime;
    }

    public void setOrderDatetime(String orderDatetime) {
        this.orderDatetime.set(orderDatetime);
    }

    public float getAmount() {
        return amount.get();
    }

    public SimpleFloatProperty amountProperty() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount.set(amount);
    }
}
