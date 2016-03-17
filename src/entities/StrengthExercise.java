package entities;

public class StrengthExercise extends WorkoutExercise {

    public StrengthExercise(Integer id, Integer workoutCollectionId, Integer exerciseId, Integer load, Integer repetitions,
                            Integer sets, Integer form, Integer performance) {
        super(id, workoutCollectionId, exerciseId, load, repetitions, sets, form, performance);
    }

    public StrengthExercise(){}
}
