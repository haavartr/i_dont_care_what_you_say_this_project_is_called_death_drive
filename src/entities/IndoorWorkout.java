package entities;

import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class IndoorWorkout extends Workout {
    private Integer airQuality;
    private Integer spectators;

    public IndoorWorkout(Integer id, String name, LocalDate date, Integer length, String note, Integer airQuality, Integer spectators) {
        super(id, name, date, length, note);
        this.airQuality = airQuality;
        this.spectators = spectators;
    }

    public static Integer nextId() {
        Connection connection = null;
        ResultSet rs;
        Statement statement = null;
        Integer id = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT COUNT(*) as last_id from workout_collection");
            if (rs.next()) {
                id = rs.getInt("last_id");
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
        return id;
    }

    public IndoorWorkout(){}

    public Integer getAirQuality() {
        return airQuality;
    }

    public Integer getSpectators() {
        return spectators;
    }

    public void setAirQuality(Integer airQuality) {
        this.airQuality = airQuality;
    }

    public void setSpectators(Integer spectators) {
        this.spectators = spectators;
    }
}
