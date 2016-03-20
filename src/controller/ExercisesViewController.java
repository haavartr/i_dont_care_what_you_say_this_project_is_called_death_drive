package controller;

import dao.ExerciseDao;
import dao.ExerciseReplacementsDao;
import entities.Exercise;
import entities.ExerciseReplacements;
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

    Exercise selectedExercise;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newExercisePane.setVisible(false);
        hideAllControls();

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

        showAllControls();
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

    private void hideAllControls() {
        canBeReplacedByLabel.setVisible(false);
        exerciseNameLabel.setVisible(false);
        exerciseDescriptionLabel.setVisible(false);
        addNewExerciseReplacement.setVisible(false);
        exerciseReplacementsList.setVisible(false);
        notExerciseReplacementsList.setVisible(false);
    }

    private void showAllControls() {
        canBeReplacedByLabel.setVisible(true);
        exerciseNameLabel.setVisible(true);
        exerciseDescriptionLabel.setVisible(true);
        addNewExerciseReplacement.setVisible(true);
        exerciseReplacementsList.setVisible(true);
        notExerciseReplacementsList.setVisible(true);
    }
}
