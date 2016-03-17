package entities;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Magnus on 16.03.2016.
 */
public class Workout {
    private LocalDate date;
    private LocalTime time;
    private Integer hours;
    private Integer minutes;
    private Integer seconds;
    private String note;
    private Float temperature;
    private String weather;
    private String airQuality;
    private Integer spectators;

    public Workout(LocalDate date, LocalTime time, Integer hours, Integer minutes, Integer seconds, String note, Float temperature, String weather, String airQuality, Integer spectators) {
        this.date = date;
        this.time = time;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.note = note;
        this.temperature = temperature;
        this.weather = weather;
        this.airQuality = airQuality;
        this.spectators = spectators;
    }

    public Workout(LocalDate date, LocalTime time, Integer hours, Integer minutes, Integer seconds, String note) {
        this(date, time, hours, minutes, seconds, note, null, null, null, null);
    }

    public String getName() {
        return date.toString() + " - " + time.getHour() + ":" + time.getMinute();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDuration() {
        return hours + ":" + minutes + ":" + seconds;
    }

    public String getNote() {
        return note;
    }
}
