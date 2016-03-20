package entities;

public class StrengthExercise extends WorkoutExercise {
    private Integer weight;
    private Integer repetitions;
    private Integer sets;

    public StrengthExercise(Integer id, Integer workoutCollectionId, Integer exerciseId, Integer weight, Integer repetitions,
                            Integer sets, Integer form, Integer performance) {
        super(id, workoutCollectionId, exerciseId, form, performance);
        this.weight = weight;
        this.repetitions = repetitions;
        this.sets = sets;
    }

    public StrengthExercise(){}

    public Integer getWeight() {
        return weight;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public Integer getSets() {
        return sets;
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

    public String toString() {
        return this.getSets() + "x" + this.getRepetitions() + " - " + " (" + this.getWeight() + " kg)";
    }
}
