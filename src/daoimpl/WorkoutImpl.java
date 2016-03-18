package daoimpl;

import dao.WorkoutDao;
import entities.Workout;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static daoimpl.RunQuery.runQuery;

public class WorkoutImpl implements WorkoutDao{
    public void createWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55)," +
                    "description varchar(255))";
        runQuery(q);
    }
}
