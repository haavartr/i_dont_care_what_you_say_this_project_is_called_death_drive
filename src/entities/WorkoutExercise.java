package entities;

// WorkoutExercise means a specific occurence of an exercise. The name is awful, we all know that.
public class WorkoutExercise {  // Superclass of CardioExercise and StrengthExercise
    private Integer id;  // Auto-incremented
    private Integer workoutCollectionId;
    private Integer exerciseId;
    private Integer form;
    private Integer performance;

    public WorkoutExercise(){}

    public WorkoutExercise(Integer id, Integer workoutCollectionId, Integer exerciseId, Integer form, Integer performance) {
        this.id = id;
        this.workoutCollectionId = workoutCollectionId;
        this.exerciseId = exerciseId;
        this.form = form;
        this.performance = performance;
    }

    public Integer getId() {
        return id;
    }

    public Integer getWorkoutCollectionId() {
        return workoutCollectionId;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public Integer getForm() {
        return form;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setWorkoutCollectionId(Integer workoutCollectionId) {
        this.workoutCollectionId = workoutCollectionId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }


    public void setForm(Integer form) {
        this.form = form;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

}
