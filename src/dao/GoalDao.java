package dao;

import entities.Goal;

import java.util.List;

public interface GoalDao {
    void createGoalsTable();

    void insert(Goal goal);

    Goal selectById(int id);

    List<Goal> selectAll();

    void delete(int id);

    void update();
}
