package app.orienti.android.entities.db_entities.joined

import androidx.room.Embedded
import androidx.room.Relation
import app.orienti.android.entities.db_entities.Run
import app.orienti.android.entities.db_entities.Runner
import app.orienti.android.entities.db_entities.Track

data class RunData(
    @Embedded
    val run: Run,

    @Relation(parentColumn = "runnerId", entityColumn = "runnerId")
    val runner: Runner,

    @Relation(parentColumn = "trackId", entityColumn = "trackId")
    val track: Track
    )