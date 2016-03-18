package daoimpl;

import dao.WorkoutExerciseDao;
import entities.WorkoutExercise;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.runQuery;

public class WorkoutExerciseImpl implements WorkoutExerciseDao {
    @Override
    public void createWorkoutExerciseTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout_exercise (" +
                    "id int primary key unique auto_increment," +
                    "workout int NOT NULL," +
                    "exercise varchar(255)," +
                    "load int ," +
                    "repetitions int," +
                    "sets int," +
                    "form int," +
                    "performance int," +
                    "group int )";
        runQuery(q);
    }
}
