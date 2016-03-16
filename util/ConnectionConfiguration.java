package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionConfiguration {
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/death_db", "root", "huge_penis");
        } catch (Exception e){
            // Exception probably came because there was no death_db database. Try to create death_db and connect again:
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "huge_penis");
                Statement statement = connection.createStatement();
                statement.execute("CREATE DATABASE IF NOT EXISTS death_db");
                return getConnection();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return connection;
    }
}
