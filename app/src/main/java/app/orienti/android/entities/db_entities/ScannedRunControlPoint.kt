package app.orienti.android.entities.db_entities

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["runId", "controlPointId"])
data class ScannedRunControlPoint(
    val runId: UUID,
    val controlPointId: UUID,
    val scanned_at: Date = Date()
)