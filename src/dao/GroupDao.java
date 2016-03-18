package dao;

import entities.Group;
import java.util.List;

public interface GroupDao {
    void createGroupTable();

    void insert(Group group);

    Group selectById(int id);

    List<Group> selectAll();

    void delete(int id);

    void update(Group group);
}
