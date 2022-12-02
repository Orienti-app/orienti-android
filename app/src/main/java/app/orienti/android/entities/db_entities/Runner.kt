package app.orienti.android.entities.db_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Runner(
    @PrimaryKey
    @ColumnInfo(name = "runnerId")
    val id: UUID,
    val name: String?
    )