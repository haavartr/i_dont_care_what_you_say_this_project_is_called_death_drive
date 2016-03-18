package daoimpl;

import dao.OutdoorWorkoutDao;
import entities.OutdoorWorkout;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class OutdoorWorkoutImpl implements OutdoorWorkoutDao{
    @Override
    public void createOutdoorWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS outdoor_workout (" +
                "id int NOT NULL UNIQUE," +
                "temperature float," +
                "weather varchar(55))," +
                "PRIMARY KEY(id)," +
                "FOREIGN KEY(id) REFERENCES workout(id) ON DELETE CASCADE)";
        RunQuery.runQuery(q);
    }

    @Override
    public void insert(OutdoorWorkout outdoorWorkout) {
        String workoutID = Integer.toString(outdoorWorkout.getId());
        String name = outdoorWorkout.getName();
        String date = outdoorWorkout.getDate().toString();
        String length = Integer.toString(outdoorWorkout.getLength());
        String note = outdoorWorkout.getNote();
        String temp = Float.toString(outdoorWorkout.getTemperature());
        String weather = outdoorWorkout.getWeather();

        insertInto("workout", workoutID, name, date, length, note);
        try {
            String id = Integer.toString(runQuery("SELECT LAST_INSERT_ID()").getInt(0));
            insertInto("outdoor_workout", id, temp, weather);
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OutdoorWorkout selectById(int id) {
        String q = String.format("SELECT * FROM outdoor_workout JOIN outdoor_workout ON exercise.id = %d " +
                "AND workout.id = %d", id, id);
        ResultSet rs = runQuery(q);
        try {
            return new OutdoorWorkout(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("date").toLocalDate(),
                    rs.getInt("length"),
                    rs.getString("note"),
                    rs.getFloat("temperature"),
                    rs.getString("weather"));
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OutdoorWorkout> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM outdoor_workout JOIN workout");
        ArrayList<OutdoorWorkout> l = new ArrayList<>();
        try {
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
            return l;
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        runQuery("DELETE FROM TABLE outdoor_workout WHERE id = " + id);
    }

    @Override
    public void update(OutdoorWorkout outdoorWorkout) {
        String id = Integer.toString(outdoorWorkout.getId());
        String name = outdoorWorkout.getName();
        String date = outdoorWorkout.getDate().toString();
        String length = Integer.toString(outdoorWorkout.getLength());
        String note = outdoorWorkout.getNote();
        String temp = Float.toString(outdoorWorkout.getTemperature());
        String weather = outdoorWorkout.getWeather();

        String q1 = String.format("UPDATE workout SET id = %s, name = %s, date = %s," +
                        "length = %s, note = %s", id, name, date, length, note);
        String q2 = String.format("UPDATE outdoor_workout SET temperature = %s, weather = %s WHERE id = %s", temp, weather, id);
        runQuery(q1);
        runQuery(q2);
    }
}
