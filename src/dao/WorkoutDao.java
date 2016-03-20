package dao;

import static dao.RunQuery.runUpdate;

public class WorkoutDao {  // Parent
    public static void createWorkoutTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout (" +
                    "id INT NOT NULL UNIQUE," +
                    "date datetime NOT NULL DEFAULT current_timestamp," +
                    "length int," +
                    "note varchar (255)," +
                    "PRIMARY KEY(id)," +
                    "FOREIGN KEY(id) REFERENCES workout_collection(id) ON DELETE CASCADE);";
        runUpdate(q);
    }
}
