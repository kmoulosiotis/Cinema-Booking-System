package userInit.mainDashboards.user.bookShow;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Cinema;
import models.Movie;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.util.ResourceBundle;

import static util.Database.*;

public class BookShowController implements Initializable {
    double x, y;
    public static int showID, movieID, cinemaID, showSeats;
    public static String showTime, showDate;

    @FXML
    private HBox topPane;

    @FXML
    private Label movieName;

    @FXML
    private Label cinemaName;

    @FXML
    private Label date;

    @FXML
    private Label screen;

    @FXML
    private Label adultCost;

    @FXML
    private Label childCost;

    @FXML
    private Label studentCost;

    @FXML
    private Label familyCost;

    @FXML
    private Label time;

    @FXML
    private Label seats;

    @FXML
    private ToggleGroup category;

    @FXML
    private JFXRadioButton adult;

    @FXML
    private JFXRadioButton child;

    @FXML
    private JFXRadioButton student;

    @FXML
    private JFXRadioButton family;

    @FXML
    private JFXTextField tickets;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO

        Movie movie = getMovieFromID(movieID);
        Cinema cinema = getCinemaFromID(cinemaID);

        movieName.setText(movie.getName());
        cinemaName.setText(cinema.getName());
        screen.setText(String.valueOf(cinema.getScreen()));
        seats.setText(String.valueOf(showSeats));
        date.setText(showDate);
        time.setText(showTime);
        adultCost.setText(String.valueOf(cinema.getAdult()));
        childCost.setText(String.valueOf(cinema.getChild()));
        studentCost.setText(String.valueOf(cinema.getStudent()));
        familyCost.setText(String.valueOf(cinema.getFamily()));
    }

    private void createOrder(String seatCategory, float price){
        float totalPrice = 0;
        if (seatCategory == "Family"){
            totalPrice = price;
        }else {
            totalPrice = price*Integer.parseInt(tickets.getText());
        }

        updateShowAvailableTickets(showID, Integer.parseInt(seats.getText())-Integer.parseInt(tickets.getText()));
        insertOrder(seatCategory, Integer.parseInt(tickets.getText()), totalPrice, showID);
    }

    @FXML
    void purchase(ActionEvent event) {
        String seatCategory = "";
        float price = 0;
        boolean availableSeats = true;

        if (category.getSelectedToggle() == adult){
            seatCategory = "Adult";
            price = Float.parseFloat(adultCost.getText());
            if (Integer.parseInt(seats.getText()) - Integer.parseInt(tickets.getText()) < 0){
                availableSeats = false;
            }
        }else if (category.getSelectedToggle() == child){
            seatCategory = "Child";
            price = Float.parseFloat(childCost.getText());
            if (Integer.parseInt(seats.getText()) - Integer.parseInt(tickets.getText()) < 0){
                availableSeats = false;
            }
        }else if (category.getSelectedToggle() == student){
            seatCategory = "Student";
            price = Float.parseFloat(studentCost.getText());
            if (Integer.parseInt(seats.getText()) - Integer.parseInt(tickets.getText()) < 0){
                availableSeats = false;
            }
        }else if (category.getSelectedToggle() == family){
            seatCategory = "Family";
            price = Float.parseFloat(familyCost.getText());
            if (Integer.parseInt(seats.getText()) - Integer.parseInt(tickets.getText()) < 0){
                availableSeats = false;
            }
        }

        if (availableSeats && Integer.parseInt(tickets.getText()) > 0){
            createOrder(seatCategory, price);
        }else {
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Not available tickets.");
            tray.setMessage("There are not "+tickets.getText()+" available tickets");
            tray.setNotificationType(NotificationType.WARNING);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.millis(3000));
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
