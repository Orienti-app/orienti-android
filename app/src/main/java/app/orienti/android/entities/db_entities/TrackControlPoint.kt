package app.orienti.android.entities.db_entities

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["trackId", "controlPointId"])
data class TrackControlPoint(
    val trackId: UUID,
    val controlPointId: UUID,
)