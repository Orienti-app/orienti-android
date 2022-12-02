package app.orienti.android.ui.screens.runner.start_run

import android.content.Context
import android.content.Intent
import app.orienti.android.entities.db_entities.joined.TrackData
import app.orienti.android.models.TrainingService
import app.orienti.android.ui.screens.common.qr_scanning.QrAnalyzer
import app.orienti.android.ui.screens.common.qr_scanning.QrScanningActivity
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.utils.decompressFromBase64
import sk.backbone.parent.utils.jsonToObject
import sk.backbone.parent.utils.toJsonString
import javax.inject.Inject

@AndroidEntryPoint
class NewRunActivity: QrScanningActivity() {
    @Inject lateinit var trainingService: TrainingService

    override fun onCodeScanned(analyzer: QrAnalyzer, barcode: Barcode) {
        val trackData = barcode.rawValue?.decompressFromBase64()?.jsonToObject<TrackData>()

        if(trackData != null){
            cameraProvider?.unbindAll()
            analyzer.close()

            trainingService.startNewRun(trackData)

            val intent = Intent()
            intent.putExtra(SCANNED_QR_VALUE_EXTRAS, trackData.toJsonString())
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