package app.orienti.android.ui.screens.trainer.training_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import app.orienti.android.databinding.UiComponentTextRowBinding
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.ui.screens.trainer.run_detail.TrainerRunDetailActivity
import sk.backbone.parent.ui.screens.ParentRecyclerAdapter
import sk.backbone.parent.utils.setSafeOnClickListener

class RunsAdapter(context: Context): ParentRecyclerAdapter<RunData, RunsAdapter.ControlPointViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlPointViewHolder {
        return ControlPointViewHolder(UiComponentTextRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    class ControlPointViewHolder(private val viewBinding: UiComponentTextRowBinding): ParentRecyclerViewHolder<RunData>(viewBinding.root) {
        override fun bindData(viewData: RunData) {
            viewBinding.name.text = viewData.user?.name

            viewBinding.root.setSafeOnClickListener {
                TrainerRunDetailActivity.startActivity(it.context, viewData.run.runId)
            }
        }
    }
}