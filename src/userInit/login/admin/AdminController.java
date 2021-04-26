package userInit.login.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import userInit.mainDashboards.admin.AdminDashboardController;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

import static util.Database.loginAdminValidate;

public class AdminController implements Initializable {

    double x, y;

    @FXML
    private HBox topPane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton userLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void loadWindow(String pathFXL) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathFXL));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            //stage.initOwner(((Stage) username.getScene().getWindow()));
            //stage.initModality(Modality.WINDOW_MODAL);
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


    @FXML
    void loginUser(ActionEvent event) throws IOException {
        closeWindow();
        loadWindow("../login.fxml");
    }

    @FXML
    void loginAdmin(ActionEvent event) throws IOException {
        String encodedPassword = Base64.getEncoder().encodeToString(password.getText().getBytes());
        if(loginAdminValidate(username.getText(), encodedPassword)){
            closeWindow();
            AdminDashboardController.username = username.getText();
            loadWindow("../../mainDashboards/admin/adminDashboard.fxml");
        }else {
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Login ERROR...");
            tray.setMessage("Username or Password is incorect.\nTry again!");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }
}
