package app.orienti.android.ui.screens.trainer.main.tracks

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import app.orienti.android.databinding.UiComponentTextRowBinding
import app.orienti.android.entities.db_entities.Track
import app.orienti.android.ui.screens.trainer.track_detail.TrackDetailActivity
import sk.backbone.parent.ui.screens.ParentRecyclerAdapter
import sk.backbone.parent.utils.setSafeOnClickListener

class TracksAdapter(context: Context): ParentRecyclerAdapter<Track, TracksAdapter.ControlPointViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlPointViewHolder {
        return ControlPointViewHolder(UiComponentTextRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    inner class ControlPointViewHolder(private val viewBinding: UiComponentTextRowBinding): ParentRecyclerViewHolder<Track>(viewBinding.root) {
        override fun bindData(viewData: Track) {
            viewBinding.name.text = viewData.name
            viewBinding.root.setSafeOnClickListener {
                TrackDetailActivity.startActivity(it.context, viewData.id)
            }
        }
    }
}