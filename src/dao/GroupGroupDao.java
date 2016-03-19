package dao;

import entities.GroupGroup;
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

public class GroupGroupDao {  // Many-to-many
    public static void createGroupGroupTable() {
        String q = ("CREATE TABLE IF NOT EXISTS group_group (" +
                "container_group_id INT NOT NULL," +
                "contained_group_id INT NOT NULL)");
        runUpdate(q);
    }

    public static void insert(GroupGroup groupGroup) {
        String containerGroupId = "container_group_id " + groupGroup.getContainerGroupId().toString();
        String containedGroupId = "contained_group_id " + groupGroup.getContainedGroupId().toString();

        insertInto("group_group", containerGroupId, containedGroupId);
    }

    public static ArrayList<GroupGroup> selectAll() {  // Returns an empty ArrayList if the table is empty
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = "SELECT * FROM group_group";
        ArrayList<GroupGroup> l = new ArrayList<>();
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new GroupGroup(rs.getInt("container_group_id"), rs.getInt("contained_group_id")));
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

    public static void delete(int containerGroupId, int containedGroupId) {
        String q = String.format("DELETE FROM TABLE group_group WHERE container_group_id = %d AND contained_group_id = %d",
                containerGroupId, containedGroupId);
        runUpdate(q);
    }
}
