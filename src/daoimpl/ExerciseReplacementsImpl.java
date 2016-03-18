package daoimpl;

import dao.ExerciseReplacementsDao;
import entities.ExerciseReplacements;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class ExerciseReplacementsImpl implements ExerciseReplacementsDao {
    @Override
    public void createExerciseReplacementsTable() {
        String q = "CREATE TABLE IF NOT EXISTS exercise_replacements (" +
                    "exercise_id_1 INT NOT NULL," +
                    "exercise_id_2 INT NOT NULL)";
        runQuery(q);
    }

    @Override
    public void insert(ExerciseReplacements exerciseReplacements) {
        insertInto("exercise_replacements", Integer.toString(exerciseReplacements.getExerciseId1()), Integer.toString(exerciseReplacements.getExerciseId2()));
    }

    @Override
    public List<ExerciseReplacements> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM exercise_replacements");
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
        }
        return null;
    }

    @Override
    public void delete(int exerciseId1, int exerciseId2) {
        String q = String.format("DELETE FROM TABLE exercise_replacements WHERE exercise_id_1 = %d AND exercise_id_2 = %d",
                exerciseId1, exerciseId2);
        runQuery(q);
    }
}
