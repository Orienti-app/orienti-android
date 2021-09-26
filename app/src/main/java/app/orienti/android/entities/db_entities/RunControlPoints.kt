package app.orienti.android.entities.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class RunControlPoints(
    @PrimaryKey
    val id: UUID,
    val trainingRunId: UUID,
    val controlPointId: UUID,
)