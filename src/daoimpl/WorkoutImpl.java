package daoimpl;

import static daoimpl.RunQuery.runUpdate;

public class WorkoutImpl {
    public static void createWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout (" +
                    "id int primary key unique auto_increment," +
                    "date datetime NOT NULL DEFAULT current_timestamp," +
                    "length int," +
                    "note varchar (255));";
        runUpdate(q);
    }
}
