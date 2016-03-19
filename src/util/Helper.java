package util;

import dao.ExerciseDao;
import entities.Exercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

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
}
