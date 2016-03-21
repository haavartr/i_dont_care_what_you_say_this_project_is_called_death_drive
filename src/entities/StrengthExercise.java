package entities;

import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StrengthExercise extends WorkoutExercise {
    private Integer weight;
    private Integer repetitions;
    private Integer sets;

    public StrengthExercise(Integer id, Integer workoutCollectionId, Integer exerciseId, Integer weight, Integer repetitions,
                            Integer sets, Integer form, Integer performance) {
        super(id, workoutCollectionId, exerciseId, form, performance);
        this.weight = weight;
        this.repetitions = repetitions;
        this.sets = sets;
    }

    public StrengthExercise(){}

    public Integer getWeight() {
        return weight;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public Integer getSets() {
        return sets;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public String toString() {
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        Integer id = this.getId();
        String q = String.format("SELECT name FROM exercise WHERE exercise.id = %d ", id);
        String name = "Huge rod";
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(q);
            if (rs.next()) {
                name = rs.getString("name");
            } else {
                return null;
            }
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return name + ": " + this.getSets() + "x" + this.getRepetitions() + " - " + " (" + this.getWeight() + " kg)";
    }
}
