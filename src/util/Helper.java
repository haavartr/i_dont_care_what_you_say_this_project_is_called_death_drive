package util;

import dao.ExerciseDao;
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
}
