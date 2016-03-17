package daoimpl;

import dao.WorkoutExerciseDao;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
