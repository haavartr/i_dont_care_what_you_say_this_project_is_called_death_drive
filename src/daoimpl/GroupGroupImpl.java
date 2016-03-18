package daoimpl;

import dao.GroupGroupDao;
import entities.GroupGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class GroupGroupImpl implements GroupGroupDao {
    @Override
    public void createGroupGroupTable() {
        String q = ("CREATE TABLE IF NOT EXISTS group_group (" +
                "container_group_id INT NOT NULL," +
                "contained_group_id INT NOT NULL)");
        runQuery(q);
    }

    @Override
    public void insert(GroupGroup groupGroup) {
        insertInto("group_group", groupGroup.getContainerGroupId().toString(), groupGroup.getContainedGroupId().toString());
    }

    @Override
    public List<GroupGroup> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM group_group");
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
        }
        return null;
    }

    @Override
    public void delete(int containerGroupId, int containedGroupId) {
        String q = String.format("DELETE FROM TABLE group_group WHERE container_group_id = %d AND contained_group_id = %d",
                containerGroupId, containedGroupId);
        runQuery(q);
    }
}
