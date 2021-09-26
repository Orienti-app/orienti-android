package app.orienti.android.entities.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TrackControlPoints(
    @PrimaryKey
    val id: UUID,
    val trackId: UUID,
    val controlPointId: UUID,
)