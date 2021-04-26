package userInit.login.register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.User;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static util.Database.*;

public class RegisterController implements Initializable {
    double x, y;
    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField fullName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton signUp;

    @FXML
    private HBox topPane;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        username.getValidators().add(validator);
        username.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) username.validate();
        });

        RequiredFieldValidator validator2 = new RequiredFieldValidator();
        validator2.setMessage("Input Required");
        fullName.getValidators().add(validator2);
        fullName.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) fullName.validate();
        });

        RegexValidator validator3 = new RegexValidator();
        validator3.setMessage("Incorrect email");
        validator3.setRegexPattern(EMAIL_PATTERN);
        email.getValidators().add(validator3);
        email.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) email.validate();
        });

        RegexValidator validator4 = new RegexValidator();
        validator4.setMessage("Password must contains:\n\ta digit at least once\n\ta lower case at least once\n\tNO whitespaces");
        validator4.setRegexPattern(PASSWORD_PATTERN);
        password.getValidators().add(validator4);
        password.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) password.validate();
        });
    }

    @FXML
    public void dragged(MouseEvent event) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    public void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
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

    private void loadWindow(String pathFXL) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathFXL));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void closeWindow(){
        ((Stage) username.getScene().getWindow()).close();
    }

    private User userData(){
        User u  = new User();

        u.setUsername(username.getText());
        u.setFullName(fullName.getText());
        u.setEmail(email.getText());
        String encodedPassword = Base64.getEncoder().encodeToString(password.getText().getBytes());
        u.setPassword(encodedPassword);

        //String decodedPassword = Base64.getDecoder().decode(encodedPassword).toString();

        return u;
    }

    private boolean validFields(){
        if("".equals(username.getText())){
            return false;
        }
        if("".equals(fullName.getText())){
            return false;
        }
        if(!Pattern.matches(EMAIL_PATTERN, email.getText())){
            return false;
        }
        if(!Pattern.matches(PASSWORD_PATTERN, password.getText())){
            return false;
        }
        return true;
    }

    @FXML
    public void RegisterUser(ActionEvent event) throws IOException {


        if(validFields()) {
            TrayNotification tray = new TrayNotification();
            if (!userExists(username.getText())) {

                addUser(userData());
                closeWindow();
                loadWindow("../login.fxml");
                tray.setTitle("Success!");
                tray.setMessage("You are successfully created an account!\nLogin to continue...");
                tray.setNotificationType(NotificationType.SUCCESS);
            } else {
                tray.setTitle("Registration failed...");
                tray.setMessage("Username already exists.");
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.millis(3000));
        }

    }

    @FXML
    public void LoginUserPanel(MouseEvent mouseEvent) throws IOException {
        closeWindow();
        loadWindow("../login.fxml");
    }
}
