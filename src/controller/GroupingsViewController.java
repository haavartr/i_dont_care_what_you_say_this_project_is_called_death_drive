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

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllGroupingsToList();

        groupingNameLabel.setVisible(false);
        changeGroupingNameButton.setVisible(false);
        groupingExercisesList.setVisible(false);
        notGroupingExercisesList.setVisible(false);
        addNewGroupingExerciseButton.setVisible(false);

        groupingsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                groupingNameLabel.setVisible(true);
                changeGroupingNameButton.setVisible(true);
                groupingExercisesList.setVisible(true);
                notGroupingExercisesList.setVisible(true);
                addNewGroupingExerciseButton.setVisible(true);

                Grouping selectedGrouping = groupingsList.getSelectionModel().getSelectedItem();

                groupingNameLabel.setText(selectedGrouping.getId().toString());
                loadAllGroupingExercisesForGrouping(selectedGrouping);
            }
        });

        createNewGroupingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GroupingDao.insert(new Grouping());
                loadAllGroupingsToList();
            }
        });

        changeGroupingNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (newGroupingNameField.isVisible()) {
                    newGroupingNameField.setVisible(false);
                    changeGroupingNameButton.setText("Endre navn");
                } else {
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
                    GroupingExerciseDao.insert(new GroupingExercise(groupingsList.getSelectionModel().getSelectedItem().getId(), exerciseToAdd.getId()));
                    loadAllGroupingExercisesForGrouping(groupingsList.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void loadAllGroupingsToList() {
        ObservableList<Grouping> list = FXCollections.observableArrayList();
        /*for (Grouping grouping : GroupingDao.selectAll()) {
            list.add(grouping);
        }*/

        groupingsList.setItems(list);
    }

    private void loadAllGroupingExercisesForGrouping(Grouping grouping) {
        ObservableList<Exercise> list = FXCollections.observableArrayList();

        /*for(GroupingExercise ge : GroupingExerciseDao.selectAll()) {
            if (ge.getGroupingId() == grouping.getId()) {
                list.add(ExerciseDao.selectById(ge.getExerciseId()));
            }
        }*/

        groupingExercisesList.setItems(list);
    }
}