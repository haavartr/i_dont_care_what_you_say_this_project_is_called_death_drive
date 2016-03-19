package entities;

public class Exercise {
    private Integer id;
    private String name;
    private String description;

    public Exercise(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Exercise(){}

    public Integer getId() {
        return id;
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

    @Override
    public String toString() {
        return this.getName();
    }
}
