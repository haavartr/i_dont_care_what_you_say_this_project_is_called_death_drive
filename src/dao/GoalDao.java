package dao;

import entities.Goal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static dao.RunQuery.*;

public class GoalDao {
    public static void createGoalsTable() {
        String q = "CREATE TABLE IF NOT EXISTS goal (" +
                "id INT NOT NULL auto_increment primary key," +
                "exercise INT NOT NULL," +
                "date datetime NOT NULL DEFAULT GETDATE()," +
                "load INT," +
                "repetitions INT," +
                "sets INT )";
        runUpdate(q);
    }

    public static void insert(Goal goal) {
        String exercise = "exercise " + goal.getExercise().toString();
        String date = "date " + goal.getDate().toString();
        String load = "load " + goal.getLoad().toString();
        String repetitions = "repetitions " + goal.getRepetitions().toString();
        String sets = "sets " + goal.getSets().toString();

        insertInto("goal", exercise, date, load, repetitions, sets);
    }

    public static Goal selectById(int id) {
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM goal WHERE id = " + id, statement);
        try {
            return new Goal(rs.getInt("id"), rs.getInt("exercise"), rs.getDate("date").toLocalDate(), rs.getInt("load"), rs.getInt("repetitions"), rs.getInt("sets"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static ArrayList<Goal> selectAll() {  // Returns an empty ArrayList if the table is empty
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM goal", statement);
        ArrayList<Goal> l = new ArrayList<>();
        try {
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new Goal(rs.getInt("id"), rs.getInt("exercise"), rs.getDate("date").toLocalDate(), rs.getInt("load"), rs.getInt("repetitions"), rs.getInt("sets")));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void delete(int id) {
        runUpdate("DELETE FROM TABLE goal WHERE id = " + id);
    }

    public static void update(Goal goal) {
        String exercise = goal.getExercise().toString();
        String date = goal.getDate().toString();
        String load = goal.getLoad().toString();
        String repetitions = goal.getRepetitions().toString();
        String sets = goal.getSets().toString();
        String id = goal.getId().toString();

        String q = String.format("UPDATE workout_exercise SET exercise = %s, date = %s, load = %s, repetitions = %s, sets = %s " +
                "WHERE id = %s", exercise, date, load, repetitions, sets, id);
        runUpdate(q);
    }
}
