package app.orienti.android.ui.screens.trainer.track_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.print.PrintHelper
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.R
import app.orienti.android.databinding.ActivityTrackDetailBinding
import app.orienti.android.entities.db_entities.ControlPoint
import app.orienti.android.entities.qr.QrContainer
import app.orienti.android.entities.qr.QrType
import app.orienti.android.models.TrainingService
import app.orienti.android.ui.screens.trainer.control_point_selection_activity.ControlPointsSelectionActivity
import app.orienti.android.ui.screens.trainer.main.control_points.ControlPointsAdapter
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ActivityTransitions
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.setCompressedBase64JsonDataToQrCode
import sk.backbone.parent.utils.setSafeOnClickListener
import sk.backbone.parent.utils.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TrackDetailActivity: ParentActivity<ActivityTrackDetailBinding>(ActivityTrackDetailBinding::inflate) {
    @Inject lateinit var trainingService: TrainingService
    @Inject lateinit var adapter: ControlPointsAdapter

    override fun getActivityTransitions(): ActivityTransitions = ActivityTransitions.BOTTOM_TOP

    private val trackId: UUID by lazy {
        intent.getSerializableExtra(TRACK_ID_EXTRAS) as UUID
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        trainingService.getTrackDetail(trackId).observe(this){ trackData ->
            adapter.replaceDataSet(trackData.controlPointsSortedByDate.toMutableList().apply {
                add(ControlPoint(UUID(0, 0), getString(R.string.control_point_finish_code), getString(R.string.control_point_finish_name), Date()))
            })
            title = trackData.track.name
            viewBinding.nameText.text = trackData.track.name
            viewBinding.qrCode.setGzipBase64JsonDataToQrCode(QrContainer(QrType.TRACK, trackData))

            if(trackData.controlPoints.any()){
                viewBinding.edit.visibility = GONE
                viewBinding.print.visibility = VISIBLE
            }

            viewBinding.print.setSafeOnClickListener {
                viewBinding.printable.getBitmap{ bitmap ->
                    val printHelper = PrintHelper(this@TrackDetailActivity)
                    printHelper.scaleMode = PrintHelper.SCALE_MODE_FIT
                    printHelper.printBitmap(getString(R.string.activity_control_point_detail_print_job), bitmap)
                }
            }
        }

        viewBinding.recycler.adapter = adapter
        viewBinding.recycler.layoutManager = LinearLayoutManager(this)

        viewBinding.edit.setSafeOnClickListener {
            ControlPointsSelectionActivity.startActivity(this@TrackDetailActivity, trackId)
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

    companion object {
        private const val TRACK_ID_EXTRAS = "TRACK_ID_EXTRAS"

        fun startActivity(context: Context, trackId: UUID) {
            context.startActivity(Intent(context, TrackDetailActivity::class.java).apply {
                putExtra(TRACK_ID_EXTRAS, trackId)
            })
        }
    }
}