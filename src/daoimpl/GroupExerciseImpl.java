package daoimpl;

import dao.GroupExerciseDao;
import entities.GroupExercise;
import entities.GroupGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class GroupExerciseImpl implements GroupExerciseDao {
    @Override
    public void createGroupExerciseTable() {
        String q = ("CREATE TABLE IF NOT EXISTS group_exercise (" +
                "group_id INT NOT NULL," +
                "exercise_id INT NOT NULL)");
        runQuery(q);
    }

    @Override
    public void insert(GroupExercise groupExercise) {
        insertInto("group_exercise", groupExercise.getGroupId().toString(), groupExercise.getExerciseId().toString());
    }

    @Override
    public List<GroupExercise> selectAll() {
        return null;
    }

    @Override
    public void delete(int groupId, int exerciseId) {
        String q = String.format("DELETE FROM TABLE group_exercise WHERE group_id = %d AND exercise_id = %d", groupId, exerciseId);
        runQuery(q);
    }
}
