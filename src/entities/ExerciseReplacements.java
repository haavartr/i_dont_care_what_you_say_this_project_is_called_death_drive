package entities;

public class ExerciseReplacements {  // Recursive many-to-many thing for Exercise
    private int exerciseId1;
    private int exerciseId2;

    public ExerciseReplacements(int exerciseId1, int exerciseId2) {
        this.exerciseId1 = exerciseId1;
        this.exerciseId2 = exerciseId2;
    }

    public int getExerciseId1() {
        return exerciseId1;
    }

    public int getExerciseId2() {
        return exerciseId2;
    }

    public void setExerciseId1(int exerciseId1) {
        this.exerciseId1 = exerciseId1;
    }

    public void setExerciseId2(int exerciseId2) {
        this.exerciseId2 = exerciseId2;
    }
}
