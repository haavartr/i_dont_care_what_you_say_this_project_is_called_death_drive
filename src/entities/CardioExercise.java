package entities;

public class CardioExercise extends WorkoutExercise {
    private Integer distance;
    private Integer time;

    public CardioExercise(Integer id, Integer workoutCollectionId, Integer exerciseId, Integer weight, Integer repetitions,
                          Integer sets, Integer form, Integer performance, Integer distance, Integer time) {
        super(id, workoutCollectionId, exerciseId, weight, repetitions, sets, form, performance);
        this.distance = distance;
        this.time = time;
    }

    public CardioExercise(){}

    public Integer getDistance() {
        return distance;
    }

    public Integer getTime() {
        return time;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
