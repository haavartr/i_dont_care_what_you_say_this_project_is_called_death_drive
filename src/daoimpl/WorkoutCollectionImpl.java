package daoimpl;

import dao.WorkoutCollectionDao;
import entities.WorkoutCollection;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class WorkoutCollectionImpl implements WorkoutCollectionDao{
    @Override
    public void createWorkoutCollectionTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout_collection (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55))";
        runQuery(q);
    }
}
