package dao;

import entities.ExerciseReplacements;

import java.util.List;

public interface ExerciseReplacementsDao {  // Many-to-many thing
    void createExerciseReplacementsTable();

    void insert(ExerciseReplacements exerciseReplacements);

    List<ExerciseReplacements> selectAll();

    void delete(int exerciseId1, int exerciseId2);
}
