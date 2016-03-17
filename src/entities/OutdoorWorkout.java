package entities;

import java.time.LocalDate;

public class OutdoorWorkout extends Workout{
    private float temperature;
    private String weather;

    public OutdoorWorkout(Integer id, String name, LocalDate date, Integer length, String note, float temperature, String weather) {
        super(id, name, date, length, note);
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
