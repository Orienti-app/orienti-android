package app.orienti.android.ui.screens.trainer.scan_run

import android.content.Context
import android.content.Intent
import app.orienti.android.models.TrainingService
import app.orienti.android.ui.screens.common.qr_scanning.QrAnalyzer
import app.orienti.android.ui.screens.common.qr_scanning.QrScanningActivity
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScanRunActivity: QrScanningActivity() {
    @Inject lateinit var trainingService: TrainingService

    override fun onCodeScanned(analyzer: QrAnalyzer, barcode: Barcode) {
        val trackData = barcode.rawValue

        if(trackData != null){
            cameraProvider?.unbindAll()
            analyzer.close()

            // Todo: Add new run

            val intent = Intent()
            intent.putExtra(SCANNED_QR_VALUE_EXTRAS, trackData)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ScanRunActivity::class.java)
        }
    }
}