package app.orienti.android.ui.screens.trainer.scan_run

import android.content.Context
import android.content.Intent
import app.orienti.android.entities.qr.QrContainer
import app.orienti.android.entities.qr.QrType
import app.orienti.android.models.TrainingService
import app.orienti.android.ui.screens.common.qr_scanning.QrAnalyzer
import app.orienti.android.ui.screens.common.qr_scanning.QrScanningActivity
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.utils.jsonToObject
import sk.backbone.parent.utils.ungzipBase64
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ScanRunActivity: QrScanningActivity() {
    @Inject lateinit var trainingService: TrainingService

    private val trainingId by lazy {
        intent.getSerializableExtra(TRAINING_ID_EXTRAS) as UUID
    }

    override fun onCodeScanned(analyzer: QrAnalyzer, barcode: Barcode) {
        val qrContainer = barcode.rawValue?.ungzipBase64()?.jsonToObject<QrContainer>()

        if(qrContainer?.type == QrType.RUN && qrContainer.run != null){
            cameraProvider?.unbindAll()
            analyzer.close()

            trainingService.onRunScanned(qrContainer.run, trainingId)

            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        private const val TRAINING_ID_EXTRAS = "TRAINING_ID_EXTRAS"

        fun createIntent(context: Context, trainingId: UUID): Intent {
            return Intent(context, ScanRunActivity::class.java).apply {
                putExtra(TRAINING_ID_EXTRAS, trainingId)
            }
        }
    }
}