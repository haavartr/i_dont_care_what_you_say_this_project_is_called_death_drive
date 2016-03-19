package dao;

import entities.OutdoorWorkout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static dao.RunQuery.insertInto;
import static dao.RunQuery.runQuery;
import static dao.RunQuery.runUpdate;

public class OutdoorWorkoutDao {
    public static void createOutdoorWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS outdoor_workout (" +
                "id int primary key unique not null," +
                "temperature float," +
                "weather varchar(55))," +
                "FOREIGN KEY(id) REFERENCES workout(id) ON DELETE CASCADE);";
        RunQuery.runUpdate(q);
    }

    public static void insert(OutdoorWorkout outdoorWorkout) {
        Statement statement = null;
        String workoutID = "workout_id " + Integer.toString(outdoorWorkout.getId());
        String name = "name " + outdoorWorkout.getName();
        String date = "date " + outdoorWorkout.getDate().toString();
        String length = "length " + Integer.toString(outdoorWorkout.getLength());
        String note = "note " + outdoorWorkout.getNote();
        String temperature = "temperature " + Float.toString(outdoorWorkout.getTemperature());
        String weather = "weather " + outdoorWorkout.getWeather();

        insertInto("workout", workoutID, name, date, length, note);
        try {
            String id = Integer.toString(runQuery("SELECT LAST_INSERT_ID()", statement).getInt(0));
            insertInto("outdoor_workout", id, temperature, weather);
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
    }

    public static OutdoorWorkout selectById(int id) {  // Returns null if the id doesn't exist
        Statement statement = null;
        String q = String.format("SELECT * FROM outdoor_workout JOIN outdoor_workout ON exercise.id = %d " +
                "AND workout.id = %d", id, id);
        ResultSet rs = runQuery(q, statement);
        try {
            if (rs != null) {
                return new OutdoorWorkout(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("date").toLocalDate(),
                        rs.getInt("length"),
                        rs.getString("note"),
                        rs.getFloat("temperature"),
                        rs.getString("weather"));
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

    public static ArrayList<OutdoorWorkout> selectAll() {  // Returns an empty ArrayList if the table is empty
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM outdoor_workout JOIN workout", statement);
        ArrayList<OutdoorWorkout> l = new ArrayList<>();
        try {
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new OutdoorWorkout(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDate("date").toLocalDate(),
                                rs.getInt("length"),
                                rs.getString("note"),
                                rs.getFloat("temperature"),
                                rs.getString("weather")));
                    } catch (SQLException|NullPointerException e) {
                        e.printStackTrace();
                    }
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
        runUpdate("DELETE FROM TABLE workout_collection WHERE id = " + id);
    }

    public static void update(OutdoorWorkout outdoorWorkout) {
        String id = Integer.toString(outdoorWorkout.getId());
        String name = outdoorWorkout.getName();
        String date = outdoorWorkout.getDate().toString();
        String length = Integer.toString(outdoorWorkout.getLength());
        String note = outdoorWorkout.getNote();
        String temp = Float.toString(outdoorWorkout.getTemperature());
        String weather = outdoorWorkout.getWeather();

        String q1 = String.format("UPDATE workout_collection SET name = %s WHERE id = %s", name, id);
        String q2 = String.format("UPDATE workout SET date = %s, length = %s, note = %s WHERE id = %s", name, date, length, note, id);
        String q3 = String.format("UPDATE outdoor_workout SET temperature = %s, weather = %s WHERE id = %s", temp, weather, id);
        runUpdate(q1);
        runUpdate(q2);
        runUpdate(q3);
    }
}
