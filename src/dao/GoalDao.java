package dao;

import entities.Goal;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static dao.RunQuery.*;

public class GoalDao {
    public static void createGoalsTable() {
        String q = "CREATE TABLE IF NOT EXISTS goal (" +
                "id INT NOT NULL auto_increment primary key," +
                "exercise INT NOT NULL," +
                "date datetime NOT NULL DEFAULT current_timestamp," +
                "weight INT," +
                "repetitions INT," +
                "sets INT )";
        runUpdate(q);
    }

    public static void insert(Goal goal) {
        String exercise = "exercise " + goal.getExercise().toString();
        String date = "date " + goal.getDate().toString();
        String weight = "weight " + goal.getWeight().toString();
        String repetitions = "repetitions " + goal.getRepetitions().toString();
        String sets = "sets " + goal.getSets().toString();

        insertInto("goal", exercise, date, weight, repetitions, sets);
    }

    public static Goal selectById(int id) {
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = "SELECT * FROM goal WHERE id = " + id;
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            return new Goal(rs.getInt("id"), rs.getInt("exercise"), rs.getDate("date").toLocalDate(), rs.getInt("weight"), rs.getInt("repetitions"), rs.getInt("sets"));
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
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        String q = "SELECT * FROM goal";
        ArrayList<Goal> l = new ArrayList<>();
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new Goal(rs.getInt("id"), rs.getInt("exercise"), rs.getDate("date").toLocalDate(), rs.getInt("weight"), rs.getInt("repetitions"), rs.getInt("sets")));
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
        String weight = goal.getWeight().toString();
        String repetitions = goal.getRepetitions().toString();
        String sets = goal.getSets().toString();
        String id = goal.getId().toString();

        String q = String.format("UPDATE workout_exercise SET exercise = %s, date = %s, weight = %s, repetitions = %s, sets = %s " +
                "WHERE id = %s", exercise, date, weight, repetitions, sets, id);
        runUpdate(q);
    }
}
