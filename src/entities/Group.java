package entities;

public class Group {
    int groupId;  // Auto-incremented

    public Group(int groupId) {
        this.groupId = groupId;
    }

    public Group(){}

    public int getGroupId() {
        return groupId;
    }
}
