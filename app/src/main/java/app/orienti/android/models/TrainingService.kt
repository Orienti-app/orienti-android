package app.orienti.android.models

import android.content.Context
import androidx.lifecycle.LiveData
import app.orienti.android.entities.db_entities.*
import app.orienti.android.entities.db_entities.joined.ControlPointData
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.entities.db_entities.joined.TrackData
import app.orienti.android.entities.db_entities.joined.TrainingData
import app.orienti.android.entities.qr.QrContainer
import app.orienti.android.repositories.room.AppDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingService @Inject constructor(@ApplicationContext val context: Context, private val database: AppDatabase) {
    @Inject lateinit var userService: UserService
    private val trainingDao get() = database.trainingDao()

    fun getTrainingData(): List<TrainingData> = trainingDao.getTrainingData()
    fun getTrainingDataForTraining(trainingId: UUID): TrainingData = trainingDao.getTrainingDataForTraining(trainingId)

    fun createTraining(name: String): Training {
        val training = Training(UUID.randomUUID(), name, Date(), Date())
        trainingDao.insert(training)
        return training
    }

    fun createTrack(name: String): Track {
        val track = Track(UUID.randomUUID(), name, Date())
        trainingDao.insert(track)
        return track
    }

    fun createControlPoint(name: String, code: String): ControlPoint {
        val controlPoint = ControlPoint(UUID.randomUUID(), code, name, Date())
        trainingDao.insert(controlPoint)
        return controlPoint
    }

    fun startNewRun(trackData: TrackData){
        trainingDao.deactivateAllRuns()
        trainingDao.insert(RunData(Run(UUID.randomUUID(), trackData.track.id, null, userService.runnerId, true, Date(), null), userService.user, trackData))
    }

    fun getActiveRunAsLiveData(): LiveData<RunData?> {
        return trainingDao.getActiveRunAsLiveData()
    }

    fun onControlPointScanned(controlPoint: ControlPoint){
        trainingDao.getActiveRunData()?.let { runData ->
            trainingDao.insert(ScannedRunControlPoint(runData.run.runId, controlPoint.id))
        }
    }

    fun removeTraining(){

    }

    fun removeTrack(){

    }

    fun removeRunFromTraining(){

    }

    fun removeControlPoint(){

    }

    fun getControlPoints() = trainingDao.getControlPoints()
    fun getTracks() = trainingDao.getTracks()
    fun getRunsDataForTraining(trainingId: UUID): List<RunData> = trainingDao.getRunsDataForTraining(trainingId)
    fun getTrackDetail(trackId: UUID): LiveData<TrackData> = trainingDao.getTrackData(trackId)
    fun getControlPointsWithDataAsLiveData(): LiveData<List<ControlPointData>> {
        return trainingDao.getControlPointsWithDataAsLiveData()
    }

    fun deleteTrackControlPoint(trackControlPoint: TrackControlPoint) {
        trainingDao.deleteTrackControlPoint(trackControlPoint)
    }

    fun createTrackControlPoint(trackControlPoint: TrackControlPoint) {
        trainingDao.insert(trackControlPoint)
    }

    fun getControlPointById(controlPointId: UUID): LiveData<ControlPoint?> {
        return trainingDao.getControlPointLiveDataById(controlPointId)
    }

    fun onRunScanned(runData: RunData, trainingId: UUID) {
        runData.run.trainingId = trainingId
        trainingDao.insert(runData)
    }

    fun getRunByIdAsLiveData(runId: UUID): LiveData<RunData?> {
        return trainingDao.getRunDataByIdAsLiveData(runId)
    }
}