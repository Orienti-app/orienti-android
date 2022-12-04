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
    User::class,
    Track::class,
    TrackControlPoint::class,
    ScannedRunControlPoint::class,
    ControlPoint::class,
                     ], version = 10, exportSchema = false)
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
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                instance = result
            }

            return result
        }
    }
}