package controller;

import dao.ExerciseDao;
import dao.GoalDao;
import dao.GroupingDao;
import dao.GroupingExerciseDao;
import entities.Exercise;
import entities.Goal;
import entities.Grouping;
import entities.GroupingExercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import util.Helper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Magnus on 19.03.2016.
 */
public class GroupingsViewController implements Initializable {

    @FXML private ListView<Grouping> groupingsList;
    @FXML private Button createNewGroupingButton;

    @FXML private Label groupingNameLabel;
    @FXML private TextField newGroupingNameField;
    @FXML private Button changeGroupingNameButton;
    @FXML private ListView<Exercise> groupingExercisesList;
    @FXML private ComboBox<Exercise> notGroupingExercisesList;
    @FXML private Button addNewGroupingExerciseButton;
    @FXML private Button removeGroupingExerciseButton;
    @FXML private Label notGroupingExercisesTitleLabel;
    @FXML private Label groupingExercisesTitleLabel;

    Grouping selectedGrouping;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllGroupingsToList();
        hideAllControls();

        groupingsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Grouping selection = groupingsList.getSelectionModel().getSelectedItem();
                if (selection != null) {
                    selectGrouping(selection);
                }
            }
        });

        createNewGroupingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Grouping newGrouping = new Grouping();
                newGrouping.setName("New grouping");

                GroupingDao.insert(newGrouping);
                loadAllGroupingsToList();
            }
        });

        changeGroupingNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (newGroupingNameField.isVisible()) {
                    if(!newGroupingNameField.getText().equals("")) {
                        selectedGrouping.setName(newGroupingNameField.getText());
                        newGroupingNameField.setText("");
                        GroupingDao.update(selectedGrouping);
                        loadAllGroupingsToList();
                        selectGrouping(selectedGrouping);
                    }

                    newGroupingNameField.setVisible(false);
                    changeGroupingNameButton.setText("Endre navn");
                } else {
                    newGroupingNameField.setPromptText(selectedGrouping.getName());
                    newGroupingNameField.setVisible(true);
                    changeGroupingNameButton.setText("Lagre");
                }
            }
        });

        addNewGroupingExerciseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Exercise exerciseToAdd = notGroupingExercisesList.getSelectionModel().getSelectedItem();
                if (exerciseToAdd != null) {
                    GroupingExerciseDao.insert(new GroupingExercise(selectedGrouping.getId(), exerciseToAdd.getId()));
                    loadAllGroupingExercisesForGrouping();
                }
            }
        });

        removeGroupingExerciseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Exercise exerciseToRemove = groupingExercisesList.getSelectionModel().getSelectedItem();
                if (exerciseToRemove != null) {
                    GroupingExerciseDao.delete(selectedGrouping.getId(), exerciseToRemove.getId());
                    loadAllGroupingExercisesForGrouping();
                }
            }
        });
    }

    private void loadAllGroupingsToList() {
        ObservableList<Grouping> list = FXCollections.observableArrayList();

        for (Grouping grouping : GroupingDao.selectAll()) {
            list.add(grouping);
        }

        groupingsList.setItems(list);
    }

    private void hideAllControls() {
        groupingNameLabel.setVisible(false);
        changeGroupingNameButton.setVisible(false);
        groupingExercisesList.setVisible(false);
        notGroupingExercisesList.setVisible(false);
        addNewGroupingExerciseButton.setVisible(false);
        removeGroupingExerciseButton.setVisible(false);
        notGroupingExercisesTitleLabel.setVisible(false);
        groupingExercisesTitleLabel.setVisible(false);
    }

    private void showAllControls() {
        groupingNameLabel.setVisible(true);
        changeGroupingNameButton.setVisible(true);
        groupingExercisesList.setVisible(true);
        notGroupingExercisesList.setVisible(true);
        addNewGroupingExerciseButton.setVisible(true);
        removeGroupingExerciseButton.setVisible(true);
        notGroupingExercisesTitleLabel.setVisible(true);
        groupingExercisesTitleLabel.setVisible(true);
    }

    private void loadAllGroupingExercisesForGrouping() {
        ObservableList<Exercise> list = FXCollections.observableArrayList();

        for(GroupingExercise ge : GroupingExerciseDao.selectAll()) {
            if (ge.getGroupingId().equals(selectedGrouping.getId())) {
                list.add(ExerciseDao.selectById(ge.getExerciseId()));
            }
        }

        groupingExercisesList.setItems(list);
        notGroupingExercisesList.setItems(Helper.getAllExercisesExcept(list));
    }

    private void selectGrouping(Grouping grouping) {
        selectedGrouping = grouping;

        groupingNameLabel.setText(selectedGrouping.getName());
        loadAllGroupingExercisesForGrouping();

        showAllControls();
    }
}
