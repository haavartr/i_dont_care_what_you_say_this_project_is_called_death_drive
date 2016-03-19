package dao;

import entities.GroupExercise;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static dao.RunQuery.insertInto;
import static dao.RunQuery.runQuery;
import static dao.RunQuery.runUpdate;

public class GroupExerciseDao {
    public static void createGroupExerciseTable() {
        String q = ("CREATE TABLE IF NOT EXISTS group_exercise (" +
                "group_id INT NOT NULL," +
                "exercise_id INT NOT NULL)");
        runUpdate(q);
    }

    public static void insert(GroupExercise groupExercise) {
        insertInto("group_exercise", groupExercise.getGroupId().toString(), groupExercise.getExerciseId().toString());
    }

    public static List<GroupExercise> selectAll() {
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

    public static void delete(int groupId, int exerciseId) {
        String q = String.format("DELETE FROM TABLE group_exercise WHERE group_id = %d AND exercise_id = %d", groupId, exerciseId);
        runUpdate(q);
    }
}
