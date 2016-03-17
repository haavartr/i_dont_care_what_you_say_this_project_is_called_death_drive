package daoimpl;

import dao.CardioExerciseDao;
import entities.CardioExercise;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static daoimpl.RunQuery.runQuery;

public class CardioExerciseImpl implements CardioExerciseDao {
    @Override
    public void createCardioExerciseTable() {
        String q = "CREATE TABLE IF NOT EXISTS cardio_exercise (" +
                "id int primary key unique auto_increment," +
                "name varchar(55)," +
                "description varchar(255))";
        runQuery(q);
    }

    @Override
    public void insert(CardioExercise cardioExercise) {

    }

    @Override
    public CardioExercise selectById(int id) {
        return null;
    }

    @Override
    public List<CardioExercise> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update() {

    }

}
