package controller;

import dao.IndoorWorkoutDao;
import dao.OutdoorWorkoutDao;
import entities.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NotesViewController implements Initializable {
    @FXML private ListView<String> notesListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<Workout> all = new ArrayList<>();
        all.addAll(IndoorWorkoutDao.selectAll());
        all.addAll(OutdoorWorkoutDao.selectAll());

        for (Workout workout : all) {
            String note = workout.getNote();
            if (!note.equals("")) {
                list.add(note);
            }
        }

        notesListView.setItems(list);
    }
}
