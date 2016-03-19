package dao;

import entities.Exercise;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static dao.RunQuery.insertInto;
import static dao.RunQuery.runQuery;
import static dao.RunQuery.runUpdate;

public class ExerciseDao {
    public static void createExerciseTable() {
        String q = ("CREATE TABLE IF NOT EXISTS exercise (" +
                "id int primary key unique auto_increment," +
                "name varchar(55)," +
                "description varchar(255))");
        runUpdate(q);
    }

    public static void insert(Exercise exercise) {
        String name = "name " + exercise.getName();
        String description = "description " + exercise.getDescription();
        insertInto("exercise", name, description);
    }

    public static Exercise selectById(int id) {
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM exercise WHERE id = " + id, statement);
        try {
            if (rs != null) {
                return new Exercise(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
            } else {
                return null;
            }
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

    public static ArrayList<Exercise> selectAll() {  // Returns an empty ArrayList if the table is empty
        Statement statement = null;
        ResultSet rs = runQuery("SELECT * FROM exercise", statement);
        ArrayList<Exercise> l = new ArrayList<>();
        try {
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new Exercise(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
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
        return l;
    }

    public static void delete(int id) {
        runUpdate("DELETE FROM TABLE exercise WHERE id = " + id);
    }

    public static void update(Exercise exercise) {
        String name = exercise.getName();
        String description = exercise.getDescription();
        String id = Integer.toString(exercise.getId());
        String q = String.format("UPDATE workout_exercise SET name = %s, description = %s WHERE id = %s", name, description, id);
        runUpdate(q);
    }
}
