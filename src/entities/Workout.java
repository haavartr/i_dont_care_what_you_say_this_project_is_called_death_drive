package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Workout extends WorkoutCollection{   // Superclass of IndoorWorkout and OutdoorWorkout
    private int workoutId;  // Auto-incremented
    private LocalDate date;
    private int length;
    private String note;

    public Workout(String name, int workoutId, LocalDate date, int length, String note) {
        super(name);
        this.workoutId = workoutId;
        this.date = date;
        this.length = length;
        this.note = note;
    }

    public Workout(){}

    public int getWorkoutId() {
        return workoutId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getLength() {
        return length;
    }

    public String getNote() {
        return note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
