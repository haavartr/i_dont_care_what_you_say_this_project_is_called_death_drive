package controller;

import dao.*;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.Helper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * Created by Magnus on 17.03.2016.
 */
public class ExercisesViewController implements Initializable {

    @FXML private ListView<Exercise> exerciseList;
    @FXML private Button createNewExerciseButton;
    @FXML private Text exerciseNameLabel;
    @FXML private Text exerciseDescriptionLabel;
    @FXML private Label canBeReplacedByLabel;

    @FXML private Button addNewExerciseReplacement;
    @FXML private ListView<Exercise> exerciseReplacementsList;
    @FXML private ComboBox<Exercise> notExerciseReplacementsList;

    @FXML private AnchorPane newExercisePane;
    @FXML private Button cancelNewExercise;
    @FXML private Button saveNewExercise;
    @FXML private TextField newExerciseName;
    @FXML private TextArea newExerciseDescription;
    @FXML private Label exerciseLogLabel;
    @FXML private ListView<Workout> exerciseLogList;

    private Exercise selectedExercise;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newExercisePane.setVisible(false);
        setControlsToVisible(false);

        exerciseList.setItems(Helper.getAllExercises());

        exerciseList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Exercise selection = exerciseList.getSelectionModel().getSelectedItem();
                if (selection != null) {
                    selectExercise(selection);
                }

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
                    exerciseList.setItems(Helper.getAllExercises());

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

        addNewExerciseReplacement.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Exercise selectedExerciseReplacement = notExerciseReplacementsList.getSelectionModel().getSelectedItem();

                if (selectedExerciseReplacement != null) {
                    Exercise selectedExercise = exerciseList.getSelectionModel().getSelectedItem();

                    ExerciseReplacementsDao.insert(new ExerciseReplacements(selectedExercise.getId(), selectedExerciseReplacement.getId()));
                    setExerciseReplacements();
                }
            }
        });

        exerciseReplacementsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Exercise selection = exerciseReplacementsList.getSelectionModel().getSelectedItem();

                if(selection != null) {
                    selectExercise(exerciseReplacementsList.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void selectExercise(Exercise exercise) {
        selectedExercise = exercise;
        exerciseList.getSelectionModel().select(selectedExercise);

        exerciseNameLabel.setText(exercise.getName());
        exerciseDescriptionLabel.setText(exercise.getDescription());
        setExerciseReplacements();

        ObservableList<Workout> isInWorkouts = FXCollections.observableArrayList();
        ArrayList<WorkoutExercise> allWorkoutExercises = new ArrayList<>();
        allWorkoutExercises.addAll(StrengthExerciseDao.selectAll());
        allWorkoutExercises.addAll(CardioExerciseDao.selectAll());

        for(WorkoutExercise e : allWorkoutExercises) {
            if (e.getExerciseId().equals(selectedExercise.getId())) {
                IndoorWorkout iw = IndoorWorkoutDao.selectById(e.getWorkoutCollectionId());
                if (iw == null) {
                    OutdoorWorkout ow = OutdoorWorkoutDao.selectById(e.getWorkoutCollectionId());
                    isInWorkouts.add(ow);
                } else {
                    isInWorkouts.add(iw);
                }

            }
        }

        exerciseLogList.setItems(isInWorkouts);

        setControlsToVisible(true);
    }

    private void setExerciseReplacements() {
        ObservableList<Exercise> replacements = FXCollections.observableArrayList();

        for(ExerciseReplacements er : ExerciseReplacementsDao.selectAll()) {
            if(er.getExerciseId1().equals(selectedExercise.getId())) {
                replacements.add(ExerciseDao.selectById(er.getExerciseId2()));
            } else if(er.getExerciseId2().equals(selectedExercise.getId())){
                replacements.add(ExerciseDao.selectById(er.getExerciseId1()));
            }
        }
        exerciseReplacementsList.setItems(replacements);
        notExerciseReplacementsList.setItems(Helper.getAllExercisesExcept(replacements));
    }

    private void setControlsToVisible(Boolean visible) {
        canBeReplacedByLabel.setVisible(visible);
        exerciseNameLabel.setVisible(visible);
        exerciseDescriptionLabel.setVisible(visible);
        addNewExerciseReplacement.setVisible(visible);
        exerciseReplacementsList.setVisible(visible);
        notExerciseReplacementsList.setVisible(visible);
        exerciseLogLabel.setVisible(visible);
        exerciseLogList.setVisible(visible);
    }
}
