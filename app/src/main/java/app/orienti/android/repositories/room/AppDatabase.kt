package app.orienti.android.repositories.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.orienti.android.entities.db_entities.*
import app.orienti.android.repositories.room.dao.TrainingDao

@Database(entities = [
    Training::class,
    Run::class,
    Runner::class,
    Track::class,
    TrackControlPoints::class,
    RunControlPoints::class,
    ControlPoint::class
                     ], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trainingDao(): TrainingDao

    companion object{
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            var result = instance

            if(result == null){
                result = Room.databaseBuilder(context, AppDatabase::class.java, "database")
                    .fallbackToDestructiveMigrationFrom(1, 2, 3)
                    .allowMainThreadQueries()
                    .build()

                instance = result
            }

            return result
        }
    }
}