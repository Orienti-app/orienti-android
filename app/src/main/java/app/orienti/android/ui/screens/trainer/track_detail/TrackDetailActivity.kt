package app.orienti.android.ui.screens.trainer.track_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import app.orienti.android.databinding.ActivityTrackDetailBinding
import app.orienti.android.entities.db_entities.Run
import app.orienti.android.entities.db_entities.Runner
import app.orienti.android.entities.db_entities.Track
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.entities.db_entities.joined.TrackData
import app.orienti.android.models.TrainingModel
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ActivityTransitions
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.setSafeOnClickListener
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TrackDetailActivity: ParentActivity<ActivityTrackDetailBinding>(ActivityTrackDetailBinding::inflate) {
    @Inject lateinit var trainingModel: TrainingModel

    var trackData: TrackData? = null

    override fun getActivityTransitions(): ActivityTransitions = ActivityTransitions.BOTTOM_TOP

    private val trackId: UUID by lazy {
        intent.getSerializableExtra(TRACK_ID_EXTRAS) as UUID
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        trackData = trainingModel.getTrackDetail(trackId)

        trackData?.let {
            title = it.track.name
        }

        viewBinding.add.setSafeOnClickListener {
            // Todo: Add control point
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

    override fun onResume() {
        super.onResume()

        trackData?.let { trackData ->
            trainingModel.getControlPoints().forEach {
                trainingModel.addControlPointToTrack(trackData.track, it)
            }
        }
    }

    companion object {
        private const val TRACK_ID_EXTRAS = "TRACK_ID_EXTRAS"

        fun startActivity(context: Context, trackId: UUID) {
            context.startActivity(Intent(context, TrackDetailActivity::class.java).apply {
                putExtra(TRACK_ID_EXTRAS, trackId)
            })
        }
    }
}