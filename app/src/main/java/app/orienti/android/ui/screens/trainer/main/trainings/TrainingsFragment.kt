package app.orienti.android.ui.screens.trainer.main.trainings

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.databinding.FragmentTrainingsBinding
import app.orienti.android.models.TrainingModel
import app.orienti.android.ui.screens.trainer.create_training.CreateTrainingActivity
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.setSafeOnClickListener
import javax.inject.Inject

@AndroidEntryPoint
class TrainingsFragment : ParentFragment<FragmentTrainingsBinding>(FragmentTrainingsBinding::inflate) {
    @Inject lateinit var trainingModel: TrainingModel

    var adapter: TrainingsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (adapter == null){
            adapter = TrainingsAdapter(view.context)
        }

        viewBinding.recycler.adapter = adapter
        viewBinding.recycler.layoutManager = LinearLayoutManager(view.context)

        viewBinding.add.setSafeOnClickListener {
            CreateTrainingActivity.startActivity(it.context)
        }
    }

    override fun onResume() {
        super.onResume()

        adapter?.replaceDataSet(trainingModel.getTrainingData())
    }
}