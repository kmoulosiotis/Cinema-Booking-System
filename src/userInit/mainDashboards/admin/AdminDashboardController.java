package userInit.mainDashboards.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    double x, y;
    public static String username;
    @FXML
    private Label adminID;

    @FXML
    private HBox topPane;

    @FXML
    private AnchorPane currPanel;

    private AnchorPane homePage, moviesPage, cinemasPage, showsPage, profilePage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        adminID.setText(username);
        try {
            homePage = FXMLLoader.load(getClass().getResource("home.fxml"));
            moviesPage = FXMLLoader.load(getClass().getResource("movies.fxml"));
            cinemasPage = FXMLLoader.load(getClass().getResource("cinemas.fxml"));
            showsPage = FXMLLoader.load(getClass().getResource("shows.fxml"));
            profilePage = FXMLLoader.load(getClass().getResource("profile.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setCurrPanel(homePage);
    }

    private void setCurrPanel(Node p){
        currPanel.getChildren().clear();
        currPanel.getChildren().add((Node) p);

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
        ((Stage) adminID.getScene().getWindow()).close();
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
    void goToHome(ActionEvent event) {
        setCurrPanel(homePage);
    }

    @FXML
    void goToMovies(ActionEvent event) {
        setCurrPanel(moviesPage);
    }

    @FXML
    void goToCinemas(ActionEvent event) {
        setCurrPanel(cinemasPage);
    }

    @FXML
    void goToShows(ActionEvent event) {
        setCurrPanel(showsPage);
    }

    @FXML
    void goToProfile(ActionEvent event) {
        setCurrPanel(profilePage);
    }

    @FXML
    void Logout(ActionEvent event) throws IOException {
        closeWindow();
        loadWindow("../../login/admin/admin.fxml");
    }
}
