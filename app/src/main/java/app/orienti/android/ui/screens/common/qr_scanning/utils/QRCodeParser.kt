package app.orienti.android.ui.screens.common.qr_scanning.utils

import app.orienti.android.entities.qr.ControlType
import app.orienti.android.entities.qr.QRControlPointData
import app.orienti.android.entities.qr.QRProtocolData
import app.orienti.android.entities.qr.QRTrackData
import sk.backbone.parent.utils.isSameDay
import java.sql.Timestamp
import java.util.*

class QRCodeParser {
    companion object {
        private const val protocolName = "QROrienti:1.0.0"
        private const val trackKey = "Route"
        private const val controlPointKey = "ControlPoint"
        private const val startControlPointKey = "Start"
        private const val finishControlPointKey = "Finish"
        private const val summaryProtocolKey = "Protocol"

        private fun isProtocolSupported(rawData: String?): Boolean = rawData?.startsWith(protocolName) == true

        fun parseTrackDefinition(rawData: String?): QRTrackData?
        {
            val rawDataRows = rawData?.split("\n") ?: listOf()
            val dataType = rawDataRows.getOrNull(1)
            val controlPointsRows = rawDataRows.drop(2)

            return if(isProtocolSupported(rawData) && dataType == trackKey){
                val qRTrackData = QRTrackData(controlPointsRows)
                qRTrackData
            } else null
        }


        fun parseControlPoint(rawData: String?): QRControlPointData?{
            val rawDataRows = rawData?.split("\n") ?: listOf()
            val dataType = rawDataRows.getOrNull(1)
            val controlPointValues = rawDataRows.drop(2)

            if (isProtocolSupported(rawData))
            {
                return when (dataType)
                {
                    controlPointKey -> {
                        val uuid = UUID.fromString(controlPointValues[0])
                        val qRControlPointData = QRControlPointData(uuid, controlPointValues[1], ControlType.Checkpoint, null)
                        qRControlPointData
                    }
                    startControlPointKey -> {
                        val qRControlPointData = QRControlPointData(null, null, ControlType.Start, null)
                        qRControlPointData
                    }
                    finishControlPointKey -> {
                        val qRControlPointData = QRControlPointData(null, null, ControlType.Finish, null)
                        qRControlPointData
                    }
                    else -> return null
                }
            }
            else return null
        }

        fun parseProtocol(rawData: String?): QRProtocolData?{
            val rawDataRows = rawData?.split("\n") ?: listOf()
            val dataType = rawDataRows.getOrNull(1)
            val protocolDataRows = rawDataRows.drop(3)

            return if(isProtocolSupported(rawData) && dataType == summaryProtocolKey){
                val runnerInfo = rawDataRows[2].split(";")
                val runnerUuid = UUID.fromString(runnerInfo[0])
                val runnerName = runnerInfo[1]

                val controlPoints = mutableListOf<QRControlPointData>()

                for (row in protocolDataRows){
                    val rowData = row.split(";")
                    val time = Timestamp.valueOf(rowData[0])
                    val type: ControlType? = when (rowData[1]){
                        "S" -> ControlType.Start
                        "C" -> ControlType.Checkpoint
                        "F" -> ControlType.Finish
                        else -> null
                    }
                    val code = rowData[2]

                    type?.let { QRControlPointData(null, code, it, time) }?.apply {
                        controlPoints.add(this)
                    }
                }
                val qRProtocolData = QRProtocolData(runnerUuid, runnerName, controlPoints)
                qRProtocolData
            } else null
        }
    }
}

