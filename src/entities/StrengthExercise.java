package entities;

public class StrengthExercise extends WorkoutExercise {

    public StrengthExercise(int weId, String workout, int load, int repetitions, int sets, int form, int performance,
                          int group) {
        super(weId, workout, load, repetitions, sets, form, performance, group);
    }

    public StrengthExercise(){}
}
