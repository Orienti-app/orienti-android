package app.orienti.android.ui.screens.runner.scan_control

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
import javax.inject.Inject

@AndroidEntryPoint
class ScanControlPointActivity: QrScanningActivity() {
    @Inject lateinit var trainingService: TrainingService

    override fun onCodeScanned(analyzer: QrAnalyzer, barcode: Barcode) {
        val qrData = barcode.rawValue?.ungzipBase64()?.jsonToObject<QrContainer>()

        if(qrData?.type == QrType.CONTROL_POINT && qrData.controlPoint != null){
            cameraProvider?.unbindAll()
            analyzer.close()

            trainingService.onControlPointScanned(qrData.controlPoint)

            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        } else if(qrData?.type == QrType.FINISH_RUN) {
            trainingService.onStopScanned()
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ScanControlPointActivity::class.java)
        }
    }
}