package controller;

import dao.*;
import entities.CardioExercise;
import entities.WorkoutExercise;
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
        /*Parent root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();

        final Connection connection = ConnectionConfiguration.getConnection();
        //ExerciseDao.createExerciseTable();
        //GoalDao.createGoalsTable();
        //WorkoutExerciseDao.createWorkoutExerciseTable();
        //StrengthExerciseDao.createStrengthExerciseTable();
        //CardioExerciseDao.createCardioExerciseTable();

        //WorkoutDao.createWorkoutTable();
        //IndoorWorkoutDao.createIndoorWorkoutTable();
        //OutdoorWorkoutDao.createOutdoorWorkoutTable();*/
    }

    public static void main(String[] args) {
        CardioExercise ce = new CardioExercise(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        CardioExerciseDao.createCardioExerciseTable();
        WorkoutExerciseDao.createWorkoutExerciseTable();
        CardioExerciseDao.insert(ce);

        launch(args);
    }
}
