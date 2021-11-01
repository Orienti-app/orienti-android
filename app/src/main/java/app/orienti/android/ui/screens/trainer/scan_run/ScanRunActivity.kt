package app.orienti.android.ui.screens.trainer.scan_run

import android.content.Context
import android.content.Intent
import app.orienti.android.ui.screens.common.qr_scanning.QrAnalyzer
import app.orienti.android.ui.screens.common.qr_scanning.QrScanningActivity
import app.orienti.android.ui.screens.common.qr_scanning.utils.QRCodeParser
import com.google.mlkit.vision.barcode.Barcode
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.utils.toJsonString

@AndroidEntryPoint
class ScanRunActivity: QrScanningActivity() {
    override fun onCodeScanned(analyzer: QrAnalyzer, barcode: Barcode) {
        val qrRouteData = QRCodeParser.parseTrackDefinition(barcode.rawValue)

        if(qrRouteData != null){
            cameraProvider?.unbindAll()
            analyzer.close()

            val intent = Intent()
            intent.putExtra(SCANNED_QR_VALUE_EXTRAS, qrRouteData.toJsonString())
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