package dao;

import entities.Workout;

import java.util.List;

public interface WorkoutDao {
    void createWorkoutTable();

    void insert(Workout workout);

    Workout selectById(int id);

    List<Workout> selectAll();

    void delete(int id);

    void update();
}
