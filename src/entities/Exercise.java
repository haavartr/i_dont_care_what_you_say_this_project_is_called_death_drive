package entities;

public class Exercise {
    private int exerciseId;
    private String name;
    private String description;

    public Exercise(int exerciseId, String name, String description) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.description = description;
    }

    public Exercise(){}

    public int getExerciseId() {
        return exerciseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
