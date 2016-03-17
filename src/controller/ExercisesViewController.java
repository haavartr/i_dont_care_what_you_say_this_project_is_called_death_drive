package controller;

import entities.Exercise;
import entities.Workout;
import entities.WorkoutExercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Magnus on 17.03.2016.
 */
public class ExercisesViewController implements Initializable {

    @FXML private ListView<Exercise> exerciseList;
    @FXML private Button createNewExerciseButton;
    @FXML private Text exerciseNameLabel;
    @FXML private Text exerciseDescriptionLabel;

    @FXML private AnchorPane newExercisePane;
    @FXML private Button cancelNewExercise;
    @FXML private Button saveNewExercise;
    @FXML private TextField newExerciseName;
    @FXML private TextArea newExerciseDescription;

    private ArrayList<Exercise> exercises = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newExercisePane.setVisible(false);

        exercises.add(new Exercise("Markløft", "Olsen"));
        exercises.add(new Exercise("Knebøy", "Pedersen"));
        exercises.add(new Exercise("Pushups", "Johnsen"));
        exercises.add(new Exercise("Benkpress", "Knutsen"));

        loadAllWorkoutsToList();

        exerciseList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Exercise exercise = exerciseList.getSelectionModel().getSelectedItem();

                exerciseNameLabel.setText(exercise.getName());
                exerciseDescriptionLabel.setText(exercise.getDescription());
            }
        });

        createNewExerciseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newExercisePane.setVisible(true);
            }
        });

        saveNewExercise.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!newExerciseName.getText().equals("")) {
                    exercises.add(new Exercise(newExerciseName.getText(), newExerciseDescription.getText()));

                    loadAllWorkoutsToList();
                    newExercisePane.setVisible(false);

                }
            }
        });

        cancelNewExercise.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newExercisePane.setVisible(false);
            }
        });
    }

    private void loadAllWorkoutsToList() {
        ObservableList<Exercise> list = FXCollections.observableArrayList();
        for (Exercise exercise : exercises) {
            list.add(exercise);
        }

        exerciseList.setItems(list);
    }
}
