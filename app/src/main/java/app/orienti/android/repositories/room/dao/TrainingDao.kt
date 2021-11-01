package app.orienti.android.repositories.room.dao

import androidx.room.*
import app.orienti.android.entities.db_entities.*
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.entities.db_entities.joined.TrainingData
import java.util.*

@Dao
interface TrainingDao {

    //////////////////////
    // SELECTING OBJECTS//
    //////////////////////

    @Query("SELECT * FROM `Training`")
    fun getTrainings(): List<Training>

    @Query("SELECT * FROM `Track`")
    fun getTracks(): List<Track>

    @Query("SELECT * FROM `ControlPoint`")
    fun getControlPoints(): List<ControlPoint>

    @Query("SELECT * FROM `Training` WHERE id == :trainingId")
    fun getTrainingById(trainingId: UUID): Training

    @Transaction
    @Query("SELECT * FROM `Run` WHERE trainingId == :trainingId")
    fun getRunsDataForTraining(trainingId: UUID): List<RunData>

    @Query("SELECT * FROM `Track` WHERE id == :trackId")
    fun getTrackData(trackId: UUID): Track


    ///////////
    // JOINS //
    ///////////

    @Transaction
    fun getTrainingDataForTraining(trainingId: UUID): TrainingData {
        val training = getTrainingById(trainingId)
        return TrainingData(training, getRunsDataForTraining(training.id))
    }

    @Transaction
    fun getTrainingData(): List<TrainingData>{
        val trainingData = getTrainings().map { training ->
            TrainingData(training, getRunsDataForTraining(training.id))
        }

        return trainingData
    }


    // SINGLE OBJECT INSERTS

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trainingData: Training)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(runner: Runner)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(run: Run)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(track: Track)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(controlPoint: ControlPoint)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(runControlPoints: RunControlPoints)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trackControlPoints: TrackControlPoints)

    @Transaction
    fun addRunDataToTraining(runData: RunData) {
        insert(runData.track)
        insert(runData.runner)
        insert(runData.run)
    }
}

