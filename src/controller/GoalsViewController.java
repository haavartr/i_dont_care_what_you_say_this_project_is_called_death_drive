package controller;

import dao.ExerciseDao;
import dao.GoalDao;
import entities.Exercise;
import entities.Goal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Helper;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Magnus on 19.03.2016.
 */
public class GoalsViewController implements Initializable {

    // LEFT COLUMN
    @FXML private ListView<Goal> goalsList;
    @FXML private Button createNewGoalButton;

    // GOAL INFORMATION
    @FXML private Label goalExerciseNameLabel;
    @FXML private Label goalSetDateLabel;
    @FXML private Label goalWeightLabel;
    @FXML private Label goalRepetitionsLabel;
    @FXML private Label goalSetsLabel;
    @FXML private Label goalWeightTitleLabel;
    @FXML private Label goalRepetitionsTitleLabel;
    @FXML private Label goalSetsTitleLabel;

    // NEW GOAL PANE
    @FXML private ComboBox<Exercise> allExercisesList;
    @FXML private AnchorPane newGoalPane;
    @FXML private Button saveNewGoalButton;
    @FXML private Button cancelNewGoalButton;

    @FXML private TextField goalWeightField;
    @FXML private TextField goalRepetitionsField;
    @FXML private TextField goalSetsField;
    @FXML private TextField goalDistanceField;
    @FXML private TextField goalDurationField;

    @FXML private Label newGoalDistanceLabel;
    @FXML private Label newGoalDurationLabel;
    @FXML private Label newGoalWeightLabel;
    @FXML private Label newGoalRepsLabel;
    @FXML private Label newGoalSetsLabel;

    @FXML private ToggleButton strengthCardioToggle;

    Goal selectedGoal;

    Boolean strength = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllGoalsToList();
        hideAllControls();
        newGoalPane.setVisible(false);

        goalsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Goal selection = goalsList.getSelectionModel().getSelectedItem();

                if(selection != null) {
                    selectGoal(selection);
                }
            }
        });

        createNewGoalButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newGoalPane.setVisible(true);
                allExercisesList.setItems(Helper.getAllExercises());
            }
        });

        strengthCardioToggle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                strength = !strengthCardioToggle.isSelected();
                if(strength) {
                    strengthCardioToggle.setText("Styrke");
                } else {
                    strengthCardioToggle.setText("Utholdenhet");
                }

                goalWeightField.setVisible(strength);
                goalRepetitionsField.setVisible(strength);
                goalSetsField.setVisible(strength);
                goalDistanceField.setVisible(!strength);
                goalDurationField.setVisible(!strength);
            }
        });

        saveNewGoalButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Exercise selectedExercise = allExercisesList.getSelectionModel().getSelectedItem();

                if (selectedExercise == null) {
                    System.out.println("Må velge øvelse.");
                    return;
                }

                Goal goal = new Goal();
                goal.setDate(LocalDate.now());
                goal.setExercise(selectedExercise.getId());

                try {
                    goal.setWeight(Integer.parseInt(goalWeightField.getText()));
                    goal.setRepetitions(Integer.parseInt(goalRepetitionsField.getText()));
                    goal.setSets(Integer.parseInt(goalSetsField.getText()));
                } catch (Exception e) {
                    System.out.println("Feil");
                }

                if (goal.getWeight() == null && goal.getRepetitions() == null && goal.getSets() == null) {
                    System.out.println("Må ha ei målsetning");
                    return;
                }

                GoalDao.insert(goal);
                newGoalPane.setVisible(false);
                loadAllGoalsToList();
            }
        });

        cancelNewGoalButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newGoalPane.setVisible(false);
            }
        });
    }

    private void loadAllGoalsToList() {
        ObservableList<Goal> list = FXCollections.observableArrayList();

        for (Goal goal : GoalDao.selectAll()) {
            list.add(goal);
        }

        goalsList.setItems(list);
    }

    private void selectGoal(Goal goal) {
        selectedGoal = goal;
        showAllControls();

        goalExerciseNameLabel.setText(ExerciseDao.selectById(selectedGoal.getExercise()).getName());
        goalSetDateLabel.setText(selectedGoal.getDate().toString());
        goalWeightLabel.setText(selectedGoal.getWeight().toString());
        goalRepetitionsLabel.setText(selectedGoal.getRepetitions().toString());
        goalSetsLabel.setText(selectedGoal.getSets().toString());

    }

    private void hideAllControls() {
        goalWeightTitleLabel.setVisible(false);
        goalRepetitionsTitleLabel.setVisible(false);
        goalSetsTitleLabel.setVisible(false);
        goalExerciseNameLabel.setVisible(false);
        goalSetDateLabel.setVisible(false);
        goalWeightLabel.setVisible(false);
        goalRepetitionsLabel.setVisible(false);
        goalSetsLabel.setVisible(false);
    }

    private void showAllControls() {
        goalWeightTitleLabel.setVisible(true);
        goalRepetitionsTitleLabel.setVisible(true);
        goalSetsTitleLabel.setVisible(true);
        goalExerciseNameLabel.setVisible(true);
        goalSetDateLabel.setVisible(true);
        goalWeightLabel.setVisible(true);
        goalRepetitionsLabel.setVisible(true);
        goalSetsLabel.setVisible(true);
    }
}
