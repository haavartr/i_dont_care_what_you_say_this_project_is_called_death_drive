package entities;

public class Grouping {
    private Integer id;  // Auto-incremented
    private String name;

    public Grouping(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Grouping(){}

    public Integer getId() {
        return id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return this.getName();
    }
}
