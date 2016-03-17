package entities;

public class Group {
    Integer id;  // Auto-incremented

    public Group(Integer id) {
        this.id = id;
    }

    public Group(){}

    public Integer getId() {
        return id;
    }
}
