package app.orienti.android.ui.screens.trainer.main.tracks

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.databinding.FragmentTracksBinding
import app.orienti.android.ui.screens.trainer.create_track.CreateTrackActivity
import app.orienti.android.ui.screens.trainer.create_training.CreateTrainingActivity
import app.orienti.android.ui.screens.trainer.main.control_points.ControlPointsAdapter
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.setSafeOnClickListener

class TracksFragment : ParentFragment<FragmentTracksBinding>(FragmentTracksBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recycler.layoutManager = LinearLayoutManager(view.context)
        viewBinding.recycler.adapter = TracksAdapter(view.context).apply {
            replaceDataSet(listOf("first", "second", "third"))
        }

        viewBinding.add.setSafeOnClickListener {
            CreateTrackActivity.startActivity(it.context)
        }
    }
}