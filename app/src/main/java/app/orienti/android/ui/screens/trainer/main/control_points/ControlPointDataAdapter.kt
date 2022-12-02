package app.orienti.android.ui.screens.trainer.main.control_points

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.orienti.android.databinding.UiComponentTextRowBinding
import app.orienti.android.entities.db_entities.TrackControlPoint
import app.orienti.android.entities.db_entities.joined.ControlPointData
import app.orienti.android.models.TrainingService
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import sk.backbone.parent.ui.screens.ParentRecyclerAdapter
import sk.backbone.parent.utils.setSafeOnClickListener
import java.util.*
import javax.inject.Inject

@ActivityScoped
class ControlPointDataAdapter @Inject constructor(@ActivityContext context: Context): ParentRecyclerAdapter<ControlPointData, ControlPointDataAdapter.ControlPointDataViewHolder>(context) {
    @Inject lateinit var trainingService: TrainingService

    var checkForTrackId: UUID? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlPointDataViewHolder {
        return ControlPointDataViewHolder(UiComponentTextRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    inner class ControlPointDataViewHolder(private val viewBinding: UiComponentTextRowBinding): ParentRecyclerViewHolder<ControlPointData>(viewBinding.root) {
        override fun bindData(viewData: ControlPointData) {
            viewBinding.name.text = viewData.controlPoint.code
            val isChecked = viewData.tracks?.any { it -> it.id == checkForTrackId } == true
            val trackControlPoint = checkForTrackId?.let { checkForTrackId -> TrackControlPoint(checkForTrackId, viewData.controlPoint.id) }

            if (isChecked){
                viewBinding.checkedImageView.visibility = View.VISIBLE
            } else {
                viewBinding.checkedImageView.visibility = View.GONE
            }

            viewBinding.root.setSafeOnClickListener {
                trackControlPoint?.let { trackControlPoint ->
                    if(isChecked){
                        trainingService.deleteTrackControlPoint(trackControlPoint)
                    } else {
                        trainingService.createTrackControlPoint(trackControlPoint)
                    }
                }
            }
        }
    }
}