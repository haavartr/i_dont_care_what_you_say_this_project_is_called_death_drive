package entities;

public class Template extends WorkoutCollection {
    private int tId;  // Auto-incremented
    private String name;

    public Template(int tId, String name) {
        this.tId = tId;
        this.name = name;
    }

    public Template(){}

    public int gettId() {
        return tId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
