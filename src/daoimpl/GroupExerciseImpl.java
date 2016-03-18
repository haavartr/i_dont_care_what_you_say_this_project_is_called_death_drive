package daoimpl;

import entities.GroupExercise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.*;

public class GroupExerciseImpl {
    @Override
    public void createGroupExerciseTable() {
        String q = "CREATE TABLE IF NOT EXISTS group_exercise (" +
                "groupID int," +
                "exerciseID int)";
        runQuery(q);
    }

    @Override
    public void insert(GroupExercise groupExercise) {
        insertInto("group_exercise", Integer.toString(groupExercise.getGroupId()), Integer.toString(groupExercise.getExerciseId()));
    }

    @Override
    public GroupExercise selectById(int id) {
        ResultSet rs = runQuery("SELECT * FROM group_exercise WHERE id = " + id);
        try {
            return new GroupExercise(rs.getInt("groupID"), rs.getInt("exerciseID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GroupExercise> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM group_exercise");
        List<GroupExercise> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new GroupExercise(rs.getInt("groupID"), rs.getInt("exerciseID")));
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
        runQuery("DELETE FROM TABLE group_exercise WHERE id = " + id);
    }
}
