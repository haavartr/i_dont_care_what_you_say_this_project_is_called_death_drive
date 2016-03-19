package controller;

import dao.GoalDao;
import entities.Exercise;
import entities.Goal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Helper;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by Magnus on 19.03.2016.
 */
public class GoalsViewController implements Initializable {

    @FXML private ListView<Goal> goalsList;
    @FXML private Button createNewGoalButton;
    @FXML private TextField goalLoadField;
    @FXML private TextField goalRepetitionsField;
    @FXML private TextField goalSetsField;

    // NEW GOAL PANE
    @FXML private ComboBox<Exercise> allExercisesList;
    @FXML private AnchorPane newGoalPane;
    @FXML private Button saveNewGoalButton;
    @FXML private Button cancelNewGoalButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllGoalsToList();
        newGoalPane.setVisible(false);

        createNewGoalButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newGoalPane.setVisible(true);
                allExercisesList.setItems(Helper.getAllExercises());
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
                    goal.setLoad(Integer.parseInt(goalLoadField.getText()));
                    goal.setRepetitions(Integer.parseInt(goalRepetitionsField.getText()));
                    goal.setSets(Integer.parseInt(goalSetsField.getText()));
                } catch (Exception e) {
                    System.out.println("Feil");
                }

                if (goal.getLoad() == null && goal.getRepetitions() == null && goal.getSets() == null) {
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
}
