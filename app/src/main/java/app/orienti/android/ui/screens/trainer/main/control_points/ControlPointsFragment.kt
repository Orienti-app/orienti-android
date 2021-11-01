package app.orienti.android.ui.screens.trainer.main.control_points

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.databinding.FragmentControlPointsBinding
import app.orienti.android.models.TrainingModel
import app.orienti.android.ui.screens.trainer.create_control_point.CreateControlPointActivity
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.setSafeOnClickListener
import javax.inject.Inject

@AndroidEntryPoint
class ControlPointsFragment : ParentFragment<FragmentControlPointsBinding>(FragmentControlPointsBinding::inflate) {
    @Inject lateinit var trainingModel: TrainingModel

    var adapter: ControlPointsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (adapter == null){
            adapter = ControlPointsAdapter(view.context)
            viewBinding.recycler.adapter = adapter
            viewBinding.recycler.layoutManager = LinearLayoutManager(view.context)
        }

        viewBinding.add.setSafeOnClickListener {
            CreateControlPointActivity.startActivity(it.context)
        }
    }

    override fun onResume() {
        super.onResume()

        adapter?.replaceDataSet(trainingModel.getControlPoints())
    }
}