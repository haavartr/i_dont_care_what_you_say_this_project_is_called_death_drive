package daoimpl;

import dao.GroupExerciseDao;
import entities.GroupExercise;
import entities.GroupGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;
import static daoimpl.RunQuery.runUpdate;

public class GroupExerciseImpl implements GroupExerciseDao {
    @Override
    public void createGroupExerciseTable() {
        String q = ("CREATE TABLE IF NOT EXISTS group_exercise (" +
                "group_id INT NOT NULL," +
                "exercise_id INT NOT NULL)");
        runUpdate(q);
    }

    @Override
    public void insert(GroupExercise groupExercise) {
        insertInto("group_exercise", groupExercise.getGroupId().toString(), groupExercise.getExerciseId().toString());
    }

    @Override
    public List<GroupExercise> selectAll() {
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM group_exercise", statement);
        List<GroupExercise> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new GroupExercise(rs.getInt("group_id"), rs.getInt("exercise_id")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void delete(int groupId, int exerciseId) {
        String q = String.format("DELETE FROM TABLE group_exercise WHERE group_id = %d AND exercise_id = %d", groupId, exerciseId);
        runUpdate(q);
    }
}
