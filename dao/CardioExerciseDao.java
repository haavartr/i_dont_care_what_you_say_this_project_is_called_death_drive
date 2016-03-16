package dao;

import entities.CardioExercise;

import java.util.List;

public interface CardioExerciseDao {
    void createPersonTable();

    void insert(CardioExercise person);

    CardioExercise selectById(int id);

    List<CardioExercise> selectAll();

    void delete(int id);

    void update();
}
