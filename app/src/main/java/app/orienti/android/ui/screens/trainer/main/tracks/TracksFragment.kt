package app.orienti.android.ui.screens.trainer.main.tracks

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.databinding.FragmentTracksBinding
import app.orienti.android.ui.base.DefaultViewModel
import app.orienti.android.ui.screens.trainer.create_track.CreateTrackActivity
import app.orienti.android.ui.screens.trainer.create_training.CreateTrainingActivity
import app.orienti.android.ui.screens.trainer.main.control_points.ControlPointsAdapter
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.setSafeOnClickListener

class TracksFragment : ParentFragment<FragmentTracksBinding>(FragmentTracksBinding::inflate) {
    private val viewModel by lazy { getViewModel<DefaultViewModel>() }

    var adapter: TracksAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (adapter == null){
            adapter = TracksAdapter(view.context)
            viewBinding.recycler.adapter = adapter
            viewBinding.recycler.layoutManager = LinearLayoutManager(view.context)
        }

        viewBinding.add.setSafeOnClickListener {
            CreateTrackActivity.startActivity(it.context)
        }
    }

    override fun onResume() {
        super.onResume()

        adapter?.replaceDataSet(viewModel.getTracks())
    }
}