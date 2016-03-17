package dao;

import entities.StrengthExercise;

import java.util.List;

public interface StrengthExerciseDao {
    void createStrengthExerciseTable();

    void insert(StrengthExercise strengthExercise);

    StrengthExercise selectById(int id);

    List<StrengthExercise> selectAll();

    void delete(int id);

    void update();
}
