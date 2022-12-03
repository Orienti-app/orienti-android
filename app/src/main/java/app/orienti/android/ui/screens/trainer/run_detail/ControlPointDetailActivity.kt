package app.orienti.android.ui.screens.trainer.run_detail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.print.PrintHelper
import app.orienti.android.R
import app.orienti.android.databinding.ActivityControlPointDetailBinding
import app.orienti.android.entities.db_entities.ControlPoint
import app.orienti.android.models.TrainingService
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.getBitmap
import sk.backbone.parent.utils.setGzipBase64JsonDataToQrCode
import sk.backbone.parent.utils.setSafeOnClickListener
import sk.backbone.parent.utils.toJsonString
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
            title = controlPoint?.name ?: ""

            viewBinding.nameText.text = controlPoint?.name ?: ""
            viewBinding.codeText.text = controlPoint?.code ?: ""
            viewBinding.controlPointImageView.setGzipBase64JsonDataToQrCode(controlPoint)

            viewBinding.print.setSafeOnClickListener {
                viewBinding.printable.getBitmap{ bitmap ->
                    val printHelper = PrintHelper(this@ControlPointDetailActivity)
                    printHelper.scaleMode = PrintHelper.SCALE_MODE_FIT
                    printHelper.printBitmap(getString(R.string.activity_control_point_detail_print_job), bitmap)
                }
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