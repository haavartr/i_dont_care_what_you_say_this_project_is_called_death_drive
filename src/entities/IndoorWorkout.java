package entities;

import java.time.LocalDate;

public class IndoorWorkout extends Workout {
    private int airQuality;
    private int spectators;

    public IndoorWorkout(int workoutId, String name, LocalDate date, int length, String note, int airQuality, int spectators) {
        super(workoutId, name, date, length, note);
        this.airQuality = airQuality;
        this.spectators = spectators;
    }

    public IndoorWorkout(){}

    public int getAirQuality() {
        return airQuality;
    }

    public int getSpectators() {
        return spectators;
    }

    public void setAirQuality(int airQuality) {
        this.airQuality = airQuality;
    }

    public void setSpectators(int spectators) {
        this.spectators = spectators;
    }
}
