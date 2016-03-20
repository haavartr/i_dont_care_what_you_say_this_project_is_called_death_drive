package dao;

import entities.StrengthExercise;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static dao.RunQuery.*;

public class StrengthExerciseDao {
    public static void createStrengthExerciseTable() {
        String q = "CREATE TABLE IF NOT EXISTS strength_exercise (" +
                "id INT NOT NULL UNIQUE," +
                "weight int, " +
                "repetitions int, " +
                "sets int, " +
                "PRIMARY KEY(id)," +
                "FOREIGN KEY(id) REFERENCES workout_exercise(id) ON DELETE CASCADE)";
        runUpdate(q);
    }

    public static void insert(StrengthExercise strengthExercise) {
        String workoutCollectionId = "workout_collection_id " + strengthExercise.getWorkoutCollectionId().toString();
        String exerciseId = "exercise_id " + strengthExercise.getExerciseId().toString();
        String weight = "weight " + strengthExercise.getWeight().toString();

        String repetitions = "repetitions " + strengthExercise.getRepetitions().toString();
        String sets = "sets " + strengthExercise.getSets().toString();
        String form = "form " + strengthExercise.getForm().toString();
        String performance = "performance " + strengthExercise.getPerformance().toString();

        insertInto("workout_exercise", workoutCollectionId, exerciseId, form, performance);
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT COUNT(*) as last_id from workout_exercise");
            String id = "id ";
            if (rs.next()) {
                id += rs.getString("last_id");
                System.out.println(id);
            }
            insertInto("strength_exercise", id, weight, repetitions, sets);
        } catch (SQLException |NullPointerException e) {
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
    }

    public static StrengthExercise selectById(int id) {  // Returns null if the id doesn't exist
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = String.format("SELECT * FROM strength_exercise JOIN workout_exercise ON strength_exercise.id = %d " +
                "AND workout_exercise.id = %d", id, id);
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                return new StrengthExercise(rs.getInt("id"),
                        rs.getInt("workout_collection_id"),
                        rs.getInt("exercise_id"),
                        rs.getInt("weight"),
                        rs.getInt("repetitions"),
                        rs.getInt("sets"),
                        rs.getInt("form"),
                        rs.getInt("performance"));
            }
        } catch (SQLException|NullPointerException e) {
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

    public static ArrayList<StrengthExercise> selectAll() {  // Returns an empty ArrayList if the table is empty
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = "SELECT * FROM strength_exercise JOIN workout_exercise";
        ArrayList<StrengthExercise> l = new ArrayList<>();
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new StrengthExercise(rs.getInt("id"),
                                rs.getInt("workout_collection_id"),
                                rs.getInt("exercise_id"),
                                rs.getInt("weight"),
                                rs.getInt("repetitions"),
                                rs.getInt("sets"),
                                rs.getInt("form"),
                                rs.getInt("performance")));
                    } catch (SQLException|NullPointerException e) {
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

    public static void delete(int id) {  // Delete the entry in the highest parent and let the deletion cascade
        runUpdate("DELETE FROM workout_exercise WHERE id = " + id);
    }

    public static void update(StrengthExercise strengthExercise) {
        String workoutCollectionId = strengthExercise.getWorkoutCollectionId().toString();
        String exerciseId = strengthExercise.getExerciseId().toString();
        String weight = strengthExercise.getWeight().toString();
        String repetitions = strengthExercise.getRepetitions().toString();
        String sets = strengthExercise.getSets().toString();
        String form = strengthExercise.getForm().toString();
        String performance = strengthExercise.getPerformance().toString();
        String id = strengthExercise.getId().toString();

        String q = String.format("UPDATE workout_exercise SET workout_collection_id = %s, exercise_id = %s, weight = %s," +
                        "repetitions = %s, sets = %s, form = %s, performance = %s WHERE id = %s", workoutCollectionId,
                exerciseId, weight, repetitions, sets, form, performance, id);
        runUpdate(q);
    }
}
