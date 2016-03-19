package dao;

import entities.Group;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static dao.RunQuery.insertInto;
import static dao.RunQuery.runQuery;
import static dao.RunQuery.runUpdate;

public class GroupDao {
    public static void createGroupTable() {
        String q = "CREATE TABLE IF NOT EXISTS group (" +
                    "id int primary key unique auto_increment)";
        runUpdate(q);
    }

    public static void insert(Group group) {
        insertInto("group");
    }

    public static Group selectById(int id) {
        Statement statement = null;
        String q = "SELECT * FROM group WHERE id = " + id;
        try {
            TreeMap<ResultSet, Statement> result = runQuery(q, statement);
            ResultSet rs = result.firstEntry().getKey();
            statement = result.firstEntry().getValue();
            if (rs != null) {
                return new Group(rs.getInt("id"));
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

    public static ArrayList<Group> selectAll() {  // Returns an empty ArrayList if the table is empty
        Statement statement = null;
        String q = "SELECT * FROM group";
        ArrayList<Group> l = new ArrayList<>();
        try {
            TreeMap<ResultSet, Statement> result = runQuery(q, statement);
            ResultSet rs = result.firstEntry().getKey();
            statement = result.firstEntry().getValue();
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new Group(rs.getInt("id")));
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
        runUpdate("DELETE FROM TABLE group WHERE id = " + id);
    }
}
