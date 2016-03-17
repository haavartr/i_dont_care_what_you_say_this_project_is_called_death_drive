package entities;

public class ExerciseReplacements {  // Recursive many-to-many thing for Exercise
    private Integer exerciseId1;
    private Integer exerciseId2;

    public ExerciseReplacements(Integer exerciseId1, Integer exerciseId2) {
        this.exerciseId1 = exerciseId1;
        this.exerciseId2 = exerciseId2;
    }

    public Integer getExerciseId1() {
        return exerciseId1;
    }

    public Integer getExerciseId2() {
        return exerciseId2;
    }

    public void setExerciseId1(Integer exerciseId1) {
        this.exerciseId1 = exerciseId1;
    }

    public void setExerciseId2(Integer exerciseId2) {
        this.exerciseId2 = exerciseId2;
    }
}
