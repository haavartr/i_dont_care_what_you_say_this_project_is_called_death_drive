package dao;

import entities.Template;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static dao.RunQuery.insertInto;
import static dao.RunQuery.runQuery;
import static dao.RunQuery.runUpdate;

public class TemplateDao {
    public static void createTemplateTable() {
        String q ="CREATE TABLE IF NOT EXISTS template (" +
                "id INT NOT NULL," +
                "PRIMARY KEY(id)," +
                "FOREIGN KEY(id) REFERENCES workout_collection(id)";
        runUpdate(q);
    }

    public static void insert(Template template) {
        Statement statement = null;
        String name = "name " + template.getName();

        insertInto("workout_collection", name);
        try {
            TreeMap<ResultSet, Statement> result = runQuery("SELECT LAST_INSERT_ID()", statement);
            ResultSet rs = result.firstEntry().getKey();
            statement = result.firstEntry().getValue();
            String id = Integer.toString(rs.getInt(0));
            insertInto("template", id);
        } catch (SQLException|NullPointerException e) {
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
    }

    public static Template selectById(int id) {  // Returns null if the id doesn't exist
        Statement statement = null;
        String q = String.format("SELECT * FROM template JOIN workout_collection ON template.id = %d " +
                "AND workout_collection.id = %d", id, id);
        try {
            TreeMap<ResultSet, Statement> result = runQuery(q, statement);
            ResultSet rs = result.firstEntry().getKey();
            statement = result.firstEntry().getValue();
            if (rs != null) {
                return new Template(rs.getInt("id"), rs.getString("name"));
            } else {
                return null;
            }
        } catch (SQLException|NullPointerException e) {
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

    public static ArrayList<Template> selectAll() {  // Returns an empty ArrayList if the table is empty
        Statement statement = null;
        String q = "SELECT * FROM template JOIN workout_collection";
        ArrayList<Template> l = new ArrayList<>();
        try {
            TreeMap<ResultSet, Statement> result = runQuery(q, statement);
            ResultSet rs = result.firstEntry().getKey();
            statement = result.firstEntry().getValue();
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    try {
                        l.add(new Template(rs.getInt("id"), rs.getString("name")));
                    } catch (SQLException|NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
            return l;
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        finally {
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

    public static void delete(int id) {  // Delete the entry in the highest parent and let the deletion cascade
        runUpdate("DELETE FROM TABLE workout_collection WHERE id = " + id);
    }

    public static void update(Template template) {
        String id = template.getId().toString();
        String name = template.getName();
        String q = String.format("UPDATE workout_collection SET name = %s WHERE id = %s", name, id);
        runUpdate(q);
    }
}
