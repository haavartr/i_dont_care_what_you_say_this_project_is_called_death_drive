package entities;

public class GroupExercise {  // Many-to-many thing for Group and Exercise
    private Integer groupId;
    private Integer exerciseId;

    public GroupExercise(Integer groupId, Integer exerciseId) {
        this.groupId = groupId;
        this.exerciseId = exerciseId;
    }

    public GroupExercise(){}

    public Integer getGroupId() {
        return groupId;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }
}
