package dao;

import entities.WorkoutExercise;

import java.util.List;

public interface WorkoutExerciseDao {
    void createWorkoutExerciseTable();

    void insert(WorkoutExercise workoutExercise);

    WorkoutExercise selectById(int id);

    List<WorkoutExercise> selectAll();

    void delete(int id);

    void update();
}
