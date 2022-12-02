package app.orienti.android.ui.screens.trainer.control_point_selection_activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.databinding.ActivityControlPointsSelectionBinding
import app.orienti.android.models.TrainingService
import app.orienti.android.ui.screens.trainer.main.control_points.ControlPointDataAdapter
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentActivity
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class ControlPointsSelectionActivity : ParentActivity<ActivityControlPointsSelectionBinding>(ActivityControlPointsSelectionBinding::inflate) {
    @Inject lateinit var trainingService: TrainingService

    @Inject lateinit var adapter: ControlPointDataAdapter

    private val trackId : UUID by lazy {
        intent.getSerializableExtra(TRACK_ID_EXTRAS) as UUID
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        trainingService.getControlPointsWithDataAsLiveData().observe(this){ controlPointData ->
            adapter.checkForTrackId = trackId

            viewBinding.recycler.adapter = adapter
            viewBinding.recycler.layoutManager = LinearLayoutManager(this)

            adapter.replaceDataSet(controlPointData)
        }
    }

    companion object {
        private const val TRACK_ID_EXTRAS = "TRACK_ID_EXTRAS"

        fun startActivity(context: Context, trackId: UUID) {
            context.startActivity(Intent(context, ControlPointsSelectionActivity::class.java).apply {
                putExtra(TRACK_ID_EXTRAS, trackId)
            })
        }
    }
}