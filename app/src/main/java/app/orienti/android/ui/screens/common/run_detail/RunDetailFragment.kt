package app.orienti.android.ui.screens.common.run_detail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.R
import app.orienti.android.databinding.FragmentRunDetailBinding
import app.orienti.android.entities.db_entities.Track
import app.orienti.android.ui.screens.runner.main.ControlPointsAdapter
import sk.backbone.parent.ui.components.recycler_decorations.LinearSpacingItemDecorationVertical
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.setCompressedBase64JsonDataToQrCode
import java.util.*


abstract class RunDetailFragment : ParentFragment<FragmentRunDetailBinding>(FragmentRunDetailBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recycler.layoutManager = LinearLayoutManager(view.context)
        viewBinding.recycler.addItemDecoration(
            LinearSpacingItemDecorationVertical(resources.getDimension(
                R.dimen.spacing_default_15_dp).toInt())
        )
        viewBinding.recycler.adapter = ControlPointsAdapter(view.context).apply {
            replaceDataSet(listOf(true, true, false, true, false, true, false, true, false, true, false, true, false, true, false))
        }

        viewBinding.qrCode.setCompressedBase64JsonDataToQrCode(Track(UUID.randomUUID(), "name", Date()))
    }

    fun setRunId(runId: UUID){
        // Todo: remove old run observer
        // Todo: set run observer
        // Todo: in observer update qr code
    }
}