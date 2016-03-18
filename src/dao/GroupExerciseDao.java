package dao;

import entities.Group;
import entities.GroupExercise;

import java.util.List;

public interface GroupExerciseDao {
    void createGroupExerciseTable();

    void insert(GroupExercise groupExercise);

    GroupExercise selectById(int id);

    List<GroupExercise> selectAll();

    void delete(int id);

    void update(GroupExercise groupExercise);
}
