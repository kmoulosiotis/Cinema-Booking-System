package userInit.mainDashboards.admin;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static util.Database.getAdminPassword;
import static util.Database.updateAdminPassword;

public class ProfileController implements Initializable {

    @FXML
    private Label username;

    @FXML
    private JFXPasswordField oldPassword;

    @FXML
    private JFXPasswordField newPassword;

    private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO

        username.setText(AdminDashboardController.username);

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        oldPassword.getValidators().add(validator);
        oldPassword.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) oldPassword.validate();
        });

        RegexValidator validator2 = new RegexValidator();
        validator2.setMessage("Password must contains:\n\ta digit at least once\n\ta lower case at least once\n\tNO whitespaces");
        validator2.setRegexPattern(PASSWORD_PATTERN);
        newPassword.getValidators().add(validator2);
        newPassword.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) newPassword.validate();
        });
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

    @FXML
    void save(ActionEvent event) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(getAdminPassword());
        String decodedPassword = new String(decodedBytes);

        if (validFields()) {
            if (decodedPassword.equals(oldPassword.getText())) {
                String encodedPassword = Base64.getEncoder().encodeToString(newPassword.getText().getBytes());
                updateAdminPassword(encodedPassword);
                closeWindow();
                loadWindow("../../login/admin/admin.fxml");
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Password change successfully");
                tray.setMessage("Please login again with the new password!");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.millis(3000));
            }else{
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Password change failed");
                tray.setMessage("Wrong old password.\nPlease try again!");
                tray.setNotificationType(NotificationType.ERROR);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.millis(3000));
            }
        }
    }

    private boolean validFields(){
        if("".equals(oldPassword.getText())){
            return false;
        }
        if(!Pattern.matches(PASSWORD_PATTERN, newPassword.getText())){
            return false;
        }
        return true;
    }
}
