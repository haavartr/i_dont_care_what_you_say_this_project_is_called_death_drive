package entities;

public class WorkoutCollection {  // Superclass of Workout and Template
    private Integer id;  // Auto-incremented
    private String name;

    public WorkoutCollection(Integer wcId, String name) {
        this.id = id;
        this.name = name;
    }

    public WorkoutCollection(){}

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
