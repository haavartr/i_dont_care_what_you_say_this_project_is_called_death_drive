package dao;

import entities.CardioExercise;
import java.sql.*;
import java.util.ArrayList;
import static dao.RunQuery.*;

public class CardioExerciseDao {
    public static void createCardioExerciseTable() {
        String q = "CREATE TABLE IF NOT EXISTS cardio_exercise (" +
                "id INT NOT NULL UNIQUE," +
                "distance INT," +
                "time INT," +
                "PRIMARY KEY(id)," +
                "FOREIGN KEY(id) REFERENCES workout_exercise(id) ON DELETE CASCADE)";
        runUpdate(q);
    }

    public static void insert(CardioExercise cardioExercise) {
        Statement statement = null;
        String workoutCollectionId = cardioExercise.getWorkoutCollectionId().toString();
        String exerciseId = cardioExercise.getExerciseId().toString();
        String load = cardioExercise.getLoad().toString();
        String repetitions = cardioExercise.getRepetitions().toString();
        String sets = cardioExercise.getSets().toString();
        String form = cardioExercise.getForm().toString();
        String performance = cardioExercise.getPerformance().toString();
        String distance = cardioExercise.getDistance().toString();
        String time = cardioExercise.getTime().toString();

        insertInto("workout_exercise", workoutCollectionId, exerciseId, load, repetitions, sets, form, performance);
        try {
            String id = Integer.toString(runQuery("SELECT LAST_INSERT_ID()", statement).getInt(0));
            insertInto("cardio_exercise", id, distance, time);
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static CardioExercise selectById(int id) {  // Returns null if the id doesn't exist
        Statement statement = null;
        String q = String.format("SELECT * FROM cardio_exercise JOIN workout_exercise ON cardio_exercise.id = %d " +
                "AND workout_exercise.id = %d", id, id);
        ResultSet rs = runQuery(q, statement);
        try {
            if (rs != null) {
                return new CardioExercise(rs.getInt("id"),
                        rs.getInt("workout_collection_id"),
                        rs.getInt("exercise_id"),
                        rs.getInt("load"),
                        rs.getInt("repetitions"),
                        rs.getInt("sets"),
                        rs.getInt("form"),
                        rs.getInt("performance"),
                        rs.getInt("distance"),
                        rs.getInt("time"));
            } else {
                return null;
            }
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        finally {
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

    public static ArrayList<CardioExercise> selectAll() {
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM cardio_exercise JOIN workout_exercise", statement);
        ArrayList<CardioExercise> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new CardioExercise(rs.getInt("id"),
                            rs.getInt("workout_collection_id"),
                            rs.getInt("exercise_id"),
                            rs.getInt("load"),
                            rs.getInt("repetitions"),
                            rs.getInt("sets"),
                            rs.getInt("form"),
                            rs.getInt("performance"),
                            rs.getInt("distance"),
                            rs.getInt("time")));
                } catch (SQLException|NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return l;
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        finally {
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

    // Delete the entry in the highest parent and let the deletion cascade
    public static void delete(int id) {
        runUpdate("DELETE FROM TABLE workout_exercise WHERE id = " + id);
    }

    public static void update(CardioExercise cardioExercise) {
        String workoutCollectionId = cardioExercise.getWorkoutCollectionId().toString();
        String exerciseId = cardioExercise.getExerciseId().toString();
        String load = cardioExercise.getLoad().toString();
        String repetitions = cardioExercise.getRepetitions().toString();
        String sets = cardioExercise.getSets().toString();
        String form = cardioExercise.getForm().toString();
        String performance = cardioExercise.getPerformance().toString();
        String distance = cardioExercise.getDistance().toString();
        String time = cardioExercise.getTime().toString();
        String id = cardioExercise.getId().toString();

        String q1 = String.format("UPDATE workout_exercise SET workout_collection_id = %s, exercise_id = %s, load = %s," +
                "repetitions = %s, sets = %s, form = %s, performance = %s WHERE id = %s", workoutCollectionId,
                exerciseId, load, repetitions, sets, form, performance, id);
        String q2 = String.format("UPDATE cardio_exercise SET distance = %s, time = %s WHERE id = %s", distance, time, id);
        runUpdate(q1);
        runUpdate(q2);
    }
}
