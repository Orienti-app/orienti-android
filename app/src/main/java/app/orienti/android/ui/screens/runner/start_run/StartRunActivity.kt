package app.orienti.android.ui.screens.runner.start_run

import android.content.Context
import android.content.Intent
import app.orienti.android.ui.screens.common.qr_scanning.QrAnalyzer
import app.orienti.android.ui.screens.common.qr_scanning.QrScanningActivity
import com.google.mlkit.vision.barcode.Barcode
import sk.backbone.parent.utils.toJsonString

class StartRunActivity: QrScanningActivity() {
    override fun onCodeScanned(analyzer: QrAnalyzer, barcode: Barcode) {
        (barcode.rawValue?.toIntOrNull()?.toString())?.apply {
            cameraProvider?.unbindAll()
            analyzer.close()

            val intent = Intent()
            intent.putExtra(SCANNED_QR_VALUE_EXTRAS, this.toJsonString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, StartRunActivity::class.java)
        }
    }
}