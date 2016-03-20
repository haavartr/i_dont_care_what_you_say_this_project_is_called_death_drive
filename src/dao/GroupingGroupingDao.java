package dao;

import entities.GroupingGrouping;
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

public class GroupingGroupingDao {  // Many-to-many
    public static void createGroupingGroupingTable() {
        String q = ("CREATE TABLE IF NOT EXISTS grouping_grouping (" +
                "container_grouping_id INT NOT NULL," +
                "contained_grouping_id INT NOT NULL)");
        runUpdate(q);
    }

    public static void insert(GroupingGrouping groupingGrouping) {
        String containerGroupingId = "container_grouping_id " + groupingGrouping.getContainerGroupingId().toString();
        String containedGroupingId = "contained_grouping_id " + groupingGrouping.getContainedGroupingId().toString();

        insertInto("grouping_grouping", containerGroupingId, containedGroupingId);
    }

    public static ArrayList<GroupingGrouping> selectAll() {  // Returns an empty ArrayList if the table is empty
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = "SELECT * FROM grouping_grouping";
        ArrayList<GroupingGrouping> l = new ArrayList<>();
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new GroupingGrouping(rs.getInt("container_grouping_id"), rs.getInt("contained_grouping_id")));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return l;
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
            return l;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void delete(int containerGroupingId, int containedGroupingId) {
        String q = String.format("DELETE FROM grouping_grouping WHERE container_grouping_id = %d AND contained_grouping_id = %d",
                containerGroupingId, containedGroupingId);
        runUpdate(q);
    }
}
