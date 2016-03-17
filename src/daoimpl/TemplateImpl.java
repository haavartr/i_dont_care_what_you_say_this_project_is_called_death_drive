package daoimpl;

import dao.TemplateDao;
import entities.Template;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.runQuery;

public class TemplateImpl implements TemplateDao{
    @Override
    public void createTemplateTable() {
        String q ="CREATE TABLE IF NOT EXISTS template (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55)," +
                    "description varchar(255))";
        runQuery(q);
    }

    @Override
    public void insert(Template template) {

    }

    @Override
    public Template selectById(int id) {
        return null;
    }

    @Override
    public List<Template> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
