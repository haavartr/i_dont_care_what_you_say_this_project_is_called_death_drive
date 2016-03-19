package controller;

import dao.GoalDao;
import dao.GroupDao;
import dao.GroupExerciseDao;
import entities.Exercise;
import entities.Goal;
import entities.Group;
import entities.GroupExercise;
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
public class GroupsViewController implements Initializable {

    @FXML private ListView<Group> groupsList;
    @FXML private Button createNewGroupButton;

    @FXML private Label groupNameLabel;
    @FXML private TextField newGroupNameField;
    @FXML private Button changeGroupNameButton;
    @FXML private ListView<Exercise> groupExercisesList;
    @FXML private ComboBox<Exercise> notGroupExercisesList;
    @FXML private Button addNewGroupExerciseButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllGroupsToList();

        groupNameLabel.setVisible(false);
        changeGroupNameButton.setVisible(false);
        groupExercisesList.setVisible(false);
        notGroupExercisesList.setVisible(false);
        addNewGroupExerciseButton.setVisible(false);

        groupsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                groupNameLabel.setVisible(true);
                changeGroupNameButton.setVisible(true);
                groupExercisesList.setVisible(true);
                notGroupExercisesList.setVisible(true);
                addNewGroupExerciseButton.setVisible(true);

                Group selectedGroup = groupsList.getSelectionModel().getSelectedItem();

                groupNameLabel.setText(selectedGroup.getId().toString());
                loadAllGroupExercisesForGroup(selectedGroup);
            }
        });

        createNewGroupButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GroupDao.insert(new Group());
                loadAllGroupsToList();
            }
        });

        changeGroupNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (newGroupNameField.isVisible()) {
                    newGroupNameField.setVisible(false);
                    changeGroupNameButton.setText("Endre navn");
                } else {
                    newGroupNameField.setVisible(true);
                    changeGroupNameButton.setText("Lagre");
                }
            }
        });

        addNewGroupExerciseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Exercise exerciseToAdd = notGroupExercisesList.getSelectionModel().getSelectedItem();
                if (exerciseToAdd != null) {
                    GroupExerciseDao.insert(new GroupExercise(groupsList.getSelectionModel().getSelectedItem().getId(), exerciseToAdd.getId()));
                }
            }
        });
    }

    private void loadAllGroupsToList() {
        ObservableList<Group> list = FXCollections.observableArrayList();
        for (Group group : GroupDao.selectAll()) {
            list.add(group);
        }

        groupsList.setItems(list);
    }

    private void loadAllGroupExercisesForGroup(Group group) {
        ObservableList<GroupExercise> list = FXCollections.observableArrayList();

        for(GroupExercise ge : GroupExerciseDao.selectAll()) {
            if (ge.getGroupId() == group.getId()) {
                list.add(ge);
            }
        }

        groupExercisesList.setItems(list);
    }
}
