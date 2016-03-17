package entities;

public class GroupExercise {  // Many-to-many thing for Group and Exercise
    private int groupId;
    private int exerciseId;

    public GroupExercise(int groupId, int exerciseId) {
        this.groupId = groupId;
        this.exerciseId = exerciseId;
    }

    public GroupExercise(){}

    public int getGroupId() {
        return groupId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
