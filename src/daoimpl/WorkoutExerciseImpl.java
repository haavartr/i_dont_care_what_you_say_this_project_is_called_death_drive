package daoimpl;

import static daoimpl.RunQuery.runUpdate;

public class WorkoutExerciseImpl {
    public static void createWorkoutExerciseTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout_exercise (" +
                    "id int primary key unique auto_increment," +
                    "workout_collection_id int NOT NULL," +
                    "exercise_id varchar(255)," +
                    "load int, " +
                    "repetitions int, " +
                    "sets int, " +
                    "form int, " +
                    "performance int);";
        System.out.println(q);
        runUpdate(q);
    }
}
