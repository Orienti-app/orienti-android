package app.orienti.android.entities.db_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Run(
    @PrimaryKey
    @ColumnInfo(name = "runId")
    val runId: UUID,
    val trackId: UUID,
    var trainingId: UUID?,
    val userId: UUID,
    var started_at: Date?,
    var finished_at: Date?,
    )