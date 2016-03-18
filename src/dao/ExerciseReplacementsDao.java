package dao;

import entities.ExerciseReplacements;

import java.util.List;

public interface ExerciseReplacementsDao {
    void createExerciseReplacementsTable();

    void insert(ExerciseReplacements exerciseReplacements);

    ExerciseReplacements selectById(int id);

    List<ExerciseReplacements> selectAll();

    void delete(int id);

    void update(ExerciseReplacements exerciseReplacements);
}
