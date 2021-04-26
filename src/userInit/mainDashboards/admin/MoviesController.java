package userInit.mainDashboards.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Movie;

import static util.Database.deleteMovie;
import static util.Database.getAllMovies;

public class MoviesController implements Initializable {

    @FXML
    private TableView<Movie> movieTable;

    @FXML
    private TableColumn<Movie, Integer> colId;

    @FXML
    private TableColumn<Movie, String> colName;

    @FXML
    private TableColumn<Movie, String> colDirector;

    @FXML
    private TableColumn<Movie, String> colCast;

    @FXML
    private TableColumn<Movie, String> colDetails;

    @FXML
    private TableColumn<Movie, Float> colRating;

    ObservableList<Movie> moviesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colCast.setCellValueFactory(new PropertyValueFactory<>("cast"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        loadMoviesTable();
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

    public void loadMoviesTable(){
        movieTable.getItems().clear();
        moviesList.removeAll(moviesList);

        moviesList = getAllMovies();
        movieTable.getItems().setAll(moviesList);
    }

    @FXML
    void addMovie(ActionEvent event) throws IOException {
        loadWindow("addMovie/addMovie.fxml");
    }

    @FXML
    void refresh(ActionEvent event) {
        loadMoviesTable();
    }

    @FXML
    void removeMovie(ActionEvent event) {
        Movie m = movieTable.getSelectionModel().getSelectedItem();
        deleteMovie(m);
        loadMoviesTable();
    }

}
