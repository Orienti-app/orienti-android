package app.orienti.android.ui.screens.common.qr_scanning.utils

class QRCodeParser {
    companion object {
        private const val protocolName = "QROrienti:1.0.0"
        private const val trackKey = "Route"

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
    }
}

