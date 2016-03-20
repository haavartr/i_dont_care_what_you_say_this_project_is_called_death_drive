package controller;

import dao.*;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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
    @FXML private Button deleteGroupingButton;

    @FXML private Label supergroupTitleLabel;
    @FXML private ComboBox<Grouping> supergroupsList;
    @FXML private Button saveSupergroupButton;

    @FXML private Label containsGroupsLabel;
    @FXML private ListView<Grouping> containsGroupsList;
    @FXML private Label belongsToGroupsLabel;
    @FXML private ListView<Grouping> belongsToGroupsList;

    private Grouping selectedGrouping;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupingsList.setItems(Helper.getAllGroupings());
        setControlsToVisible(false);

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
                newGrouping.setName("Ny gruppe");

                GroupingDao.insert(newGrouping);
                groupingsList.setItems(Helper.getAllGroupings());
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
                        groupingsList.setItems(Helper.getAllGroupings());
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

        deleteGroupingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GroupingDao.delete(selectedGrouping.getId());
                groupingsList.setItems(Helper.getAllGroupings());
                setControlsToVisible(false);
            }
        });

        saveSupergroupButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Grouping selected = supergroupsList.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    GroupingGroupingDao.insert(new GroupingGrouping(selected.getId(), selectedGrouping.getId()));
                    getSupergroups();
                    supergroupsList.setItems(Helper.getAllGroupingsExcept(belongsToGroupsList.getItems()));
                }
            }
        });
    }

    private void setControlsToVisible(Boolean visible) {
        groupingNameLabel.setVisible(visible);
        changeGroupingNameButton.setVisible(visible);
        groupingExercisesList.setVisible(visible);
        notGroupingExercisesList.setVisible(visible);
        addNewGroupingExerciseButton.setVisible(visible);
        removeGroupingExerciseButton.setVisible(visible);
        notGroupingExercisesTitleLabel.setVisible(visible);
        groupingExercisesTitleLabel.setVisible(visible);
        deleteGroupingButton.setVisible(visible);
        supergroupsList.setVisible(visible);
        supergroupTitleLabel.setVisible(visible);
        saveSupergroupButton.setVisible(visible);
        containsGroupsList.setVisible(visible);
        containsGroupsLabel.setVisible(visible);
        belongsToGroupsLabel.setVisible(visible);
        belongsToGroupsList.setVisible(visible);
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

        getSubgroups();
        getSupergroups();
        groupingNameLabel.setText(selectedGrouping.getName());
        loadAllGroupingExercisesForGrouping();

        supergroupsList.setItems(Helper.getAllGroupingsExcept(belongsToGroupsList.getItems()));

        setControlsToVisible(true);
    }

    private void getSupergroups() {
        ObservableList<Grouping> list = FXCollections.observableArrayList();

        for(GroupingGrouping gg : GroupingGroupingDao.selectAll()) {
            if(gg.getContainedGroupingId().equals(selectedGrouping.getId())) {
                list.add(GroupingDao.selectById(gg.getContainerGroupingId()));
            }
        }

        belongsToGroupsList.setItems(list);
    }

    private void getSubgroups() {
        ObservableList<Grouping> list = FXCollections.observableArrayList();

        for(GroupingGrouping gg : GroupingGroupingDao.selectAll()) {
            if(gg.getContainerGroupingId().equals(selectedGrouping.getId())) {
                list.add(GroupingDao.selectById(gg.getContainedGroupingId()));
            }
        }

        containsGroupsList.setItems(list);
    }
}
