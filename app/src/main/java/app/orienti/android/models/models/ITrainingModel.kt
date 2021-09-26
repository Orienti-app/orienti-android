package app.orienti.android.models.models

import app.orienti.android.entities.*
import app.orienti.android.repositories.room.AppDatabase
import sk.backbone.parent.models.IModel
import java.util.*

interface ITrainingModel : IModel<ModelsProvider> {
    val trainingDao get() = AppDatabase.getDatabase(context).trainingDao()

    fun createTraining(name: String): List<TrainingData> {
        val trainingId = UUID.randomUUID()
        val runnerId = UUID.randomUUID()

//        val trainingData = TrainingData(
//            Training(UUID.randomUUID(), name, Date()),
//            listOf(
//                Run(
//                    Run(UUID.randomUUID(), trainingId, runnerId, Date(), Date()),
//                    Runner(runnerId, "RunnerWiiii")
//                )
//            )
//        )

        trainingDao.insert(Training(UUID.randomUUID(), name, Date()))

        val insertedData = trainingDao.getAll()

        return insertedData
    }

    fun createTrack(name: String){

    }

    fun createControlPoint(name: String){

    }

    fun addRunToTraining(){

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
}