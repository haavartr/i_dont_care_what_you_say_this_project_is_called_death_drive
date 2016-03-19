package daoimpl;

import dao.IndoorWorkoutDao;
import entities.IndoorWorkout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class IndoorWorkoutImpl {
    public static void createIndoorWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS indoor_workout (" +
                "id int primary key unique auto_increment," +
                "air_quality float," +
                "spectators varchar(55))," +
                "FOREIGN KEY(id) REFERENCES workout(id) ON DELETE CASCADE);";
        RunQuery.runQuery(q);
    }

    public static void insert(IndoorWorkout indoorWorkout) {
        String workoutID = Integer.toString(indoorWorkout.getId());
        String name = indoorWorkout.getName();
        String date = indoorWorkout.getDate().toString();
        String length = Integer.toString(indoorWorkout.getLength());
        String note = indoorWorkout.getNote();
        String temp = Integer.toString(indoorWorkout.getAirQuality());
        String spectators = Integer.toString(indoorWorkout.getSpectators());

        insertInto("workout", workoutID, name, date, length, note);
        try {
            String id = Integer.toString(runQuery("SELECT LAST_INSERT_ID()").getInt(0));
            insertInto("indoor_workout", id, temp, spectators);
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static IndoorWorkout selectById(int id) {
        String q = String.format("SELECT * FROM indoor_workout JOIN indoor_workout ON indoor_workout.id = %d " +
                "AND workout.id = %d", id, id);
        ResultSet rs = runQuery(q);
        try {
            return new IndoorWorkout(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("date").toLocalDate(),
                    rs.getInt("length"),
                    rs.getString("note"),
                    rs.getInt("air_quality"),
                    rs.getInt("spectators"));
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<IndoorWorkout> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM indoor_workout JOIN workout");
        ArrayList<IndoorWorkout> l = new ArrayList<>();
        try {
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
            return l;
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete the entry in the highest parent and let the deletion cascade
    public static void delete(int id) {  // Delete the entry in the highest parent and let the deletion cascade
        runQuery("DELETE FROM TABLE workout_collection WHERE id = " + id);
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
        runQuery(q1);
        runQuery(q2);
        runQuery(q3);
    }
}