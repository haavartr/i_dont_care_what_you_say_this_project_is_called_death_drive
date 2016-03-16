package dao;

import entities.Person;

import java.util.List;

public interface PersonDao {
    void createPersonTable();

    void insert(Person person);

    Person selectById(int id);

    List<Person> selectAll();

    void delete(int id);

    void update();
}
