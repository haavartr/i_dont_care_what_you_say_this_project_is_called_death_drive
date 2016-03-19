package daoimpl;

import dao.TemplateDao;
import entities.Template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;
import static daoimpl.RunQuery.runUpdate;

public class TemplateImpl implements TemplateDao{
    @Override
    public void createTemplateTable() {
        String q ="CREATE TABLE IF NOT EXISTS template (" +
                "id INT NOT NULL," +
                "PRIMARY KEY(id)," +
                "FOREIGN KEY(id) REFERENCES workout_collection(id)";
        runUpdate(q);
    }

    @Override
    public void insert(Template template) {
        Statement statement = null;
        String name = template.getName();

        insertInto("workout_collection", name);
        try {
            String id = Integer.toString(runQuery("SELECT LAST_INSERT_ID()", statement).getInt(0));
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

    @Override
    public Template selectById(int id) {  // Returns null if the id doesn't exist
        Statement statement = null;
        String q = String.format("SELECT * FROM template JOIN workout_collection ON template.id = %d " +
                "AND workout_collection.id = %d", id, id);
        ResultSet rs = runQuery(q, statement);
        try {
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

    @Override
    public List<Template> selectAll() {
        return null;
    }

    @Override  // Delete the entry in the highest parent and let the deletion cascade
    public void delete(int id) {
        runUpdate("DELETE FROM TABLE workout_collection WHERE id = " + id);
    }

    @Override
    public void update(Template template) {
        String id = template.getId().toString();
        String name = template.getName();
        String q = String.format("UPDATE workout_collection SET name = %s WHERE id = %s", name, id);
        runUpdate(q);
    }
}
