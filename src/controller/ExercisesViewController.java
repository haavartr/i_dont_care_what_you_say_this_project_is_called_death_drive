package controller;

import dao.ExerciseDao;
import entities.Exercise;
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
import java.util.List;
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

    private List<Exercise> exercises = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newExercisePane.setVisible(false);

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
                    ExerciseDao.insert(new Exercise(0, newExerciseName.getText(), newExerciseDescription.getText()));
                    loadAllWorkoutsToList();

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
        exercises = ExerciseDao.selectAll();
        ObservableList<Exercise> list = FXCollections.observableArrayList();
        for (Exercise exercise : exercises) {
            list.add(exercise);
        }

        exerciseList.setItems(list);
    }
}
