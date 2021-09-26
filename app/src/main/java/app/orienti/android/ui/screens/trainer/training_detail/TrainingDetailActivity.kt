package app.orienti.android.ui.screens.trainer.training_detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import app.orienti.android.databinding.ActivityTrainingDetailBinding
import app.orienti.android.entities.db_entities.joined.TrainingData
import app.orienti.android.ui.screens.trainer.scan_run.ScanRunActivity
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.jsonToObject
import sk.backbone.parent.utils.setSafeOnClickListener
import sk.backbone.parent.utils.toJsonString

class TrainingDetailActivity: ParentActivity<ActivityTrainingDetailBinding>(ActivityTrainingDetailBinding::inflate) {
    private val trainingData by lazy {
        intent.getStringExtra(TRAINING_DATA_EXTRAS)!!.jsonToObject<TrainingData>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = trainingData.training.name

        viewBinding.add.setSafeOnClickListener {
            scanRunLauncher.launch(ScanRunActivity.createIntent(it.context))
        }
    }

    private val scanRunLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            return@registerForActivityResult
        }
    }

    companion object {
        private const val TRAINING_DATA_EXTRAS = "TRAINING_DATA_EXTRAS"

        fun startActivity(context: Context, trainingData: TrainingData){
            context.startActivity(Intent(context, TrainingDetailActivity::class.java).apply {
                putExtra(TRAINING_DATA_EXTRAS, trainingData.toJsonString())
            })
        }
    }
}