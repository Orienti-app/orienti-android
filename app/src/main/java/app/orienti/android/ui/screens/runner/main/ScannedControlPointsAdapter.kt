package app.orienti.android.ui.screens.runner.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import app.orienti.android.R
import app.orienti.android.databinding.UiComponentControlPointRowBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import sk.backbone.parent.ui.screens.ParentRecyclerAdapter
import sk.backbone.parent.utils.getDifferenceIn24HFormat
import java.util.*
import javax.inject.Inject

@ActivityScoped
class ScannedControlPointsAdapter @Inject constructor(@ApplicationContext context: Context): ParentRecyclerAdapter<Boolean, ScannedControlPointsAdapter.ControlPointViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlPointViewHolder {
        return ControlPointViewHolder(UiComponentControlPointRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    class ControlPointViewHolder(private val viewBinding: UiComponentControlPointRowBinding): ParentRecyclerViewHolder<Boolean>(viewBinding.root) {
        override fun bindData(viewData: Boolean) {
            viewBinding.scanned.isChecked = viewData
            val timeInPast = Date(Date().time - 49234728934)
            val now = Date()

            viewBinding.name.text = "Wiii"
            viewBinding.time.text = viewBinding.root.context.getString(R.string.activity_main_runner_scanned_in_format).format(getDifferenceIn24HFormat(timeInPast, now))
        }
    }
}