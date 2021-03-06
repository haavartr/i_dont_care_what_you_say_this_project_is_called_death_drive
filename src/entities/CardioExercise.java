package entities;

import dao.ExerciseDao;

public class CardioExercise extends WorkoutExercise {
    private Integer distance;
    private Integer time;

    public CardioExercise(Integer id, Integer workoutCollectionId, Integer exerciseId, Integer form, Integer performance, Integer distance, Integer time) {
        super(id, workoutCollectionId, exerciseId, form, performance);
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

    @Override
    public String toString() {
        Exercise exercise = ExerciseDao.selectById(this.getExerciseId());
        return this.getDistance() + " km " + exercise.getName() + " in " + this.getTime() + " seconds.";
    }
}
