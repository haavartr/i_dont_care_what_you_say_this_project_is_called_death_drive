package daoimpl;

import dao.GroupDao;
import entities.Exercise;
import entities.Group;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class GroupImpl implements GroupDao {
    @Override
    public void createGroupTable() {
        String q = "CREATE TABLE IF NOT EXISTS group (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55)," +
                    "description varchar(255))";
        runQuery(q);
    }

    @Override
    public void insert(Group group) {
        insertInto("group");
    }

    @Override
    public Group selectById(int id) {
        ResultSet rs = runQuery("SELECT * FROM group WHERE id = " + id);
        try {
            return new Group(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Group> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM group");
        List<Group> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new Group(rs.getInt("id")));
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
    public void delete(int id) {
        runQuery("DELETE FROM TABLE group WHERE id = " + id);
    }
}
