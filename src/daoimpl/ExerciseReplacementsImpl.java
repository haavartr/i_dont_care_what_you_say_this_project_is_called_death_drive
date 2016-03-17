package daoimpl;

import dao.ExerciseReplacementsDao;
import entities.ExerciseReplacements;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExerciseReplacementsImpl implements ExerciseReplacementsDao {
    @Override
    public void createExerciseReplacementsTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS exercise_replacements (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55)," +
                    "description varchar(255))");
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

    @Override
    public void insert(ExerciseReplacements exerciseReplacements) {

    }

    @Override
    public ExerciseReplacements selectById(int id) {
        return null;
    }

    @Override
    public List<ExerciseReplacements> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
