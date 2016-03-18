package dao;

import entities.GroupExercise;
import entities.GroupGroup;

import java.util.List;

public interface GroupGroupDao {
    void createGroupGroupTable();

    void insert(GroupGroup groupGroup);

    GroupGroup selectById(int id);

    List<GroupGroup> selectAll();

    void delete(int id);

    void update(GroupGroup groupGroup);
}
