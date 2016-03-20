package dao;

import entities.GroupingExercise;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static dao.RunQuery.insertInto;
import static dao.RunQuery.runQuery;
import static dao.RunQuery.runUpdate;

public class GroupingExerciseDao {  // Many-to-many
    public static void createGroupingExerciseTable() {
        String q = ("CREATE TABLE IF NOT EXISTS grouping_exercise (" +
                "grouping_id INT NOT NULL," +
                "exercise_id INT NOT NULL)");
        runUpdate(q);
    }

    public static void insert(GroupingExercise groupingExercise) {
        String groupingId = "grouping_id " + groupingExercise.getGroupingId().toString();
        String exerciseId = "exercise_id " + groupingExercise.getExerciseId().toString();

        insertInto("grouping_exercise", groupingId, exerciseId);
    }

    public static ArrayList<GroupingExercise> selectAll() {  // Returns an empty ArrayList if the table is empty
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = "SELECT * FROM grouping_exercise";
        ArrayList<GroupingExercise> l = new ArrayList<>();
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new GroupingExercise(rs.getInt("grouping_id"), rs.getInt("exercise_id")));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return l;
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
            return l;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void delete(int groupingId, int exerciseId) {
        String q = String.format("DELETE FROM grouping_exercise WHERE grouping_id = %d AND exercise_id = %d", groupingId, exerciseId);
        runUpdate(q);
    }
}
