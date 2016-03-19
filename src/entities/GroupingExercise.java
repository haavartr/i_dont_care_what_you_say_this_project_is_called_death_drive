package entities;

public class GroupingExercise {  // Many-to-many thing for Grouping and Exercise
    private Integer groupingId;
    private Integer exerciseId;

    public GroupingExercise(Integer groupingId, Integer exerciseId) {
        this.groupingId = groupingId;
        this.exerciseId = exerciseId;
    }

    public GroupingExercise(){}

    public Integer getGroupingId() {
        return groupingId;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setGroupingId(Integer groupingId) {
        this.groupingId = groupingId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }
}
