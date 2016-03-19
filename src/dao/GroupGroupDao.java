package dao;

import entities.GroupGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static dao.RunQuery.insertInto;
import static dao.RunQuery.runQuery;
import static dao.RunQuery.runUpdate;

public class GroupGroupDao {
    public static void createGroupGroupTable() {
        String q = ("CREATE TABLE IF NOT EXISTS group_group (" +
                "container_group_id INT NOT NULL," +
                "contained_group_id INT NOT NULL)");
        runUpdate(q);
    }

    public static void insert(GroupGroup groupGroup) {
        insertInto("group_group", groupGroup.getContainerGroupId().toString(), groupGroup.getContainedGroupId().toString());
    }

    public static List<GroupGroup> selectAll() {
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM group_group", statement);
        List<GroupGroup> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new GroupGroup(rs.getInt("container_group_id"), rs.getInt("contained_group_id")));
                } catch (SQLException e) {
                    e.printStackTrace();
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
