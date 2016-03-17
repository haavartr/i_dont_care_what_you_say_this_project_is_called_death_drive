package entities;

public class CardioExercise extends WorkoutExercise {
    private int distance;
    private int time;

    public CardioExercise(int weId, String workout, int load, int repetitions, int sets, int form, int performance,
                          int group, int distance, int time) {
        super(weId, workout, load, repetitions, sets, form, performance, group);
        this.distance = distance;
        this.time = time;
    }

    public CardioExercise(){}

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
