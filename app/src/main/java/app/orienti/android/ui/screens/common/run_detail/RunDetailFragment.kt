package app.orienti.android.ui.screens.common.run_detail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.R
import app.orienti.android.databinding.FragmentRunDetailBinding
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.entities.qr.QrContainer
import app.orienti.android.entities.qr.QrType
import app.orienti.android.ui.screens.runner.main.ScannedControlPointsAdapter
import sk.backbone.parent.ui.components.recycler_decorations.LinearSpacingItemDecorationVertical
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.formatIsoDateOnlyToLocalDate
import sk.backbone.parent.utils.getDifferenceIn24HFormat
import sk.backbone.parent.utils.setGzipBase64JsonDataToQrCode
import java.util.*


abstract class RunDetailFragment : ParentFragment<FragmentRunDetailBinding>(FragmentRunDetailBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recycler.layoutManager = LinearLayoutManager(view.context)
        viewBinding.recycler.addItemDecoration(
            LinearSpacingItemDecorationVertical(resources.getDimension(
                R.dimen.spacing_default_15_dp).toInt())
        )
    }

    fun setupWithRunData(runData: RunData?, showQrCode: Boolean){
        if(runData != null){
            viewBinding.recycler.adapter = view?.let {
                ScannedControlPointsAdapter(it.context).apply {
                    val started = runData.run.started_at
                    val finished = runData.run.finished_at

                    replaceDataSet(runData.runControlPoints)
                    if(showQrCode){
                        viewBinding.qrCode.setGzipBase64JsonDataToQrCode(QrContainer(QrType.RUN, run = runData))
                    }

                    if(started == null){
                        viewBinding.startedAtValue.text = "-"
                    } else {
                        viewBinding.startedAtValue.text = runData.run.started_at.toString()
                        if(finished == null){
                            viewBinding.elapsedValue.text = getDifferenceIn24HFormat(Date(), started)
                        } else {
                            viewBinding.elapsedValue.text = getDifferenceIn24HFormat(finished, started)
                        }
                    }
                }
            }
        }
    }
}