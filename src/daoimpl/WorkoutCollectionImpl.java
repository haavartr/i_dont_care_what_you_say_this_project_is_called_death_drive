package daoimpl;

import dao.WorkoutCollectionDao;

import static daoimpl.RunQuery.runUpdate;

public class WorkoutCollectionImpl implements WorkoutCollectionDao{
    @Override
    public void createWorkoutCollectionTable() {
        String q = "CREATE TABLE IF NOT EXISTS workout_collection (" +
                    "id int primary key unique auto_increment," +
                    "name varchar(55))";
        runUpdate(q);
    }
}
