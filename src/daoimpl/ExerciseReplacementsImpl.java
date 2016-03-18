package daoimpl;

import dao.ExerciseReplacementsDao;
import entities.CardioExercise;
import entities.Exercise;
import entities.ExerciseReplacements;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class ExerciseReplacementsImpl implements ExerciseReplacementsDao {
    @Override
    public void createExerciseReplacementsTable() {
        String q = "CREATE TABLE IF NOT EXISTS exercise_replacements (" +
                    "id_1 int primary key unique auto_increment," +
                    "id_2 int)";
        runQuery(q);
    }

    @Override
    public void insert(ExerciseReplacements exerciseReplacements) {
        insertInto("exercise_replacements", Integer.toString(exerciseReplacements.getExerciseId1()), Integer.toString(exerciseReplacements.getExerciseId2()));
    }

    @Override
    public ExerciseReplacements selectById(int id) {
        ResultSet rs = runQuery("SELECT * FROM exercise_replacements WHERE id = " + id);
        try {
            return new ExerciseReplacements(rs.getInt("id1"), rs.getInt("id_2"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ExerciseReplacements> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM exercise_replacements");
        List<ExerciseReplacements> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new ExerciseReplacements(rs.getInt("id_1"), rs.getInt("id_2")));
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
    public void delete(int id) {
        runQuery("DELETE FROM TABLE exercise_replacements WHERE id_1 = " + id);
    }

    @Override
    public void update() {

    }
}
