package app.orienti.android.ui.screens.trainer.main.control_points

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import app.orienti.android.R
import app.orienti.android.databinding.UiComponentControlPointRowBinding
import app.orienti.android.databinding.UiComponentTextRowBinding
import app.orienti.android.entities.db_entities.ControlPoint
import app.orienti.android.ui.screens.trainer.run_detail.ControlPointDetailActivity
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import sk.backbone.parent.ui.screens.ParentRecyclerAdapter
import sk.backbone.parent.utils.getDifferenceIn24HFormat
import sk.backbone.parent.utils.setSafeOnClickListener
import java.util.*
import javax.inject.Inject

@ActivityScoped
class ControlPointsAdapter @Inject constructor(@ActivityContext context: Context): ParentRecyclerAdapter<ControlPoint, ControlPointsAdapter.ControlPointViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlPointViewHolder {
        return ControlPointViewHolder(UiComponentTextRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    class ControlPointViewHolder(private val viewBinding: UiComponentTextRowBinding): ParentRecyclerViewHolder<ControlPoint>(viewBinding.root) {
        override fun bindData(viewData: ControlPoint) {
            viewBinding.name.text = viewData.name

            viewBinding.root.setSafeOnClickListener {
                ControlPointDetailActivity.startActivity(it.context, viewData.id)
            }
        }
    }
}