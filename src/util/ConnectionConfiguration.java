package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.*;

public class ConnectionConfiguration {
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exercise_diary", "root", "password");
        } catch (Exception e){
            System.out.println();            // Exception probably came because there was no death_db database. Try to create death_db and connect again:
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "root", "password");
                Statement statement = connection.createStatement();
                statement.execute("CREATE DATABASE IF NOT EXISTS exercise_diary");
                return getConnection();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        return connection;
    }
}
//8qJTQTOD&-pO
