package dao;

import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static dao.RunQuery.runUpdate;

public class WorkoutCollectionDao {  // Parent
    public static void createWorkoutCollectionTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout_collection (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55))";
        runUpdate(q);
    }

    public static Integer nextId() {
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        Integer id = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT COUNT(*) as last_id from workout_collection");
            if (rs.next()) {
                id = rs.getInt("last_id");
            }
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }
}
