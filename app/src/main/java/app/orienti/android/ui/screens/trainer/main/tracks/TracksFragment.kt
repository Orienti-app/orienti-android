package app.orienti.android.ui.screens.trainer.main.tracks

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.databinding.FragmentTracksBinding
import app.orienti.android.models.TrainingService
import app.orienti.android.ui.screens.trainer.create_track.CreateTrackActivity
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.setSafeOnClickListener
import javax.inject.Inject

@AndroidEntryPoint
class TracksFragment : ParentFragment<FragmentTracksBinding>(FragmentTracksBinding::inflate) {
    @Inject lateinit var trainingService: TrainingService

    var adapter: TracksAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (adapter == null){
            adapter = TracksAdapter(view.context)
        }

        viewBinding.recycler.adapter = adapter
        viewBinding.recycler.layoutManager = LinearLayoutManager(view.context)

        viewBinding.add.setSafeOnClickListener {
            CreateTrackActivity.startActivity(it.context)
        }
    }

    override fun onResume() {
        super.onResume()

        adapter?.replaceDataSet(trainingService.getTracks())
    }
}