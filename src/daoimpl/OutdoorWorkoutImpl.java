package daoimpl;

import dao.OutdoorWorkoutDao;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}
