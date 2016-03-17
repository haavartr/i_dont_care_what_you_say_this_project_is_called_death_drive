package entities;

import java.time.LocalDate;

public class IndoorWorkout extends Workout {
    private Integer airQuality;
    private Integer spectators;

    public IndoorWorkout(Integer id, String name, LocalDate date, Integer length, String note, Integer airQuality, Integer spectators) {
        super(id, name, date, length, note);
        this.airQuality = airQuality;
        this.spectators = spectators;
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
