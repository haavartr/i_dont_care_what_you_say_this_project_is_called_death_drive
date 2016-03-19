package dao;

import entities.ExerciseReplacements;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static dao.RunQuery.insertInto;
import static dao.RunQuery.runQuery;
import static dao.RunQuery.runUpdate;

public class ExerciseReplacementsDao {
    public static void createExerciseReplacementsTable() {
        String q = "CREATE TABLE IF NOT EXISTS exercise_replacements (" +
                    "exercise_id_1 INT NOT NULL," +
                    "exercise_id_2 INT NOT NULL)";
        runUpdate(q);
    }

    public static void insert(ExerciseReplacements exerciseReplacements) {
        insertInto("exercise_replacements", Integer.toString(exerciseReplacements.getExerciseId1()), Integer.toString(exerciseReplacements.getExerciseId2()));
    }

    public static List<ExerciseReplacements> selectAll() {
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM exercise_replacements", statement);
        List<ExerciseReplacements> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new ExerciseReplacements(rs.getInt("exercise_id_1"), rs.getInt("exercise_id_2")));
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

    public static void delete(int exerciseId1, int exerciseId2) {
        String q = String.format("DELETE FROM TABLE exercise_replacements WHERE exercise_id_1 = %d AND exercise_id_2 = %d",
                exerciseId1, exerciseId2);
        runUpdate(q);
    }
}
