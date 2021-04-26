package userInit.mainDashboards.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Order;
import models.Show;

import java.net.URL;
import java.util.ResourceBundle;

import static util.Database.*;

public class BookedShowsController  implements Initializable {

    String username = userDashboardController.username;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, Integer> orderIDCol;

    @FXML
    private TableColumn<Order, String> movieNameCol;

    @FXML
    private TableColumn<Order, String> cinemaNameCol;

    @FXML
    private TableColumn<Order, Integer> screenCol;

    @FXML
    private TableColumn<Order, String> dateCol;

    @FXML
    private TableColumn<Order, String> timeCol;

    @FXML
    private TableColumn<Order, String> categoryCol;

    @FXML
    private TableColumn<Order, Integer> ticketsCol;

    @FXML
    private TableColumn<Order, Float> amountCol;

    @FXML
    private TableColumn<Order, String> datetimeCol;

    ObservableList<Order> orderList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        orderIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        movieNameCol.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        cinemaNameCol.setCellValueFactory(new PropertyValueFactory<>("cinemaName"));
        screenCol.setCellValueFactory(new PropertyValueFactory<>("screen"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        ticketsCol.setCellValueFactory(new PropertyValueFactory<>("tickets"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        datetimeCol.setCellValueFactory(new PropertyValueFactory<>("orderDatetime"));

        loadBookedShowsTable();
    }

    public void loadBookedShowsTable(){
        orderTable.getItems().clear();
        orderList.removeAll(orderList);

        orderList = getAllBookedShows(username);
        orderTable.getItems().setAll(orderList);
    }

    @FXML
    void refresh(ActionEvent event) {
        loadBookedShowsTable();
    }
}
