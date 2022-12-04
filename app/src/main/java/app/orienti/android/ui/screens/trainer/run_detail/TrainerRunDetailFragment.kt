package app.orienti.android.ui.screens.trainer.run_detail

import app.orienti.android.ui.screens.common.run_detail.RunDetailFragment
import android.os.Bundle
import android.view.View
import app.orienti.android.models.TrainingService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TrainerRunDetailFragment : RunDetailFragment() {
    @Inject lateinit var trainingService: TrainingService
}