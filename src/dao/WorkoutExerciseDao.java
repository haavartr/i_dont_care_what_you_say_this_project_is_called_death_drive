package dao;

import entities.WorkoutExercise;

import java.util.List;

/**
 * Created by hans on 17.03.16.
 */
public interface WorkoutExerciseDao {
    void createWorkoutExerciseTable();


    void insert(WorkoutExercise exercise);

    WorkoutExercise selectById(int id);

    List<WorkoutExercise> selectAll();

    void delete(int id);

    void update();
}
