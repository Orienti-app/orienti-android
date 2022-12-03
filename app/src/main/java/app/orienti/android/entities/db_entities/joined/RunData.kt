package app.orienti.android.entities.db_entities.joined

import androidx.room.Embedded
import androidx.room.Relation
import app.orienti.android.entities.db_entities.*

data class RunData(
    @Embedded
    val run: Run,

    @Relation(parentColumn = "runnerId", entityColumn = "runnerId")
    val runner: Runner?,

    @Relation(entity = Track::class, parentColumn = "trackId", entityColumn = "trackId")
    val trackData: TrackData,

    @Relation(entity = ScannedRunControlPoint::class, parentColumn = "runId", entityColumn = "runId")
    val scannedRunControlPoints: List<ScannedRunControlPoint> = listOf()
    ){
    val runControlPoints : List<ControlPoint> get() = trackData.controlPointsSortedByDate.map { trackControlPoint ->
        trackControlPoint.let { controlPoint ->
            controlPoint.checked = scannedRunControlPoints.any { runControlPoint ->
                runControlPoint.controlPointId == controlPoint.id
            }
            return@let controlPoint
        }
    }
}