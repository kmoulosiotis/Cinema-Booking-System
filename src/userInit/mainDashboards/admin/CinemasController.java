package userInit.mainDashboards.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Cinema;
import models.Movie;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static util.Database.deleteCinema;
import static util.Database.getAllCinemas;

public class CinemasController implements Initializable {


    @FXML
    private TableView<Cinema> cinemaTable;

    @FXML
    private TableColumn<Cinema, Integer> colId;

    @FXML
    private TableColumn<Cinema, String> colName;

    @FXML
    private TableColumn<Cinema, Integer> colScreen;

    @FXML
    private TableColumn<Cinema, String> colAddress;

    @FXML
    private TableColumn<Cinema, Float> colTickets;

    @FXML
    private TableColumn<Cinema, Float> colAdult;

    @FXML
    private TableColumn<Cinema, Float> colChild;

    @FXML
    private TableColumn<Cinema, Float> colStudent;

    @FXML
    private TableColumn<Cinema, Float> colFamily;

    ObservableList<Cinema> cinemaList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colScreen.setCellValueFactory(new PropertyValueFactory<>("screen"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAdult.setCellValueFactory(new PropertyValueFactory<>("adult"));
        colChild.setCellValueFactory(new PropertyValueFactory<>("child"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("student"));
        colFamily.setCellValueFactory(new PropertyValueFactory<>("family"));

        loadCinemaTable();

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

    public void loadCinemaTable(){
        cinemaTable.getItems().clear();
        cinemaList.removeAll(cinemaList);

        cinemaList = getAllCinemas();
        cinemaTable.getItems().setAll(cinemaList);
    }

    @FXML
    void addCinema(ActionEvent event) throws IOException {
        loadWindow("addCinema/addCinema.fxml");
    }

    @FXML
    void refresh(ActionEvent event) {
        loadCinemaTable();
    }

    @FXML
    void removeCinema(ActionEvent event) {
        Cinema c = cinemaTable.getSelectionModel().getSelectedItem();
        deleteCinema(c);
        loadCinemaTable();
    }


}
