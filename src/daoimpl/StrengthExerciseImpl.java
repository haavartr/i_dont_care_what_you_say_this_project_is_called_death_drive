package daoimpl;

import dao.StrengthExerciseDao;
import entities.StrengthExercise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static daoimpl.RunQuery.insertInto;
import static daoimpl.RunQuery.runQuery;

public class StrengthExerciseImpl implements StrengthExerciseDao{
    @Override
    public void createStrengthExerciseTable() {
        String q = "CREATE TABLE IF NOT EXISTS strength_exercise (" +
                "id INT NOT NULL UNIQUE," +
                "PRIMARY KEY(id)," +
                "FOREIGN KEY(id) REFERENCES workout_exercise(id) ON DELETE CASCADE))";
        runQuery(q);
    }

    @Override
    public void insert(StrengthExercise strengthExercise) {
        String workoutCollectionId = strengthExercise.getWorkoutCollectionId().toString();
        String exerciseId = strengthExercise.getExerciseId().toString();
        String load = strengthExercise.getLoad().toString();
        String repetitions = strengthExercise.getRepetitions().toString();
        String sets = strengthExercise.getSets().toString();
        String form = strengthExercise.getForm().toString();
        String performance = strengthExercise.getPerformance().toString();

        insertInto("workout_exercise", workoutCollectionId, exerciseId, load, repetitions, sets, form, performance);
        try {
            String id = Integer.toString(runQuery("SELECT LAST_INSERT_ID()").getInt(0));
            insertInto("strength_exercise", id);
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StrengthExercise selectById(int id) {
        String q = String.format("SELECT * FROM strength_exercise JOIN workout_exercise ON strength_exercise.id = %d " +
                "AND workout_exercise.id = %d", id, id);
        ResultSet rs = runQuery(q);
        try {
            return new StrengthExercise(rs.getInt("id"),
                    rs.getInt("workout_collection_id"),
                    rs.getInt("exercise_id"),
                    rs.getInt("load"),
                    rs.getInt("repetitions"),
                    rs.getInt("sets"),
                    rs.getInt("form"),
                    rs.getInt("performance"));
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StrengthExercise> selectAll() {
        ResultSet rs = runQuery("SELECT * FROM strength_exercise JOIN workout_exercise");
        List<StrengthExercise> l = new ArrayList<>();
        try {
            while (rs.next()) {
                try {
                    l.add(new StrengthExercise(rs.getInt("id"),
                            rs.getInt("workout_collection_id"),
                            rs.getInt("exercise_id"),
                            rs.getInt("load"),
                            rs.getInt("repetitions"),
                            rs.getInt("sets"),
                            rs.getInt("form"),
                            rs.getInt("performance")));
                } catch (SQLException|NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return l;
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        runQuery("DELETE FROM TABLE workout_exercise WHERE id = " + id);
    }

    @Override
    public void update(StrengthExercise strengthExercise) {
        String workoutCollectionId = strengthExercise.getWorkoutCollectionId().toString();
        String exerciseId = strengthExercise.getExerciseId().toString();
        String load = strengthExercise.getLoad().toString();
        String repetitions = strengthExercise.getRepetitions().toString();
        String sets = strengthExercise.getSets().toString();
        String form = strengthExercise.getForm().toString();
        String performance = strengthExercise.getPerformance().toString();
        String id = strengthExercise.getId().toString();

        String q = String.format("UPDATE workout_exercise SET workout_collection_id = %s, exercise_id = %s, load = %s," +
                        "repetitions = %s, sets = %s, form = %s, performance = %s WHERE id = %s", workoutCollectionId,
                exerciseId, load, repetitions, sets, form, performance, id);
        runQuery(q);
    }
}