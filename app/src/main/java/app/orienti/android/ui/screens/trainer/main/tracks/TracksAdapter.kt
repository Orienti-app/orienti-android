package app.orienti.android.ui.screens.trainer.main.tracks

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import app.orienti.android.R
import app.orienti.android.databinding.UiComponentControlPointRowBinding
import app.orienti.android.databinding.UiComponentTextRowBinding
import sk.backbone.parent.ui.screens.ParentRecyclerAdapter
import sk.backbone.parent.utils.getDifferenceIn24HFormat
import java.util.*

class TracksAdapter(context: Context): ParentRecyclerAdapter<String, TracksAdapter.ControlPointViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlPointViewHolder {
        return ControlPointViewHolder(UiComponentTextRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    class ControlPointViewHolder(private val viewBinding: UiComponentTextRowBinding): ParentRecyclerViewHolder<String>(viewBinding.root) {
        override fun bindData(viewData: String) {
            viewBinding.name.text = viewData
        }
    }
}