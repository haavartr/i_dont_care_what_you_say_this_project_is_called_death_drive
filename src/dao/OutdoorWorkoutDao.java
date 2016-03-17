package dao;

import entities.OutdoorWorkout;

import java.util.List;

public interface OutdoorWorkoutDao {
    void createOutdoorWorkoutTable();

    void insert(OutdoorWorkout outdoorWorkout);

    OutdoorWorkout selectById(int id);

    List<OutdoorWorkout> selectAll();

    void delete(int id);

    void update();
}
