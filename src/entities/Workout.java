package entities;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
    private ArrayList<WorkoutExercise> workoutExercises = new ArrayList<>();

    public Workout(LocalDate date, LocalTime time, Integer hours, Integer minutes, Integer seconds, String note, Float temperature, String weather, String airQuality, Integer spectators, ArrayList<WorkoutExercise> workoutExercises) {
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
        this.workoutExercises = workoutExercises;
    }

    public Workout(LocalDate date, LocalTime time, Integer hours, Integer minutes, Integer seconds, String note) {
        this(date, time, hours, minutes, seconds, note, null, null, null, null, null);
    }

    public ArrayList<WorkoutExercise> getWorkoutExercises() {
        return this.workoutExercises;
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

    @Override
    public String toString() {
        return this.getName();
    }
}
