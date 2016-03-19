package controller;

import dao.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.ConnectionConfiguration;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        final Connection connection = ConnectionConfiguration.getConnection();
        ExerciseDao.createExerciseTable();
        GoalDao.createGoalTable();
        GroupingExerciseDao.createGroupingExerciseTable();
        GroupingGroupingDao.createGroupingGroupingTable();
        TemplateDao.createTemplateTable();
        WorkoutCollectionDao.createWorkoutCollectionTable();
        ExerciseReplacementsDao.createExerciseReplacementsTable();
        WorkoutExerciseDao.createWorkoutExerciseTable();
        StrengthExerciseDao.createStrengthExerciseTable();
        CardioExerciseDao.createCardioExerciseTable();
        WorkoutDao.createWorkoutTable();
        IndoorWorkoutDao.createIndoorWorkoutTable();
        OutdoorWorkoutDao.createOutdoorWorkoutTable();

        Parent root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
