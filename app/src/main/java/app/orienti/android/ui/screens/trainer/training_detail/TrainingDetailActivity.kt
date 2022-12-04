package app.orienti.android.ui.screens.trainer.training_detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.databinding.ActivityTrainingDetailBinding
import app.orienti.android.entities.db_entities.Training
import app.orienti.android.entities.db_entities.joined.TrainingData
import app.orienti.android.models.TrainingService
import app.orienti.android.ui.screens.trainer.scan_run.ScanRunActivity
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.setSafeOnClickListener
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TrainingDetailActivity: ParentActivity<ActivityTrainingDetailBinding>(ActivityTrainingDetailBinding::inflate) {
    private val trainingId: UUID by lazy {
        intent.getSerializableExtra(TRAINING_ID_DATA_EXTRAS) as UUID
    }

    @Inject lateinit var trainingService: TrainingService

    private var adapter: RunsAdapter? = null
    private var training: Training? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = RunsAdapter(this)
        viewBinding.recycler.adapter = adapter
        viewBinding.recycler.layoutManager = LinearLayoutManager(this)

        viewBinding.add.setSafeOnClickListener {
            scanRunLauncher.launch(ScanRunActivity.createIntent(it.context, trainingId))
        }
    }

    override fun onResume() {
        super.onResume()

        trainingService.getTrainingDataForTraining(trainingId).apply {
            this@TrainingDetailActivity.training = training
            title = this.training.name
            adapter?.replaceDataSet(this.runs)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val scanRunLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            return@registerForActivityResult
        }
    }

    companion object {
        private const val TRAINING_ID_DATA_EXTRAS = "TRAINING_DATA_EXTRAS"

        fun startActivity(context: Context, trainingData: TrainingData){
            context.startActivity(Intent(context, TrainingDetailActivity::class.java).apply {
                putExtra(TRAINING_ID_DATA_EXTRAS, trainingData.training.id)
            })
        }
    }
}