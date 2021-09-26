package app.orienti.android.repositories.room.dao

import androidx.room.*
import app.orienti.android.entities.Training
import app.orienti.android.entities.TrainingData

@Dao
interface TrainingDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trainings: List<Training>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trainingData: Training)

    @Query("SELECT * FROM `Training`")
    fun getAll(): List<TrainingData>

}