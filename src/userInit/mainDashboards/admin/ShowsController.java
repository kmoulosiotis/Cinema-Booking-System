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
import models.Show;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static util.Database.*;

public class ShowsController implements Initializable {

    @FXML
    private TableView<Show> showsTable;

    @FXML
    private TableColumn<Show, Integer> colSID;

    @FXML
    private TableColumn<Show, Integer> colCID;

    @FXML
    private TableColumn<Show, Integer> colMID;

    @FXML
    private TableColumn<Show, String> colMovieName;

    @FXML
    private TableColumn<Show, String> colCinemaName;

    @FXML
    private TableColumn<Show, Integer> colScreen;

    @FXML
    private TableColumn<Show, String> colDate;

    @FXML
    private TableColumn<Show, String> colTime;

    @FXML
    private TableColumn<Show, Integer> colSeats;

    ObservableList<Show> showList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        colSID.setCellValueFactory(new PropertyValueFactory<>("sID"));
        colCID.setCellValueFactory(new PropertyValueFactory<>("cID"));
        colMID.setCellValueFactory(new PropertyValueFactory<>("mID"));
        colMovieName.setCellValueFactory(new PropertyValueFactory<>("mName"));
        colCinemaName.setCellValueFactory(new PropertyValueFactory<>("cName"));
        colScreen.setCellValueFactory(new PropertyValueFactory<>("screen"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));

        deleteExpiredShows();
        loadShowsTable();
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

    public void loadShowsTable(){
        showsTable.getItems().clear();
        showList.removeAll(showList);

        showList = getAllShows();
        showsTable.getItems().setAll(showList);
    }

    @FXML
    void RemoveShow(ActionEvent event) {
        Show s = showsTable.getSelectionModel().getSelectedItem();
        deleteShow(s);
        loadShowsTable();
    }

    @FXML
    void addShow(ActionEvent event) throws IOException {
        loadWindow("addShow/addShow.fxml");
    }

    @FXML
    void refresh(ActionEvent event) {
        loadShowsTable();
    }
}
