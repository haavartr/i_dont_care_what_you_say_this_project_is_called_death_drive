package dao;

import entities.IndoorWorkout;
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

public class IndoorWorkoutDao {
    public static void createIndoorWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS indoor_workout (" +
                "id INT NOT NULL UNIQUE," +
                "air_quality float," +
                "spectators varchar(55)," +
                "PRIMARY KEY(id)," +
                "FOREIGN KEY(id) REFERENCES workout(id) ON DELETE CASCADE);";
        RunQuery.runUpdate(q);
    }

    public static void insert(IndoorWorkout indoorWorkout) {
        String workoutID = "workout_id " + Integer.toString(indoorWorkout.getId());
        String name = "name " + indoorWorkout.getName();
        String date = "date " + indoorWorkout.getDate().toString();
        String length = "length " + Integer.toString(indoorWorkout.getLength());
        String note = "note " + indoorWorkout.getNote();
        String airQuality = "air_quality " + Integer.toString(indoorWorkout.getAirQuality());
        String spectators = "spectators " + Integer.toString(indoorWorkout.getSpectators());
        insertInto("workout", workoutID, name, date, length, note);

        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT COUNT(*) as last_id from workout_collection");
            String id = "id ";
            if (rs.next()) {
                id += rs.getString("last_id");
                System.out.println(id);
            }
            insertInto("indoor_workout", id, airQuality, spectators);
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static IndoorWorkout selectById(int id) {
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = String.format("SELECT * FROM indoor_workout JOIN indoor_workout ON indoor_workout.id = %d " +
                "AND workout.id = %d", id, id);
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                return new IndoorWorkout(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("date").toLocalDate(),
                        rs.getInt("length"),
                        rs.getString("note"),
                        rs.getInt("air_quality"),
                        rs.getInt("spectators"));
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

    public static ArrayList<IndoorWorkout> selectAll() {  // Returns an empty ArrayList if the table is empty
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = "SELECT * FROM indoor_workout JOIN workout";
        ArrayList<IndoorWorkout> l = new ArrayList<>();
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new IndoorWorkout(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDate("date").toLocalDate(),
                                rs.getInt("length"),
                                rs.getString("note"),
                                rs.getInt("air_quality"),
                                rs.getInt("spectators")));
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
        runUpdate("DELETE FROM workout_collection WHERE id = " + id);
    }

    public static void update(IndoorWorkout indoorWorkout) {
        String id = Integer.toString(indoorWorkout.getId());
        String name = indoorWorkout.getName();
        String date = indoorWorkout.getDate().toString();
        String length = Integer.toString(indoorWorkout.getLength());
        String note = indoorWorkout.getNote();
        String airQuality = Float.toString(indoorWorkout.getAirQuality());
        String spectators = indoorWorkout.getSpectators().toString();

        String q1 = String.format("UPDATE workout_collection SET name = %s WHERE id = %s", name, id);
        String q2 = String.format("UPDATE workout SET date = %s, length = %s, note = %s WHERE id = %s", date, length, note, id);
        String q3 = String.format("UPDATE indoor_workout SET air_quality = %s, spectators = %s WHERE id = %s", airQuality, spectators, id);
        runUpdate(q1);
        runUpdate(q2);
        runUpdate(q3);
    }
}