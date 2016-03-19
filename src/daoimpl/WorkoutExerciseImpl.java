package daoimpl;

import dao.WorkoutExerciseDao;
import entities.WorkoutExercise;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.runQuery;

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
        runQuery(q);
    }
}
