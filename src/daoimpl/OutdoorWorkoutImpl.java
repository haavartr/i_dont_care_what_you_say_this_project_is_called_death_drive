package daoimpl;

import dao.OutdoorWorkoutDao;
import entities.OutdoorWorkout;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OutdoorWorkoutImpl implements OutdoorWorkoutDao{
    @Override
    public void createOutdoorWorkoutTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS outdoor_workout (" +
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
    public void insert(OutdoorWorkout outdoorWorkout) {

    }

    @Override
    public OutdoorWorkout selectById(int id) {
        return null;
    }

    @Override
    public List<OutdoorWorkout> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
