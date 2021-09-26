package app.orienti.android.entities.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Run(
    @PrimaryKey
    val id: UUID,
    val trackId: UUID,
    val trainingId: UUID,
    val runnerId: UUID,
    val started_at: Date,
    val finished_at: Date,
    )