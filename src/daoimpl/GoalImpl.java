package daoimpl;

import dao.GoalDao;
import entities.CardioExercise;
import entities.ExerciseReplacements;
import entities.Goal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.*;

public class GoalImpl implements GoalDao {
    @Override
    public void createGoalsTable() {
        String q = "CREATE TABLE IF NOT EXISTS goal (" +
                "id INT NOT NULL auto_increment primary key," +
                "exercise INT NOT NULL," +
                "date datetime NOT NULL DEFAULT GETDATE()," +
                "load INT," +
                "repetitions INT," +
                "sets INT" +
                ")";
        runQuery(q);
    }

    @Override
    public void insert(Goal goal) {
        insertInto("goal", Integer.toString(goal.getId()), Integer.toString(goal.getExercise()), goal.getDate().toString(),
                Integer.toString(goal.getLoad()), Integer.toString(goal.getRepetitions()), Integer.toString(goal.getSets()));
    }

    @Override
    public Goal selectById(int id) {
        ResultSet rs = runQuery("SELECT * FROM goal WHERE id = " + id);
        try {
            return new Goal(rs.getInt("id"), rs.getInt("exercise"), rs.getDate("date").toLocalDate(), rs.getInt("load"), rs.getInt("repetitions"), rs.getInt("sets"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goal> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM goal");
        List<Goal> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new Goal(rs.getInt("id"), rs.getInt("exercise"), rs.getDate("date").toLocalDate(), rs.getInt("load"), rs.getInt("repetitions"), rs.getInt("sets")));
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
        runQuery("DELETE FROM TABLE goal WHERE id = " + id);
    }

    @Override
    public void update(Goal goal) {
        String exercise = goal.getExercise().toString();
        String date = goal.getDate().toString();
        String load = goal.getLoad().toString();
        String repetitions = goal.getRepetitions().toString();
        String sets = goal.getSets().toString();
        String id = goal.getId().toString();

        String q = String.format("UPDATE workout_exercise SET exercise = %s, date = %s, load = %s, repetitions = %s, sets = %s " +
                "WHERE id = %s", exercise, date, load, repetitions, sets, id);
        runQuery(q);
    }
}
