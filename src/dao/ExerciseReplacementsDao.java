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

public class ExerciseReplacementsDao {  // Many-to-many
    public static void createExerciseReplacementsTable() {
        String q = "CREATE TABLE IF NOT EXISTS exercise_replacements (" +
                    "exercise_id_1 INT NOT NULL," +
                    "exercise_id_2 INT NOT NULL)";
        runUpdate(q);
    }

    public static void insert(ExerciseReplacements exerciseReplacements) {
        String exerciseId1 = "exercise_id_1 " + exerciseReplacements.getExerciseId1().toString();
        String exerciseId2 = "exercise_id_2 " + exerciseReplacements.getExerciseId2().toString();
        insertInto("exercise_replacements", exerciseId1, exerciseId2);
    }

    public static ArrayList<ExerciseReplacements> selectAll() {  // Returns an empty ArrayList if the table is empty
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM exercise_replacements", statement);
        ArrayList<ExerciseReplacements> l = new ArrayList<>();
        try {
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new ExerciseReplacements(rs.getInt("exercise_id_1"), rs.getInt("exercise_id_2")));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
