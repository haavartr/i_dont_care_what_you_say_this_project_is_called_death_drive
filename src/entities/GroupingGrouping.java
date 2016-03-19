package entities;

public class GroupingGrouping {  // Recursive many-to-many thing for Grouping
    private Integer containerGroupingId;  // This one contains the one below
    private Integer containedGroupingId;

    public GroupingGrouping(Integer containerGroupingId, Integer containedGroupingId) {
        this.containerGroupingId = containerGroupingId;
        this.containedGroupingId = containedGroupingId;
    }

    public GroupingGrouping(){}

    public Integer getContainerGroupingId() {
        return containerGroupingId;
    }

    public Integer getContainedGroupingId() {
        return containedGroupingId;
    }

    public void setContainerGroupingId(Integer containerGroupingId) {
        this.containerGroupingId = containerGroupingId;
    }

    public void setContainedGroupingId(Integer containedGroupingId) {
        this.containedGroupingId = containedGroupingId;
    }
}
