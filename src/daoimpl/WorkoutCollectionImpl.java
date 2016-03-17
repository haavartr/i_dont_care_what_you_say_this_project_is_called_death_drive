package daoimpl;

import dao.WorkoutCollectionDao;
import entities.WorkoutCollection;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.runQuery;

public class WorkoutCollectionImpl implements WorkoutCollectionDao{
    @Override
    public void createWorkoutCollectionTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout_collection (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55)," +
                    "description varchar(255))";
        runQuery(q);
    }

    @Override
    public void insert(WorkoutCollection workoutCollection) {

    }

    @Override
    public WorkoutCollection selectById(int id) {
        return null;
    }

    @Override
    public List<WorkoutCollection> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
