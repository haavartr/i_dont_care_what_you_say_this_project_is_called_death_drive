package daoimpl;

import dao.GoalsDao;
import entities.Goals;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.runQuery;

public class GoalsImpl implements GoalsDao {
    @Override
    public void createGoalsTable() {
        String q = "CREATE TABLE IF NOT EXISTS goals (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55)," +
                    "description varchar(255))";

        runQuery(q);
    }

    @Override
    public void insert(Goals goals) {

    }

    @Override
    public Goals selectById(int id) {
        return null;
    }

    @Override
    public List<Goals> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
