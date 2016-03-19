package daoimpl;

import dao.WorkoutDao;
import entities.Workout;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import static daoimpl.RunQuery.runQuery;

public class WorkoutImpl {
    public static void createWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout (" +
                    "id int primary key unique auto_increment," +
                    "date datetime NOT NULL DEFAULT current_timestamp," +
                    "length int," +
                    "note varchar (255));";
        runQuery(q);
    }
}
