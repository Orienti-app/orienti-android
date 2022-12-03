package app.orienti.android.ui.screens.runner.start_run

import android.content.Context
import android.content.Intent
import app.orienti.android.entities.qr.QrContainer
import app.orienti.android.entities.qr.QrType
import app.orienti.android.models.TrainingService
import app.orienti.android.ui.screens.common.qr_scanning.QrAnalyzer
import app.orienti.android.ui.screens.common.qr_scanning.QrScanningActivity
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.utils.decompressFromBase64
import sk.backbone.parent.utils.jsonToObject
import sk.backbone.parent.utils.toJsonString
import sk.backbone.parent.utils.ungzipBase64
import javax.inject.Inject

@AndroidEntryPoint
class NewRunActivity: QrScanningActivity() {
    @Inject lateinit var trainingService: TrainingService

    override fun onCodeScanned(analyzer: QrAnalyzer, barcode: Barcode) {
        val qrData = barcode.rawValue?.ungzipBase64()?.jsonToObject<QrContainer>()

        if (qrData?.type == QrType.TRACK && qrData.track != null) {
            cameraProvider?.unbindAll()
            analyzer.close()

            trainingService.startNewRun(qrData.track)

            val intent = Intent()
            intent.putExtra(SCANNED_QR_VALUE_EXTRAS, qrData.track.toJsonString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, NewRunActivity::class.java)
        }
    }
}