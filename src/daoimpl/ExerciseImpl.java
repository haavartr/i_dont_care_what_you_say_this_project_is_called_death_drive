package daoimpl;

import dao.ExerciseDao;
import entities.CardioExercise;
import entities.Exercise;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class ExerciseImpl {
    public static void createExerciseTable() {
        String q = ("CREATE TABLE IF NOT EXISTS exercise (" +
                "id int primary key unique auto_increment," +
                "name varchar(55)," +
                "description varchar(255))");
        runQuery(q);
    }

    public static void insert(Exercise exercise) {
        insertInto("exercise", exercise.getName(), exercise.getDescription());
    }

    public static Exercise selectById(int id) {
        ResultSet rs = runQuery("SELECT * FROM exercise WHERE id = " + id);
        try {
            return new Exercise(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Exercise> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM exercise");
        List<Exercise> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new Exercise(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public static void delete(int id) {
        runQuery("DELETE FROM TABLE exercise WHERE id = " + id);
    }

    public static void update(Exercise exercise) {
        String name = exercise.getName();
        String description = exercise.getDescription();
        String id = Integer.toString(exercise.getId());
        String q = String.format("UPDATE workout_exercise SET name = %s, description = %s WHERE id = %s", name, description, id);
        runQuery(q);
    }
}
