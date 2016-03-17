package dao;

import entities.Goals;

import java.util.List;

public interface GoalsDao {
    void createGoalsTable();

    void insert(Goals goals);

    Goals selectById(int id);

    List<Goals> selectAll();

    void delete(int id);

    void update();
}
