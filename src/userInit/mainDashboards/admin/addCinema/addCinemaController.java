package userInit.mainDashboards.admin.addCinema;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Cinema;

import java.net.URL;
import java.util.ResourceBundle;

import static util.Database.insertCinema;

public class addCinemaController implements Initializable {

    double x, y;

    @FXML
    private HBox topPane;

    @FXML
    private JFXTextField cinemaName;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField screen;

    @FXML
    private JFXTextField adult;

    @FXML
    private JFXTextField child;

    @FXML
    private JFXTextField student;

    @FXML
    private JFXTextField family;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        cinemaName.getValidators().add(validator);
        cinemaName.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) cinemaName.validate();
        });

        RequiredFieldValidator validator2 = new RequiredFieldValidator();
        validator2.setMessage("Input Required");
        address.getValidators().add(validator2);
        address.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) address.validate();
        });

        RequiredFieldValidator validator3 = new RequiredFieldValidator();
        validator3.setMessage("Input Required");
        screen.getValidators().add(validator3);
        screen.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) screen.validate();
        });

        RequiredFieldValidator validator4 = new RequiredFieldValidator();
        validator4.setMessage("Input Required");
        adult.getValidators().add(validator4);
        adult.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) adult.validate();
        });

        RequiredFieldValidator validator5 = new RequiredFieldValidator();
        validator5.setMessage("Input Required");
        child.getValidators().add(validator5);
        child.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) child.validate();
        });

        RequiredFieldValidator validator6 = new RequiredFieldValidator();
        validator6.setMessage("Input Required");
        student.getValidators().add(validator6);
        student.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) student.validate();
        });

        RequiredFieldValidator validator7 = new RequiredFieldValidator();
        validator7.setMessage("Input Required");
        family.getValidators().add(validator7);
        family.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) family.validate();
        });
    }

    private boolean validFields(){

        if ("".equals(cinemaName.getText())){
            return false;
        }
        if ("".equals(address.getText())){
            return false;
        }
        if ("".equals(screen.getText())){
            return false;
        }
        if ("".equals(adult.getText())){
            return false;
        }
        if ("".equals(child.getText())){
            return false;
        }
        if ("".equals(student.getText())){
            return false;
        }
        if ("".equals(family.getText())){
            return false;
        }
        return true;
    }

    @FXML
    void addCinema(ActionEvent event) {
        if (validFields()){
            Cinema c = new Cinema(cinemaName.getText(),Integer.parseInt(screen.getText()), address.getText(),Float.parseFloat(adult.getText()), Float.parseFloat(child.getText()), Float.parseFloat(student.getText()), Float.parseFloat(family.getText()));
            insertCinema(c);
        }
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
