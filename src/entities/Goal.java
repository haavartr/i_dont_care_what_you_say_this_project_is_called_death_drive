package entities;

import dao.ExerciseDao;

import java.time.LocalDate;

public class Goal {
    private Integer id;  // Auto increment
    private Integer exercise;
    private LocalDate date;
    private Integer weight;
    private Integer repetitions;
    private Integer sets;

    public Goal(Integer id, Integer exercise, LocalDate date, Integer weight, Integer repetitions, Integer sets) {
        this.id = id;
        this.exercise = exercise;
        this.date = date;
        this.weight = weight;
        this.repetitions = repetitions;
        this.sets = sets;
    }

    public Goal(){}

    public Integer getId() {
        return id;
    }

    public Integer getExercise() {
        return exercise;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setExercise(Integer exercise) {
        this.exercise = exercise;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Integer getSets() {
        return sets;
    }
}
