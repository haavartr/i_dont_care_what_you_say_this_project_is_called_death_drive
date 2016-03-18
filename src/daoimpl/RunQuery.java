package daoimpl;

import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RunQuery {
    public static ResultSet runQuery(String query) {
        Connection connection = null;
        Statement statement = null;
        String arr[] = query.split(" ", 2);
        arr[0] = arr[0].toUpperCase();

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            if (arr[0].equals("INSERT") || arr[0].equals("UPDATE") || arr[0].equals("DELETE") || arr[0].equals("CREATE")){
                statement.executeUpdate(query);
            }
            else {
                return statement.executeQuery(query);
            }
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
        return null;
    }

    public static void insertInto (String name, String... args) {
        String q = "INSERT INTO " + name + "VALUES (";
        for (String arg : args){
            q += " " + arg + ",";
        }
        q = q.substring(0, q.length()-1);
        q += ")";
        runQuery(q);
    }


}
