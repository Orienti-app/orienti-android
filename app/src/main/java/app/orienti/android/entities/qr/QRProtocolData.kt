package app.orienti.android.entities.qr

import java.util.*

data class QRProtocolData(
    val runnerUUID: UUID,
    val runnerName: String,
    val controlPoints: List<QRControlPointData>
)

