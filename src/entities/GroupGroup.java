package entities;

public class GroupGroup {  // Recursive many-to-many thing for Group
    private Integer containerGroupId;  // This one contains the one below
    private Integer containedGroupId;

    public GroupGroup(Integer containerGroupId, Integer containedGroupId) {
        this.containerGroupId = containerGroupId;
        this.containedGroupId = containedGroupId;
    }

    public GroupGroup(){}

    public Integer getContainerGroupId() {
        return containerGroupId;
    }

    public Integer getContainedGroupId() {
        return containedGroupId;
    }

    public void setContainerGroupId(Integer containerGroupId) {
        this.containerGroupId = containerGroupId;
    }

    public void setContainedGroupId(Integer containedGroupId) {
        this.containedGroupId = containedGroupId;
    }
}
