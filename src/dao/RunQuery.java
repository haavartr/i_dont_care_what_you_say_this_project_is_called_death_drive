package dao;

import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RunQuery {
    //used for INSERT, UBPDATE, CREATE and DELETE
    public static void runUpdate(String query) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
                statement.executeUpdate(query);
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

    public static ResultSet runQuery (String query, Statement statement) {
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
        }
        return rs;
    }

    public static void insertInto (String name, String... args) {
        String q = "INSERT INTO " + name + " VALUES (";
        for (String arg : args){
            q += "'" + arg + "'" + ",";
        }
        q = q.substring(0, q.length()-1);
        q += ")";
        System.out.println(q);
        runUpdate(q);
    }


}
