package daoimpl;

import dao.ExerciseDao;
import entities.Exercise;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class ExerciseImpl implements ExerciseDao{
    @Override
    public void createExerciseTable() {
        String q = ("CREATE TABLE IF NOT EXISTS exercise (" +
                "id int primary key unique auto_increment," +
                "name varchar(55)," +
                "description varchar(255))");
        runQuery(q);
    }

    @Override
    public void insert(Exercise exercise) {
        insertInto("exercise", exercise.getName(), exercise.getDescription());
    }

    @Override
    public Exercise selectById(int id) {

        return null;
    }

    @Override
    public List<Exercise> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
