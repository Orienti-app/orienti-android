package app.orienti.android.entities.db_entities.joined

import app.orienti.android.entities.db_entities.Training

data class TrainingData(
    val training: Training,
    val runs: List<RunData>,
)