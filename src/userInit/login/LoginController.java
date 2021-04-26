package userInit.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import userInit.mainDashboards.user.userDashboardController;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

import static util.Database.loginValidate;

public class LoginController implements Initializable {

    double x, y;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private HBox topPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            //stage.initOwner(((Stage) username.getScene().getWindow()));
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void closeWindow(){
        ((Stage) username.getScene().getWindow()).close();
    }

    @FXML
    public void RegisterUserPanel(MouseEvent mouseEvent) throws IOException {
        closeWindow();
        loadWindow("register/register.fxml");
    }

    @FXML
    public void AdminLoginPanel(MouseEvent mouseEvent) throws IOException {
        closeWindow();
        loadWindow("admin/admin.fxml");
    }

    @FXML
    public void loginUser(ActionEvent event) throws IOException {
        String encodedPassword = Base64.getEncoder().encodeToString(password.getText().getBytes());
        if(loginValidate(username.getText(), encodedPassword)) {
            closeWindow();
            userDashboardController.username = username.getText();
            loadWindow("../mainDashboards/user/userDashboard.fxml");
        }else{
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Login ERROR...");
            tray.setMessage("Username or Password is incorect.\nTry again or create an account");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.millis(3000));

        }
    }

}
