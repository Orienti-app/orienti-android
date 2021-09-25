package app.orienti.android.ui.screens.runner.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import app.orienti.android.databinding.UiComponentControlPointRowBinding
import sk.backbone.parent.ui.screens.ParentRecyclerAdapter

class ControlPointsAdapter(context: Context): ParentRecyclerAdapter<Boolean, ControlPointsAdapter.ControlPointViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlPointViewHolder {
        return ControlPointViewHolder(UiComponentControlPointRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    class ControlPointViewHolder(val viewBinding: UiComponentControlPointRowBinding): ParentRecyclerViewHolder<Boolean>(viewBinding.root) {
        override fun bindData(viewData: Boolean) {
            viewBinding.scanned.isChecked = viewData
            viewBinding.name.text = "Wiiii"
            viewBinding.time.text = "asdaslkjdlksajdklsajdlkasdasdadsadasdsadada"
        }
    }
}