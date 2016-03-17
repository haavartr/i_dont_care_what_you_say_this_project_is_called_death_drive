package daoimpl;

import dao.GoalDao;
import entities.Goal;

import java.util.List;

import static daoimpl.RunQuery.runQuery;

public class GoalImpl implements GoalDao {
    @Override
    public void createGoalsTable() {
        String q = "CREATE TABLE IF NOT EXISTS goals (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55)," +
                    "description varchar(255))";

        runQuery(q);
    }

    @Override
    public void insert(Goal goal) {

    }

    @Override
    public Goal selectById(int id) {
        return null;
    }

    @Override
    public List<Goal> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
