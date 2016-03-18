package dao;

import entities.GroupExercise;
import entities.GroupGroup;

import java.util.List;

public interface GroupGroupDao {  // Many-to-many thing
    void createGroupGroupTable();

    void insert(GroupGroup groupGroup);

    List<GroupGroup> selectAll();

    void delete(int containerGroupId, int containedGroupId);
}
