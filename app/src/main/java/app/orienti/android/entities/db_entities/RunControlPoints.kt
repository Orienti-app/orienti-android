package app.orienti.android.entities.db_entities

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["trainingRunId", "controlPointId"])
data class RunControlPoints(
    val trainingRunId: UUID,
    val controlPointId: UUID,
)