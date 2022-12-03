package app.orienti.android.entities.qr

import app.orienti.android.entities.db_entities.joined.ControlPointData
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.entities.db_entities.joined.TrackData

class QrContainer (
    val type: QrType,
    val track: TrackData? = null,
    val controlPoint: ControlPointData? = null,
    val run: RunData? = null
)
