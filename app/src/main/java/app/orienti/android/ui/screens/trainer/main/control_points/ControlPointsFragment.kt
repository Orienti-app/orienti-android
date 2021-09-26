package app.orienti.android.ui.screens.trainer.main.control_points

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.databinding.FragmentControlPointsBinding
import app.orienti.android.ui.base.DefaultViewModel
import app.orienti.android.ui.screens.trainer.create_control_point.CreateControlPointActivity
import app.orienti.android.ui.screens.trainer.create_training.CreateTrainingActivity
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.setSafeOnClickListener

class ControlPointsFragment : ParentFragment<FragmentControlPointsBinding>(FragmentControlPointsBinding::inflate) {
    private val viewModel by lazy { getViewModel<DefaultViewModel>() }

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

        adapter?.replaceDataSet(viewModel.getControlPoints())
    }
}