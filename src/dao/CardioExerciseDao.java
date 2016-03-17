package dao;

import entities.CardioExercise;

import java.util.List;

public interface CardioExerciseDao {
    void createCardioExerciseTable();

    void insert(CardioExercise cardioExercise);

    CardioExercise selectById(int id);

    List<CardioExercise> selectAll();

    void delete(int id);

    void update();
}
