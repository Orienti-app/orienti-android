package app.orienti.android.entities.db_entities.joined

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import app.orienti.android.entities.db_entities.*

data class ControlPointData(
    @Embedded
    val controlPoint: ControlPoint,

    @Relation(
        parentColumn = "controlPointId",
        entityColumn = "trackId",
        associateBy = Junction(TrackControlPoint::class)
    )
    val tracks: List<Track>?
    )