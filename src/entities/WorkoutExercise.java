package entities;

/**
 * Created by hans on 16.03.16.
 */
public class WorkoutExercise {
    private int id;
    private String exercise;
    private int load;
    private int repetitions;
    private int sets;
    private Integer form;
    private Integer preformance;
    private int group;

    public WorkoutExercise () {

    }

    public WorkoutExercise(int id, String exercise, int load, int repetitions, int sets, Integer form, Integer preformance, int group) {
        this.id = id;
        this.exercise = exercise;
        this.load = load;
        this.repetitions = repetitions;
        this.sets = sets;
        this.form = form;
        this.preformance = preformance;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getExercise() {
        return exercise;
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

    public int getPreformance() {
        return preformance;
    }

    public int getGroup() {
        return group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
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

    public void setPreformance(int preformance) {
        this.preformance = preformance;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String toString() {
        return this.getSets() + "x" + this.getRepetitions() + " - " + this.getExercise() + " (" + this.getLoad() + " kg)";
    }
}
