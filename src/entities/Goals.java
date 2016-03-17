package entities;

import java.time.LocalDate;

public class Goals {
    private int goalId;  // Auto increment
    private int exercise;
    private LocalDate date;
    private int load;
    private int repetitions;
    private int sets;

    public Goals(int goalId, int exercise, LocalDate date, int load, int repetitions, int sets) {
        this.goalId = goalId;
        this.exercise = exercise;
        this.date = date;
        this.load = load;
        this.repetitions = repetitions;
        this.sets = sets;
    }

    public Goals(){}

    public int getGoalId() {
        return goalId;
    }

    public int getExercise() {
        return exercise;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getLoad() {
        return load;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setExercise(int exercise) {
        this.exercise = exercise;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getSets() {
        return sets;
    }
}
