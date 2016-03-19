package dao;

import static dao.RunQuery.runUpdate;

public class WorkoutExerciseDao {  // Parent
    public static void createWorkoutExerciseTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout_exercise (" +
                    "id int primary key unique auto_increment," +
                    "workout_collection_id int NOT NULL," +
                    "exercise_id varchar(255)," +
                    "weight int, " +
                    "repetitions int, " +
                    "sets int, " +
                    "form int, " +
                    "performance int);";
        runUpdate(q);
    }
}
