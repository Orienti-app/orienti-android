package app.orienti.android.entities

class QRCodeParser {
    val protocolName = "QROrienti"
    val protocolVersion = "1.0.0"

    fun parse(rawData: String): QRRouteData?
    {
        val splitData = rawData.split("\n") as MutableList<String>
        val protocolInfo = splitData.removeAt(0).split(":")

        if(protocolInfo[0] != protocolName || protocolInfo[1] != protocolVersion)
        {
            return null
        }

        if(splitData.removeAt(0) == "Route")
        {
            val qRRouteData = QRRouteData()
            for (checkpoint in splitData)
            {
                qRRouteData.AddCheckpoint(checkpoint)
            }

            return  qRRouteData
        }

        return null
    }

}

class QRRouteData{
    private var checkpoints = mutableListOf<String>()
    
    fun AddCheckpoint(newCheckpoint: String)
    {
        checkpoints.add(newCheckpoint)
    }
}