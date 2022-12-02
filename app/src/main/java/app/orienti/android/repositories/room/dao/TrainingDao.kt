package app.orienti.android.repositories.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import app.orienti.android.entities.db_entities.*
import app.orienti.android.entities.db_entities.joined.ControlPointData
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.entities.db_entities.joined.TrackData
import app.orienti.android.entities.db_entities.joined.TrainingData
import java.util.*

@Dao
interface TrainingDao {
    @Query("SELECT * FROM `Training`")
    fun getTrainings(): List<Training>

    @Query("SELECT * FROM `Track`")
    fun getTracks(): List<Track>

    @Query("SELECT * FROM `ControlPoint`")
    fun getControlPoints(): List<ControlPoint>

    @Query("SELECT * FROM `Training` WHERE trainingId == :trainingId")
    fun getTrainingById(trainingId: UUID): Training

    @Transaction
    @Query("SELECT * FROM `Run` WHERE trainingId == :trainingId")
    fun getRunsDataForTraining(trainingId: UUID): List<RunData>

    @Query("SELECT * FROM `Track` WHERE trackId == :trackId")
    fun getTrack(trackId: UUID): Track


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

    @Transaction
    @Query("SELECT * FROM `ControlPoint`")
    fun getControlPointsWithDataAsLiveData(): LiveData<List<ControlPointData>>


    @Transaction
    @Query("SELECT * FROM 'Track' WHERE trackId == :trackId LIMIT 1")
    fun getTrackData(trackId: UUID): LiveData<TrackData>

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
    fun insert(trackControlPoint: TrackControlPoint)


    @Transaction
    fun insert(runData: RunData) {
        insert(runData.trackData.track)
        runData.runner?.let { insert(it) }
        insert(runData.run)
    }

    @Delete
    fun deleteTrackControlPoint(trackControlPoint: TrackControlPoint)

    @Query("""UPDATE `Run` SET is_active = 0""")
    fun deactivateAllRuns()

    @Transaction
    @Query("""SELECT * FROM `Run` LIMIT 1""")
    fun getActiveRun(): LiveData<RunData?>
}

