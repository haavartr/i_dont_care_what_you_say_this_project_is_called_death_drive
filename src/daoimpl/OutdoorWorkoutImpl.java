package daoimpl;

import dao.OutdoorWorkoutDao;
import entities.OutdoorWorkout;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OutdoorWorkoutImpl implements OutdoorWorkoutDao{
    @Override
    public void createOutdoorWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS outdoor_workout (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55)," +
                    "description varchar(255))";
        RunQuery.runQuery(q);
    }

    @Override
    public void insert(OutdoorWorkout outdoorWorkout) {

    }

    @Override
    public OutdoorWorkout selectById(int id) {
        return null;
    }

    @Override
    public List<OutdoorWorkout> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
