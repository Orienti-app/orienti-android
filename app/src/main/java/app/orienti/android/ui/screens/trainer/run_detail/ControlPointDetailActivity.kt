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
import sk.backbone.parent.ui.screens.ParentActivity
import sk.backbone.parent.utils.setGzipBase64JsonDataToQrCode
import sk.backbone.parent.utils.setSafeOnClickListener
import sk.backbone.parent.utils.toJsonString
import java.util.*


class ControlPointDetailActivity : ParentActivity<ActivityControlPointDetailBinding>(ActivityControlPointDetailBinding::inflate) {
    val controlPoint = ControlPoint(UUID.randomUUID(), "CODE", "NAME", Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = controlPoint.name

        viewBinding.nameText.text = controlPoint.name
        viewBinding.codeText.text = controlPoint.code
        viewBinding.controlPointImageView.setGzipBase64JsonDataToQrCode(controlPoint)

        viewBinding.print.setSafeOnClickListener {
            viewBinding.printable.getBitmap{ bitmap ->
                val printHelper = PrintHelper(this@ControlPointDetailActivity)
                printHelper.scaleMode = PrintHelper.SCALE_MODE_FIT
                printHelper.printBitmap(getString(R.string.activity_control_point_detail_print_job), bitmap)
            }
        }
    }

    private fun View.getBitmap(onBitmapReady: (Bitmap) -> Unit) {
        post {
            val bitmap = Bitmap.createBitmap(
                measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            draw(canvas)
            onBitmapReady(bitmap)
        }
    }

    companion object {
        fun startActivity(context: Context){
            context.startActivity(Intent(context, ControlPointDetailActivity::class.java))
        }
    }
}