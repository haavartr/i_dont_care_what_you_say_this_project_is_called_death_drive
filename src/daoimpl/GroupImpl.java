package daoimpl;

import dao.GroupDao;
import entities.Group;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

    }

    @Override
    public Group selectById(int id) {
        return null;
    }

    @Override
    public List<Group> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
