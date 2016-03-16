package dao;

import entities.Exercise;

import java.util.List;

public interface ExerciseDao {
    void createExerciseTable();

    void insert(Exercise exercise);

    Exercise selectById(int id);

    List<Exercise> selectAll();

    void delete(int id);

    void update();
}
