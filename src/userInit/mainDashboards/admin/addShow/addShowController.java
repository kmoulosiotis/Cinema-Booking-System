package userInit.mainDashboards.admin.addShow;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Cinema;
import models.Show;


import java.net.URL;

import java.time.LocalDate;
import java.util.ResourceBundle;

import static util.Database.*;

public class addShowController implements Initializable {

    double x, y;

    @FXML
    private HBox topPane;

    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXTimePicker time;

    @FXML
    private JFXDatePicker endDate;

    @FXML
    private JFXTextField seats;

    @FXML
    private JFXTextField movieID;

    @FXML
    private JFXTextField cinemaID;

    @FXML
    private JFXTextArea movieName;

    @FXML
    private JFXTextArea cinemaName;

    @FXML
    private JFXTextArea screen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        movieID.getValidators().add(validator);
        movieID.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) movieID.validate();
        });

        RequiredFieldValidator validator2 = new RequiredFieldValidator();
        validator2.setMessage("Input Required");
        cinemaID.getValidators().add(validator2);
        cinemaID.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) cinemaID.validate();
        });

        RequiredFieldValidator validator3 = new RequiredFieldValidator();
        validator3.setMessage("Input Required");
        seats.getValidators().add(validator3);
        seats.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) seats.validate();
        });

        RequiredFieldValidator validator4 = new RequiredFieldValidator();
        validator4.setMessage("Input Required");
        startDate.getValidators().add(validator4);
        startDate.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) startDate.validate();
        });

        RequiredFieldValidator validator5 = new RequiredFieldValidator();
        validator5.setMessage("Input Required");
        endDate.getValidators().add(validator5);
        endDate.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) endDate.validate();
        });

        RequiredFieldValidator validator6 = new RequiredFieldValidator();
        validator6.setMessage("Input Required");
        time.getValidators().add(validator6);
        time.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) time.validate();
        });
    }

    private boolean validFields(){

        if ("".equals(movieID.getText())){
            return false;
        }
        if ("".equals(cinemaID.getText())){
            return false;
        }
        if ("".equals(seats.getText())){
            return false;
        }
        if ("".equals(startDate.getValue())){
            return false;
        }
        if ("".equals(endDate.getValue())){
            return false;
        }
        if ("".equals(time.getValue())){
            return false;
        }
        return true;
    }

    @FXML
    void addShow(ActionEvent event) {
        if (validFields()) {
            LocalDate end = endDate.getValue().plusDays(1);
            for (LocalDate date = startDate.getValue(); date.isBefore(end); date = date.plusDays(1)) {
                Show s = new Show(
                        Integer.parseInt(cinemaID.getText()),
                        Integer.parseInt(movieID.getText()),
                        movieName.getText(),
                        cinemaName.getText(),
                        Integer.parseInt(screen.getText()),
                        date.toString(),
                        time.getValue().toString(),
                        Integer.parseInt(seats.getText()));

                insertShow(s);
            }
        }
    }

    @FXML
    void searchCinema(ActionEvent event) {
        Cinema c = getCinemaFromID(Integer.parseInt(cinemaID.getText()));
        cinemaName.setText(c.getName());
        screen.setText(String.valueOf(c.getScreen()));
    }

    @FXML
    void searchMovie(ActionEvent event) {
        String name = getMovieNameFromID(Integer.parseInt(movieID.getText()));
        movieName.setText(name);
    }

    @FXML
    void exit(MouseEvent event) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void maximize(MouseEvent event) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setFullScreen(true);
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }
}
