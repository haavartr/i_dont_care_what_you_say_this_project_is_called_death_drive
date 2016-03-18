package daoimpl;

import dao.IndoorWorkoutDao;
import entities.IndoorWorkout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class IndoorWorkoutImpl implements IndoorWorkoutDao {
    public void createIndoorWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS indoor_workout (" +
                "id int NOT NULL UNIQUE," +
                "air_quality float," +
                "spectators varchar(55))," +
                "PRIMARY KEY(id)," +
                "FOREIGN KEY(id) REFERENCES workout(id) ON DELETE CASCADE)";
        RunQuery.runQuery(q);
    }

    @Override
    public void insert(IndoorWorkout indoorWorkout) {
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

    @Override
    public IndoorWorkout selectById(int id) {
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

    @Override
    public List<IndoorWorkout> selectAll() {
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

    @Override  // Delete the entry in the highest parent and let the deletion cascade
    public void delete(int id) {  // Delete the entry in the highest parent and let the deletion cascade
        runQuery("DELETE FROM TABLE workout_collection WHERE id = " + id);
    }

    @Override
    public void update(IndoorWorkout indoorWorkout) {
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