package daoimpl;

import dao.GoalDao;
import entities.Goal;

import java.util.List;

import static daoimpl.RunQuery.runQuery;

public class GoalImpl implements GoalDao {
    @Override
    public void createGoalsTable() {
        String q = "CREATE TABLE IF NOT EXISTS goals (" +
                "id INT NOT NULL auto_increment primary key," +
                "exercise INT NOT NULL," +
                "date datetime NOT NULL DEFAULT GETDATE()," +
                "load INT" +
                "repetitions INT" +
                "sets INT" +
                ")";
        runQuery(q);
    }

   Integer load, Integer repetitions, Integer sets


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
