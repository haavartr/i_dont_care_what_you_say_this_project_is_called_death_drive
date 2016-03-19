package entities;

public class StrengthExercise extends WorkoutExercise {

    public StrengthExercise(Integer id, Integer workoutCollectionId, Integer exerciseId, Integer weight, Integer repetitions,
                            Integer sets, Integer form, Integer performance) {
        super(id, workoutCollectionId, exerciseId, weight, repetitions, sets, form, performance);
    }

    public StrengthExercise(){}
}
