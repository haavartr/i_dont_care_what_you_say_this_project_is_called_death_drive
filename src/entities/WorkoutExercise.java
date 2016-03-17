package entities;

// WorkoutExercise means a specific occurence of an exercise. The name is awful, we all know that.
public class WorkoutExercise {  // Superclass of CardioExercise and StrengthExercise
    private int weId;  // Auto-incremented
    private String workout;
    private int load;
    private int repetitions;
    private int sets;
    private Integer form;
    private Integer performance;
    private int group;

    public WorkoutExercise(){}

    public WorkoutExercise(int weId, String workout, int load, int repetitions, int sets, int form, int performance, int group) {
        this.weId = weId;
        this.workout = workout;
        this.load = load;
        this.repetitions = repetitions;
        this.sets = sets;
        this.form = form;
        this.performance = performance;
        this.group = group;
    }

    public int getWeId() {
        return weId;
    }

    public String getWorkout() {
        return workout;
    }

    public int getLoad() {
        return load;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public int getSets() {
        return sets;
    }

    public int getForm() {
        return form;
    }

    public int getPerformance() {
        return performance;
    }

    public int getGroup() {
        return group;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
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

    public void setForm(int form) {
        this.form = form;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String toString() {
        return this.getSets() + "x" + this.getRepetitions() + " - " + this.getWorkout() + " (" + this.getLoad() + " kg)";
    }
}
