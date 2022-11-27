package app.orienti.android.ui.screens.common.qr_scanning

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Size
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import app.orienti.android.databinding.ActivityQrScanningBinding
import com.google.mlkit.vision.barcode.common.Barcode
import sk.backbone.parent.ui.screens.ActivityTransitions
import sk.backbone.parent.ui.screens.ParentActivity

abstract class QrScanningActivity: ParentActivity<ActivityQrScanningBinding>(ActivityQrScanningBinding::inflate){
    override fun getActivityTransitions(): ActivityTransitions = ActivityTransitions.BOTTOM_TOP

    protected var cameraProvider: ProcessCameraProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startQrScanner()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()

            try {
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(viewBinding.activityQrScanningPreview.surfaceProvider)
                }

                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetResolution(Size(1280, 720))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build().apply {
                        setAnalyzer(ContextCompat.getMainExecutor(this@QrScanningActivity), QrAnalyzer(::onCodeScanned))
                    }

                cameraProvider?.unbindAll()

                cameraProvider?.bindToLifecycle(this, cameraSelector, imageAnalysis, preview)

            } catch (exc: Exception) {

            }
        }, ContextCompat.getMainExecutor(this))
    }

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            startCamera()
        } else {
            finish()
        }
    }

    private fun startQrScanner() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    abstract fun onCodeScanned(analyzer: QrAnalyzer, barcode: Barcode)

    companion object {
        const val SCANNED_QR_VALUE_EXTRAS = "SCANNED_QR_VALUE_EXTRAS"
    }
}