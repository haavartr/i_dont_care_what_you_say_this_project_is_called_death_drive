package daoimpl;

import dao.CardioExerciseDao;
import entities.CardioExercise;
import util.ConnectionConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class CardioExerciseImpl implements CardioExerciseDao {
    @Override
    public void createCardioExerciseTable() {
        String q = "CREATE TABLE IF NOT EXISTS cardio_exercise (" +
                "WEID int primary key unique," +
                "distance int," +
                "time int" +
                "FOREIGN KEY (WeId) REFERENCES workout_exercise(WeId)))";
        runQuery(q);
    }

    @Override
    public void insert(CardioExercise cardioExercise) {
        insertInto("cardio_exercise", Integer.toString(cardioExercise.getWeId), Integer.toString(cardioExercise.getDistance), Integer.toString(cardioExercise.getTime));
    }

    @Override
    public CardioExercise selectById(int id) {
        ResultSet rs = runQuery("SELECT * FROM cardio_exercise WHERE WEID = " + id);
        try {
            return new CardioExercise(rs.getInt("WEID"), rs.getInt("distance"), rs.getInt("time"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<CardioExercise> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM cardio_exercise");
        List<CardioExercise> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new CardioExercise(rs.getInt("WEID"), rs.getInt("distance"), rs.getInt("time")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        runQuery("DELETE FROM TABLE cardio_exercis WHERE WEID = " + id);
    }

    @Override
    public void update() {

    }
}
