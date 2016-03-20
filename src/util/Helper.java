package util;

import dao.ExerciseDao;
import dao.RunQuery;
import entities.Exercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Magnus on 19.03.2016.
 */
public class Helper {
    public static ObservableList<Exercise> getAllExercises() {
        ObservableList<Exercise> list = FXCollections.observableArrayList();

        for (Exercise exercise : ExerciseDao.selectAll()) {
            list.add(exercise);
        }

        return list;
    }

    public static ObservableList<Exercise> getAllExercisesExcept(ObservableList<Exercise> exceptions) {
        ObservableList<Exercise> list = FXCollections.observableArrayList();

        for (Exercise exercise : ExerciseDao.selectAll()) {
            if(exceptions.stream().noneMatch(e -> e.getId().equals(exercise.getId()))) {
                list.add(exercise);
            }
        }
        return list;
    }

    public static void deleteAllTables() {
        RunQuery.runUpdate("DROP TABLE cardio_exercise");
        RunQuery.runUpdate("DROP TABLE exercise");
        RunQuery.runUpdate("DROP TABLE exercise_replacements");
        RunQuery.runUpdate("DROP TABLE goal");
        RunQuery.runUpdate("DROP TABLE grouping");
        RunQuery.runUpdate("DROP TABLE grouping_exercise");
        RunQuery.runUpdate("DROP TABLE grouping_grouping");
        RunQuery.runUpdate("DROP TABLE indoor_workout");
        RunQuery.runUpdate("DROP TABLE outdoor_workout");
        RunQuery.runUpdate("DROP TABLE strength_exercise");
        RunQuery.runUpdate("DROP TABLE template");
        RunQuery.runUpdate("DROP TABLE workout_collection");
        RunQuery.runUpdate("DROP TABLE workout");
        RunQuery.runUpdate("DROP TABLE workout_exercise");
    }
}
