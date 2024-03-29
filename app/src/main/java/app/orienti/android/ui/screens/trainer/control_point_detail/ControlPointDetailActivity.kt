package app.orienti.android.ui.screens.trainer.control_point_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.print.PrintHelper
import app.orienti.android.R
import app.orienti.android.databinding.ActivityControlPointDetailBinding
import app.orienti.android.entities.db_entities.ControlPoint
import app.orienti.android.entities.qr.QrContainer
import app.orienti.android.entities.qr.QrType
import app.orienti.android.models.TrainingService
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.getBitmap
import sk.backbone.parent.utils.setGzipBase64JsonDataToQrCode
import sk.backbone.parent.utils.setSafeOnClickListener
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ControlPointDetailActivity : ParentActivity<ActivityControlPointDetailBinding>(ActivityControlPointDetailBinding::inflate) {
    @Inject lateinit var trainingService: TrainingService

    val controlPointId by lazy {
        intent.getSerializableExtra(CONTROL_POINT_EXTRAS) as UUID
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        trainingService.getControlPointById(controlPointId).observe(this) { controlPoint ->
            setupUi(controlPoint)
        }
    }

    private fun setupUi(controlPoint: ControlPoint?){
        if(controlPointId == UUID(0,0)){
            title = getString(R.string.control_point_finish_name)

            viewBinding.nameText.text = getString(R.string.control_point_finish_name)
            viewBinding.codeText.text = getString(R.string.control_point_finish_code)

            viewBinding.controlPointImageView.setGzipBase64JsonDataToQrCode(QrContainer(QrType.FINISH_RUN))
        } else {
            title = controlPoint?.name ?: ""

            viewBinding.nameText.text = controlPoint?.name ?: ""
            viewBinding.codeText.text = controlPoint?.code ?: ""

            viewBinding.controlPointImageView.setGzipBase64JsonDataToQrCode(QrContainer(QrType.CONTROL_POINT, controlPoint = controlPoint))
        }

        viewBinding.print.setSafeOnClickListener {
            viewBinding.printable.getBitmap{ bitmap ->
                val printHelper = PrintHelper(this@ControlPointDetailActivity)
                printHelper.scaleMode = PrintHelper.SCALE_MODE_FIT
                printHelper.printBitmap(getString(R.string.activity_control_point_detail_print_job), bitmap)
            }
        }
    }

    companion object {
        const val CONTROL_POINT_EXTRAS = "CONTROL_POINT_EXTRAS"

        fun startActivity(context: Context, controlPointId: UUID){
            context.startActivity(Intent(context, ControlPointDetailActivity::class.java).apply {
                putExtra(CONTROL_POINT_EXTRAS, controlPointId)
            })
        }
    }
}