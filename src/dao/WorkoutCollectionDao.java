package dao;

import entities.WorkoutCollection;

import java.util.List;

public interface WorkoutCollectionDao {
    void createWorkoutCollectionTable();

    void insert(WorkoutCollection workoutCollection);

    WorkoutCollection selectById(int id);

    List<WorkoutCollection> selectAll();

    void delete(int id);

    void update();
}
