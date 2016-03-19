package dao;

import entities.Grouping;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static dao.RunQuery.insertInto;
import static dao.RunQuery.runQuery;
import static dao.RunQuery.runUpdate;

public class GroupingDao {
    public static void createGroupingTable() {
        String q = "CREATE TABLE IF NOT EXISTS grouping (" +
                    "id int primary key unique auto_increment)";
        runUpdate(q);
    }

    public static void insert(Grouping grouping) {
        insertInto("grouping");
    }

    public static Grouping selectById(int id) {
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = "SELECT * FROM grouping WHERE id = " + id;
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs != null) {
                return new Grouping(rs.getInt("id"));
            } else {
                return null;
            }
        } catch (SQLException e) {
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
        return null;
    }

    public static ArrayList<Grouping> selectAll() {  // Returns an empty ArrayList if the table is empty
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = "SELECT * FROM grouping";
        ArrayList<Grouping> l = new ArrayList<>();
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new Grouping(rs.getInt("id")));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return l;
        } catch (SQLException e) {
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
        return null;
    }

    public static void delete(int id) {
        runUpdate("DELETE FROM TABLE grouping WHERE id = " + id);
    }
}
