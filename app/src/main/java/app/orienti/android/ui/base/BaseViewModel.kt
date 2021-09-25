package app.orienti.android.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import app.orienti.android.models.models.ModelsProvider

abstract class BaseViewModel(override val context: Application) : AndroidViewModel(context), ModelsProvider {
    val modelsProvider: ModelsProvider get() = this
}