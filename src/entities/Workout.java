package entities;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Workout extends WorkoutCollection {   // Superclass of IndoorWorkout and OutdoorWorkout
    private LocalDate date;
    private Integer length;
    private String note;

    public Workout(Integer id, String name, LocalDate date, Integer length, String note) {
        super(id, name);
        this.date = date;
        this.length = length;
        this.note = note;
    }

    public Workout(){}

    public LocalDate getDate() {
        return date;
    }

    public Integer getLength() {
        return length;
    }

    public String getNote() {
        return note;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
