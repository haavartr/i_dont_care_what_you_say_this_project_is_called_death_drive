package dao;

import entities.StrengthExercise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static dao.RunQuery.*;

public class StrengthExerciseDao {
    public static void createStrengthExerciseTable() {
        String q = "CREATE TABLE IF NOT EXISTS strength_exercise (" +
                "id int primary key unique auto_increment," +
                "FOREIGN KEY(id) REFERENCES workout_exercise(id) ON DELETE CASCADE)";
        runUpdate(q);
    }

    public static void insert(StrengthExercise strengthExercise) {
        Statement statement = null;
        String workoutCollectionId = strengthExercise.getWorkoutCollectionId().toString();
        String exerciseId = strengthExercise.getExerciseId().toString();
        String load = strengthExercise.getLoad().toString();
        String repetitions = strengthExercise.getRepetitions().toString();
        String sets = strengthExercise.getSets().toString();
        String form = strengthExercise.getForm().toString();
        String performance = strengthExercise.getPerformance().toString();

        insertInto("workout_exercise", workoutCollectionId, exerciseId, load, repetitions, sets, form, performance);
        try {
            String id = Integer.toString(runQuery("SELECT LAST_INSERT_ID()", statement).getInt(0));
            insertInto("strength_exercise", id);
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
        Statement statement = null;
        String q = String.format("SELECT * FROM strength_exercise JOIN workout_exercise ON strength_exercise.id = %d " +
                "AND workout_exercise.id = %d", id, id);
        ResultSet rs = runQuery(q, statement);
        try {
            if (rs != null) {
                return new StrengthExercise(rs.getInt("id"),
                        rs.getInt("workout_collection_id"),
                        rs.getInt("exercise_id"),
                        rs.getInt("load"),
                        rs.getInt("repetitions"),
                        rs.getInt("sets"),
                        rs.getInt("form"),
                        rs.getInt("performance"));
            } else {
                return null;
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

    public static List<StrengthExercise> selectAll() {
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM strength_exercise JOIN workout_exercise", statement);
        List<StrengthExercise> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new StrengthExercise(rs.getInt("id"),
                            rs.getInt("workout_collection_id"),
                            rs.getInt("exercise_id"),
                            rs.getInt("load"),
                            rs.getInt("repetitions"),
                            rs.getInt("sets"),
                            rs.getInt("form"),
                            rs.getInt("performance")));
                } catch (SQLException|NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return l;
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

    public static void delete(int id) {  // Delete the entry in the highest parent and let the deletion cascade
        runUpdate("DELETE FROM TABLE workout_exercise WHERE id = " + id);
    }

    public static void update(StrengthExercise strengthExercise) {
        String workoutCollectionId = strengthExercise.getWorkoutCollectionId().toString();
        String exerciseId = strengthExercise.getExerciseId().toString();
        String load = strengthExercise.getLoad().toString();
        String repetitions = strengthExercise.getRepetitions().toString();
        String sets = strengthExercise.getSets().toString();
        String form = strengthExercise.getForm().toString();
        String performance = strengthExercise.getPerformance().toString();
        String id = strengthExercise.getId().toString();

        String q = String.format("UPDATE workout_exercise SET workout_collection_id = %s, exercise_id = %s, load = %s," +
                        "repetitions = %s, sets = %s, form = %s, performance = %s WHERE id = %s", workoutCollectionId,
                exerciseId, load, repetitions, sets, form, performance, id);
        runUpdate(q);
    }
}
