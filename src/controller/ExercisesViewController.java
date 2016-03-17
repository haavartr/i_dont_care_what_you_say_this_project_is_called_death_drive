package controller;

import entities.Exercise;
import entities.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Magnus on 17.03.2016.
 */
public class ExercisesViewController implements Initializable {

    @FXML private ListView exerciseList;
    @FXML private Button createNewExerciseButton;

    private ArrayList<Exercise> exercises = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exercises.add(new Exercise("Markløft", "Olsen"));
        exercises.add(new Exercise("Knebøy", "Pedersen"));
        exercises.add(new Exercise("Pushups", "Johnsen"));
        exercises.add(new Exercise("Benkpress", "Knutsen"));

        loadAllWorkoutsToList();
    }

    private void loadAllWorkoutsToList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Exercise exercise : exercises) {
            list.add(exercise.getName());
        }

        exerciseList.setItems(list);
    }
}
