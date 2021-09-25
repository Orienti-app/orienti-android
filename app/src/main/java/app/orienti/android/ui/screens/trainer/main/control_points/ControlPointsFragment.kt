package app.orienti.android.ui.screens.trainer.main.control_points

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.databinding.FragmentControlPointsBinding
import app.orienti.android.ui.screens.trainer.create_control_point.CreateControlPointActivity
import app.orienti.android.ui.screens.trainer.create_training.CreateTrainingActivity
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.setSafeOnClickListener

class ControlPointsFragment : ParentFragment<FragmentControlPointsBinding>(FragmentControlPointsBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recycler.layoutManager = LinearLayoutManager(view.context)
        viewBinding.recycler.adapter = ControlPointsAdapter(view.context).apply {
            replaceDataSet(listOf("first", "second", "third"))
        }

        viewBinding.add.setSafeOnClickListener {
            CreateControlPointActivity.startActivity(it.context)
        }
    }
}