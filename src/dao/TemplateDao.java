package dao;

import entities.Template;

import java.util.List;

public interface TemplateDao {
    void createTemplateTable();

    void insert(Template template);

    Template selectById(int id);

    List<Template> selectAll();

    void delete(int id);

    void update(Template template);
}
