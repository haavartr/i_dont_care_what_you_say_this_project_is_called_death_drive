package daoimpl;

import dao.IndoorWorkoutDao;
import entities.IndoorWorkout;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.runQuery;

public class IndoorWorkoutImpl implements IndoorWorkoutDao {
    @Override
    public void createIndoorWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS indoor_workout (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55)," +
                    "description varchar(255))";
        runQuery(q);
    }

    @Override
    public void insert(IndoorWorkout indoorWorkout) {

    }

    @Override
    public IndoorWorkout selectById(int id) {
        return null;
    }

    @Override
    public List<IndoorWorkout> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
