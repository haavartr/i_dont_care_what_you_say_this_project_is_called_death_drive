package dao;

import entities.Group;
import entities.GroupExercise;

import java.util.List;

public interface GroupExerciseDao {  // Many-to-many thing
    void createGroupExerciseTable();

    void insert(GroupExercise groupExercise);

    List<GroupExercise> selectAll();

    void delete(int groupId, int exerciseId);
}
