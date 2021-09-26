package app.orienti.android.ui.screens.trainer.main.trainings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import app.orienti.android.R
import app.orienti.android.databinding.UiComponentControlPointRowBinding
import app.orienti.android.databinding.UiComponentTextRowBinding
import app.orienti.android.entities.db_entities.joined.TrainingData
import sk.backbone.parent.ui.screens.ParentRecyclerAdapter
import sk.backbone.parent.utils.getDifferenceIn24HFormat
import java.util.*

class TrainingsAdapter(context: Context): ParentRecyclerAdapter<TrainingData, TrainingsAdapter.ControlPointViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlPointViewHolder {
        return ControlPointViewHolder(UiComponentTextRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    class ControlPointViewHolder(private val viewBinding: UiComponentTextRowBinding): ParentRecyclerViewHolder<TrainingData>(viewBinding.root) {
        override fun bindData(viewData: TrainingData) {
            viewBinding.name.text = viewData.training.name
        }
    }
}