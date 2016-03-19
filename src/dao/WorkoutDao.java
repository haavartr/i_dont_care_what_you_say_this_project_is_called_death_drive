package dao;

import static dao.RunQuery.runUpdate;

public class WorkoutDao {  // Parent
    public static void createWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout (" +
                    "id int primary key unique auto_increment," +
                    "date datetime NOT NULL DEFAULT current_timestamp," +
                    "length int," +
                    "note varchar (255));";
        runUpdate(q);
    }
}
