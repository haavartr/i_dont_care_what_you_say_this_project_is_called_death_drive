package dao;

import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

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

    public static Map<ResultSet, Statement> runQuery (String q, Statement statement) {
        Connection connection = null;
        ResultSet rs;
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
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
        return null;
    }

    public static void insertInto (String name, String... args) {
        String colums = "";
        for (String c : args) {
            colums += c.split(" ", 2)[0];
            colums += ",";
        }
        colums = colums.substring(0, colums.length()-1);
        String q = "INSERT INTO " + name +" (" + colums + ") VALUES (";
        for (String arg : args){
            q += "'" + arg.split(" ", 2)[1] + "'" + ",";
        }
        q = q.substring(0, q.length()-1);
        q += ")";
        runUpdate(q);
    }

}
