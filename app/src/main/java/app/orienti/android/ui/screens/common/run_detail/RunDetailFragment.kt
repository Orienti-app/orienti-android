package app.orienti.android.ui.screens.common.run_detail

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import app.orienti.android.R
import app.orienti.android.databinding.FragmentRunDetailBinding
import app.orienti.android.entities.db_entities.joined.RunData
import app.orienti.android.entities.qr.QrContainer
import app.orienti.android.entities.qr.QrType
import app.orienti.android.ui.screens.runner.main.ScannedControlPointsAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import sk.backbone.parent.ui.components.recycler_decorations.LinearSpacingItemDecorationVertical
import sk.backbone.parent.ui.screens.ParentFragment
import sk.backbone.parent.utils.getDifferenceIn24HFormat
import sk.backbone.parent.utils.setGzipBase64JsonDataToQrCode
import java.util.*


abstract class RunDetailFragment : ParentFragment<FragmentRunDetailBinding>(FragmentRunDetailBinding::inflate) {
    var currentRun: RunData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scopes.default.launch {
            while (true){
                if(lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)){
                    scopes.ui.launch {
                        refreshTimeBasedData()
                    }
                    delay(1000)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recycler.layoutManager = LinearLayoutManager(view.context)
        viewBinding.recycler.addItemDecoration(
            LinearSpacingItemDecorationVertical(resources.getDimension(
                R.dimen.spacing_default_15_dp).toInt())
        )
    }

    open fun setupWithRunData(runData: RunData?, showQrCode: Boolean, showElapsed: Boolean, showSinceLastControlPoint: Boolean){
        if(runData != null){
            currentRun = runData

            viewBinding.recycler.adapter = view?.let {
                ScannedControlPointsAdapter(it.context).apply {

                    replaceDataSet(runData.runControlPoints)
                    if(showQrCode){
                        viewBinding.qrCode.setGzipBase64JsonDataToQrCode(QrContainer(QrType.RUN, run = runData))
                    }

                    viewBinding.elapsedLayout.visibility = if (showElapsed) VISIBLE else GONE
                    viewBinding.sinceLastControlPointLayout.visibility = if (showSinceLastControlPoint) VISIBLE else GONE

                    refreshTimeBasedData()
                }
            }
        }
    }

    private fun refreshTimeBasedData(){
        val started = currentRun?.run?.started_at
        val finished = currentRun?.run?.finished_at

        viewBinding.startedAtValue.text = "-"
        viewBinding.sinceLastControlPoint.text = "-"
        viewBinding.elapsedValue.text = "-"
        viewBinding.finished.text = "-"

        if(started != null){
            viewBinding.startedAtValue.text = started.toString()

            if(finished == null){
                viewBinding.elapsedValue.text = getDifferenceIn24HFormat(Date(), started)
            } else {
                viewBinding.finished.text = finished.toString()
                viewBinding.elapsedValue.text = getDifferenceIn24HFormat(finished, started)
            }

            currentRun?.scannedRunControlPoints?.maxByOrNull { it.scanned_at }?.let {
                viewBinding.sinceLastControlPoint.text = getDifferenceIn24HFormat(finished ?: Date(), it.scanned_at)
            }
        }
    }
}