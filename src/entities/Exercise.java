package entities;

public class Exercise {
    private int id;
    private String name;
    private String description;

    public Exercise() {

    }
    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    //public void setId(int id) { this.id = id; } //this should be illegal?

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
