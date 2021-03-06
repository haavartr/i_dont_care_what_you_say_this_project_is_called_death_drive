package dao;

import entities.CardioExercise;
import util.ConnectionConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

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
        String workoutCollectionId = "workout_collection_id " + cardioExercise.getWorkoutCollectionId().toString();
        String exerciseId = "exercise_id " + cardioExercise.getExerciseId().toString();
        String form = "form " + cardioExercise.getForm().toString();
        String performance = "performance " + cardioExercise.getPerformance().toString();
        String distance = "distance " + cardioExercise.getDistance().toString();
        String time = "time " + cardioExercise.getTime().toString();
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
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = String.format("SELECT * FROM cardio_exercise JOIN workout_exercise ON cardio_exercise.id = %d " +
                "AND workout_exercise.id = %d", id, id);
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                return new CardioExercise(rs.getInt("id"),
                        rs.getInt("workout_collection_id"),
                        rs.getInt("exercise_id"),
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
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

    public static ArrayList<CardioExercise> selectAll() {  // Returns an empty ArrayList if the table is empty
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = "SELECT * FROM cardio_exercise JOIN workout_exercise ON cardio_exercise.id = workout_exercise.id  GROUP BY cardio_exercise.id";
        ArrayList<CardioExercise> l = new ArrayList<>();
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new CardioExercise(rs.getInt("id"),
                                rs.getInt("workout_collection_id"),
                                rs.getInt("exercise_id"),
                                rs.getInt("form"),
                                rs.getInt("performance"),
                                rs.getInt("distance"),
                                rs.getInt("time")));
                    } catch (SQLException|NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
            return l;
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
            return l;
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

    public static void delete(int id) {  // Delete the entry in the highest parent and let the deletion cascade
        runUpdate("DELETE FROM workout_exercise WHERE id = " + id);
    }

    public static void update(CardioExercise cardioExercise) {
        String workoutCollectionId = cardioExercise.getWorkoutCollectionId().toString();
        String exerciseId = cardioExercise.getExerciseId().toString();
        String form = cardioExercise.getForm().toString();
        String performance = cardioExercise.getPerformance().toString();
        String distance = cardioExercise.getDistance().toString();
        String time = cardioExercise.getTime().toString();
        String id = cardioExercise.getId().toString();

        String q1 = String.format("UPDATE workout_exercise SET workout_collection_id = %s, exercise_id = %s, weight = %s," +
                "repetitions = %s, sets = %s, form = %s, performance = %s WHERE id = %s", workoutCollectionId,
                exerciseId, form, performance, id);
        String q2 = String.format("UPDATE cardio_exercise SET distance = %s, time = %s WHERE id = %s", distance, time, id);
        runUpdate(q1);
        runUpdate(q2);
    }
}
