package userInit.mainDashboards.admin.addMovie;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Movie;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static util.Database.insertMovie;

public class AddMovieController implements Initializable {

    double x, y;
    File imageFile;

    @FXML
    private HBox topPane;

    @FXML
    private JFXTextField movieName;

    @FXML
    private JFXTextField director;

    @FXML
    private JFXTextField cast;

    @FXML
    private JFXTextField rating;

    @FXML
    private JFXTextArea details;

    @FXML
    private ImageView poster;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        movieName.getValidators().add(validator);
        movieName.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) movieName.validate();
        });

        RequiredFieldValidator validator2 = new RequiredFieldValidator();
        validator2.setMessage("Input Required");
        director.getValidators().add(validator2);
        director.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) director.validate();
        });

        RequiredFieldValidator validator3 = new RequiredFieldValidator();
        validator3.setMessage("Input Required");
        cast.getValidators().add(validator3);
        cast.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) cast.validate();
        });

        RequiredFieldValidator validator4 = new RequiredFieldValidator();
        validator4.setMessage("Input Required");
        details.getValidators().add(validator4);
        details.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) details.validate();
        });

        RequiredFieldValidator validator5 = new RequiredFieldValidator();
        validator5.setMessage("Input Required");
        rating.getValidators().add(validator5);
        rating.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) rating.validate();
        });

    }

    @FXML
    void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extensionFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extensionFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extensionFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.JPEG)", "*.JPEG");
        FileChooser.ExtensionFilter extensionFilterjpeg = new FileChooser.ExtensionFilter("jpeg files (*.jpeg)", "*.jpeg");

        fileChooser.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterjpg, extensionFilterPNG, extensionFilterpng, extensionFilterJPEG, extensionFilterjpeg);

        imageFile = fileChooser.showOpenDialog(((Stage) movieName.getScene().getWindow()));
        Image image = new Image(imageFile.toURI().toString());
        poster.setImage(image);
    }

    private void closeWindow(){
        ((Stage) movieName.getScene().getWindow()).close();
    }

    private boolean validFields(){

        if("".equals(movieName.getText())){
            return false;
        }
        if ("".equals(director.getText())){
            return false;
        }
        if("".equals(cast.getText())){
            return false;
        }
        if("".equals(details.getText())){
            return false;
        }
        if("".equals(rating.getText())){
            return false;
        }

        return true;
    }

    @FXML
    void addMovie(ActionEvent event) {
        if (validFields()){
            Movie m = new Movie(movieName.getText(), director.getText(), cast.getText(), details.getText(), Float.parseFloat(rating.getText()));
            int status = insertMovie(m, imageFile);

            if (status == 0){
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Movie insert failed...");
                tray.setMessage("Movie already exists.");
                tray.setNotificationType(NotificationType.ERROR);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.millis(3000));
            }
            //closeWindow();
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
