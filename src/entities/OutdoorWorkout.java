package entities;

import java.time.LocalDate;

public class OutdoorWorkout extends Workout{
    private float temperature;
    private String weather;

    public OutdoorWorkout(int workoutId, String name, LocalDate date, int length, String note, float temperature, String weather) {
        super(workoutId, name, date, length, note);
        this.temperature = temperature;
        this.weather = weather;
    }

    public OutdoorWorkout(){}

    public float getTemperature() {
        return temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
