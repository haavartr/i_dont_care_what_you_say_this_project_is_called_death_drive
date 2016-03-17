package daoimpl;

import dao.WorkoutExerciseDao;
import entities.WorkoutExercise;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by hans on 16.03.16.
 */
public class WorkoutExerciseImpl implements WorkoutExerciseDao {
    @Override
    public void createWorkoutExerciseTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS WorKout_Exercise (" +
                    "weid int primary key unique auto_increment," +
                    "workout int NOT NULL," +
                    "exercise varchar(255)," +
                    "load int ," +
                    "repetitions int," +
                    "sets int," +
                    "form int," +
                    "preformance int," +
                    "group int )");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void insert(WorkoutExercise exercise) {

    }

    @Override
    public WorkoutExercise selectById(int id) {
        return null;
    }

    @Override
    public List<WorkoutExercise> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
