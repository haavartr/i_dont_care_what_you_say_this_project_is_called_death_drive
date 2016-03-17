package daoimpl;

import dao.ExerciseReplacementsDao;
import entities.ExerciseReplacements;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class ExerciseReplacementsImpl implements ExerciseReplacementsDao {
    @Override
    public void createExerciseReplacementsTable() {
        String q = "CREATE TABLE IF NOT EXISTS exercise_replacements (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55)," +
                    "description varchar(255))";
        runQuery(q);
    }

    @Override
    public void insert(ExerciseReplacements exerciseReplacements) {
        insertInto("exercise_replacements", );
    }

    @Override
    public ExerciseReplacements selectById(int id) {
        return null;
    }

    @Override
    public List<ExerciseReplacements> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }
}
