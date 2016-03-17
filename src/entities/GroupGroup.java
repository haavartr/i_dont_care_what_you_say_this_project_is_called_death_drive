package entities;

public class GroupGroup {  // Recursive many-to-many thing for Group
    private int containerGroupId;  // This one contains the other one
    private int containedGroupId;

    public GroupGroup(int groupId1, int groupId2) {
        this.groupId1 = groupId1;
        this.groupId2 = groupId2;
    }

    public GroupGroup(){}

    public int getGroupId1() {
        return groupId1;
    }

    public int getGroupId2() {
        return groupId2;
    }

    public void setGroupId1(int groupId1) {
        this.groupId1 = groupId1;
    }

    public void setGroupId2(int groupId2) {
        this.groupId2 = groupId2;
    }
}
