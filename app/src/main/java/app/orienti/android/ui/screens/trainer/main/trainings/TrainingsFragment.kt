package app.orienti.android.ui.screens.trainer.main.trainings

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.databinding.FragmentTrainingsBinding
import app.orienti.android.ui.screens.trainer.create_training.CreateTrainingActivity
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.setSafeOnClickListener

class TrainingsFragment : ParentFragment<FragmentTrainingsBinding>(FragmentTrainingsBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recycler.layoutManager = LinearLayoutManager(view.context)
        viewBinding.recycler.adapter = TrainingsAdapter(view.context).apply {
            replaceDataSet(listOf("first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third","first", "second", "third",))
        }

        viewBinding.add.setSafeOnClickListener {
            CreateTrainingActivity.startActivity(it.context)
        }
    }
}