package entities;

public class WorkoutCollection {  // Superclass of Workout and Template
    private int wcId;  // Auto-incremented
    private String name;

    public WorkoutCollection(String name) {
        this.name = name;
    }

    public WorkoutCollection(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
