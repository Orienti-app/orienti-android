package app.orienti.android.entities.qr

import java.sql.Timestamp
import java.util.*

enum class ControlType{
    Checkpoint,
    Start,
    Finish,
}

data class QRControlPointData(
    val uuid: UUID?,
    val code: String?,
    val type: ControlType,
    val timeZone: Timestamp?
    )