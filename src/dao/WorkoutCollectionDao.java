package dao;

import static dao.RunQuery.runUpdate;

public class WorkoutCollectionDao {
    public static void createWorkoutCollectionTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout_collection (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55))";
        runUpdate(q);
    }
}
