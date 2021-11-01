package app.orienti.android.entities.db_entities.joined

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import app.orienti.android.entities.db_entities.ControlPoint
import app.orienti.android.entities.db_entities.Track
import app.orienti.android.entities.db_entities.TrackControlPoint

data class TrackData(
    @Embedded
    val track: Track,

    @Relation(
        parentColumn = "trackId",
        entityColumn = "controlPointId",
        associateBy = Junction(TrackControlPoint::class)
    )
    val controlPoints: List<ControlPoint>,
)
