package dao;

import entities.IndoorWorkout;

import java.util.List;

public interface IndoorWorkoutDao {
    void createIndoorWorkoutTable();

    void insert(IndoorWorkout indoorWorkout);

    IndoorWorkout selectById(int id);

    List<IndoorWorkout> selectAll();

    void delete(int id);

    void update();
}
