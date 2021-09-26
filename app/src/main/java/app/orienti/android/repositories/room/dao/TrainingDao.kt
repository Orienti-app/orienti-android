package app.orienti.android.repositories.room.dao

import androidx.room.*
import app.orienti.android.entities.*

@Dao
interface TrainingDao {
    @Transaction
    fun insertTrainingData(trainings: List<TrainingData>){
        // Todo: Foreach entity insert

        insertTrainings(trainings.map { x -> x.training })

        trainings.forEach { trainingData ->
            insertTraining(trainingData.training)
            trainingData.runs.forEach { run ->
                insertRun(run)
            }
        }

        // ...
        // ...
        // ...
    }

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrainings(trainings: List<Training>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTraining(trainingData: Training)

    @Query("SELECT * FROM `Training`")
    fun getAll(): List<TrainingData>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRunner(runner: Runner)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRun(run: Run)


    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(track: Track)
}

