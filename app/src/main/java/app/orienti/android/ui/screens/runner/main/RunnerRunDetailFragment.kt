package app.orienti.android.ui.screens.runner.main

import app.orienti.android.ui.screens.common.run_detail.RunDetailFragment
import android.os.Bundle
import android.view.View
import app.orienti.android.models.TrainingService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RunnerRunDetailFragment : RunDetailFragment() {
    @Inject lateinit var trainingService: TrainingService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trainingService.getActiveRunAsLiveData().observe(this.viewLifecycleOwner){ runData ->
            setRun(runData, true)
        }
    }
}