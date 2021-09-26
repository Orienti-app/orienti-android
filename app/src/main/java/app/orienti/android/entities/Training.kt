package app.orienti.android.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*

data class TrainingData(
    @Embedded
    val training: Training,

    @Relation(parentColumn =  "id", entityColumn = "trainingId")
    val runs: List<Run>,
    )

data class RunData(
    @Embedded
    val run: Run,

    @Relation(parentColumn =  "id", entityColumn = "runnerId")
    val runner: Runner
    )

@Entity
data class Training (
    @PrimaryKey
    val id: UUID,
    val name: String,
    val createdAt: Date
    )

@Entity
data class Run(
    @PrimaryKey
    val id: UUID,
    val trainingId: UUID,
    val runnerId: UUID,
    // Todo: val trackId: UUID,
    val started_at: Date,
    val finished_at: Date,
    )

@Entity
data class Runner(
    @PrimaryKey
    val id: UUID,
    val name: String
    )

data class RunControlPoints(val id: UUID, val trainingRunId: UUID, val controlPointId: UUID)

data class ControlPoint(val id: UUID, val code: Int, val description: String)

data class TrackControlPoints(val id: UUID, val trackId: UUID, val controlPointId: UUID)

@Entity
data class Track(
    @PrimaryKey
    val id: UUID,
    val name: String,
    val description: String
    )