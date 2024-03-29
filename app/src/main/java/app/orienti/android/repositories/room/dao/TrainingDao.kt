package app.orienti.android.repositories.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import app.orienti.android.entities.db_entities.*
import app.orienti.android.entities.db_entities.joined.ControlPointData
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.entities.db_entities.joined.TrackData
import app.orienti.android.entities.db_entities.joined.TrainingData
import app.orienti.android.entities.qr.QrContainer
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
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(run: Run)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(track: Track)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(track: List<ControlPoint>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(controlPoint: ControlPoint)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(scannedRunControlPoint: ScannedRunControlPoint)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trackControlPoint: TrackControlPoint)

    @Insert
    @Transaction
    fun insert(runData: RunData): RunData {
        insert(runData.trackData.track)
        runData.user?.let { insert(it) }
        insert(runData.run)

        runData.trackData.trackControlPoints.forEach {
            insert(it)
        }

        insert(runData.trackData.controlPoints)

        return runData
    }

    @Delete
    fun deleteTrackControlPoint(trackControlPoint: TrackControlPoint)

    @Query("""SELECT * FROM `ControlPoint` WHERE controlPointId = :controlPointId LIMIT 1""")
    fun getControlPointLiveDataById(controlPointId: UUID): LiveData<ControlPoint?>

    @Transaction
    @Query("""SELECT * FROM `Run` WHERE runId = :runId LIMIT 1""")
    fun getRunDataByIdAsLiveData(runId: UUID?) : LiveData<RunData?>

    @Transaction
    @Query("""SELECT * FROM `Run` WHERE runId = :runId LIMIT 1""")
    fun getRunDataByIdData(runId: UUID?) : RunData?
}

