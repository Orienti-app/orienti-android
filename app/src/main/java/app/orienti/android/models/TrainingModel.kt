package app.orienti.android.models

import android.content.Context
import app.orienti.android.entities.db_entities.ControlPoint
import app.orienti.android.entities.db_entities.Track
import app.orienti.android.entities.db_entities.TrackControlPoint
import app.orienti.android.entities.db_entities.Training
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.entities.db_entities.joined.TrackData
import app.orienti.android.entities.db_entities.joined.TrainingData
import app.orienti.android.repositories.room.AppDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingModel @Inject constructor(@ApplicationContext val context: Context, private val database: AppDatabase) {
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

    fun addRunData(runData: RunData){
        trainingDao.addRunDataToTraining(runData)
    }

    fun addControlPointToTrack(track: Track, controlPoint: ControlPoint){
        trainingDao.insert(TrackControlPoint(track.id, controlPoint.id))
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
    fun getTrackDetail(trackId: UUID): TrackData = trainingDao.getTrackData(trackId)
}