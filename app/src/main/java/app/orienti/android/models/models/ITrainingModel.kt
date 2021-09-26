package app.orienti.android.models.models

import app.orienti.android.entities.db_entities.ControlPoint
import app.orienti.android.entities.db_entities.Track
import app.orienti.android.entities.db_entities.Training
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.entities.db_entities.joined.TrainingData
import app.orienti.android.repositories.room.AppDatabase
import sk.backbone.parent.models.IModel
import java.util.*

interface ITrainingModel : IModel<ModelsProvider> {
    val trainingDao get() = AppDatabase.getDatabase(context).trainingDao()

    fun getTrainingData(): List<TrainingData> = trainingDao.getTrainingData()

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

    fun createControlPoint(name: String, code: Int): ControlPoint {
        val controlPoint = ControlPoint(UUID.randomUUID(), code, name, Date())
        trainingDao.insert(controlPoint)
        return controlPoint
    }

    fun addRunToTraining(training: Training, runData: RunData){

    }

    fun addControlPointToTrack(){

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
}