package app.orienti.android.ui.screens.trainer.run_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.orienti.android.R
import app.orienti.android.databinding.ActivityTrainerRunDetailBinding
import app.orienti.android.models.TrainingService
import app.orienti.android.ui.screens.common.run_detail.RunDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentActivity
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TrainerRunDetailActivity : ParentActivity<ActivityTrainerRunDetailBinding>(ActivityTrainerRunDetailBinding::inflate) {
    @Inject lateinit var trainingService: TrainingService

    val runId by lazy {
        intent.getSerializableExtra(RUN_ID_EXTRAS) as UUID
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val runDetailFragment: RunDetailFragment = supportFragmentManager.findFragmentById(R.id.run_detail_fragment) as RunDetailFragment

        trainingService.getRunByIdAsLiveData(runId).observe(this) { runData ->
            title = runData?.user?.name ?: ""
            runDetailFragment.setupWithRunData(runData, false)
        }
    }

    companion object {
        private const val RUN_ID_EXTRAS = "RUN_ID_EXTRAS"

        fun startActivity(context: Context, runId: UUID){
            context.startActivity(Intent(context, TrainerRunDetailActivity::class.java).apply {
                putExtra(RUN_ID_EXTRAS, runId)
            })
        }
    }
}