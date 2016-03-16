package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class WorkoutsViewController implements Initializable {

    // Root pane
    @FXML private ListView workoutList;
    @FXML private Label timeLabel;
    @FXML private Label durationLabel;
    @FXML private TextArea descriptionText;
    @FXML private ListView exerciseList;
    @FXML private Button createTemplateButton;
    @FXML private Button createNewWorkoutButton;

    // New workout pane
    @FXML private AnchorPane newWorkoutPane;
    @FXML private Button addNewWorkoutExercise;
    @FXML private Button saveWorkoutButton;

    // New workout exercise pane
    @FXML private AnchorPane newWorkoutExercisePane;
    @FXML private Button saveWorkoutExerciseButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hideAllControls();
        loadAllWorkoutsToList();

        newWorkoutPane.setVisible(false);

        workoutList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String title = (String)workoutList.getSelectionModel().getSelectedItem();

                System.out.println(title);
                timeLabel.setText(title);

                showAllControls();
            }
        });

        createNewWorkoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newWorkoutPane.setVisible(true);
                newWorkoutExercisePane.setVisible(false);
            }
        });

        addNewWorkoutExercise.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newWorkoutExercisePane.setVisible(true);
                saveWorkoutButton.setDisable(true);
            }
        });

        saveWorkoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newWorkoutPane.setVisible(false);
            }
        });

        saveWorkoutExerciseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newWorkoutExercisePane.setVisible(false);
                saveWorkoutButton.setDisable(false);
            }
        });
    }

    private void hideAllControls() {
        timeLabel.setVisible(false);
        durationLabel.setVisible(false);
        descriptionText.setVisible(false);
        createTemplateButton.setVisible(false);
        exerciseList.setVisible(false);
    }

    private void showAllControls() {
        timeLabel.setVisible(true);
        durationLabel.setVisible(true);
        descriptionText.setVisible(true);
        createTemplateButton.setVisible(true);
        exerciseList.setVisible(true);
    }

    private void loadAllWorkoutsToList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("16.03.2016 - 13:30");
        list.add("15.03.2016 - 14:27");
        list.add("13.03.2016 - 21:12");

        workoutList.setItems(list);
    }
}
