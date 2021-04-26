package models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Movie {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name, director, cast, details;
    private SimpleFloatProperty rating;

    public Movie(int id, String name, String diretor, String cast, String details, float rating) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.director = new SimpleStringProperty(diretor);
        this.cast = new SimpleStringProperty(cast);
        this.details = new SimpleStringProperty(details);
        this.rating = new SimpleFloatProperty(rating);
    }

    public Movie(String name, String diretor, String cast, String details, float rating) {
        this.name = new SimpleStringProperty(name);
        this.director = new SimpleStringProperty(diretor);
        this.cast = new SimpleStringProperty(cast);
        this.details = new SimpleStringProperty(details);
        this.rating = new SimpleFloatProperty(rating);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDirector() {
        return director.get();
    }

    public SimpleStringProperty directorProperty() {
        return director;
    }

    public void setDirector(String director) {
        this.director.set(director);
    }

    public String getCast() {
        return cast.get();
    }

    public SimpleStringProperty castProperty() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast.set(cast);
    }

    public String getDetails() {
        return details.get();
    }

    public SimpleStringProperty detailsProperty() {
        return details;
    }

    public void setDetails(String details) {
        this.details.set(details);
    }

    public float getRating() {
        return rating.get();
    }

    public SimpleFloatProperty ratingProperty() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating.set(rating);
    }
}
