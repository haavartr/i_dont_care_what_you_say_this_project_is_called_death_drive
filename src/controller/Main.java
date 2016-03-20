package controller;

import dao.*;
import entities.Grouping;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.ConnectionConfiguration;
import util.Helper;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        final Connection connection = ConnectionConfiguration.getConnection();

        ExerciseDao.createExerciseTable();
        GoalDao.createGoalTable();
        GroupingDao.createGroupingTable();
        GroupingExerciseDao.createGroupingExerciseTable();
        GroupingGroupingDao.createGroupingGroupingTable();
        WorkoutCollectionDao.createWorkoutCollectionTable();
        TemplateDao.createTemplateTable();
        ExerciseReplacementsDao.createExerciseReplacementsTable();
        WorkoutExerciseDao.createWorkoutExerciseTable();
        StrengthExerciseDao.createStrengthExerciseTable();
        CardioExerciseDao.createCardioExerciseTable();
        WorkoutDao.createWorkoutTable();
        IndoorWorkoutDao.createIndoorWorkoutTable();
        OutdoorWorkoutDao.createOutdoorWorkoutTable();

        //Helper.deleteAllTables();

        Parent root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
        primaryStage.setTitle("Treningsdagbok");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
