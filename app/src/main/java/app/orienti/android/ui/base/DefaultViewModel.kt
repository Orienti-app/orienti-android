package app.orienti.android.ui.base

import android.app.Application
import app.orienti.android.models.models.ITrainingModel
import app.orienti.android.models.models.IUserModel


open class DefaultViewModel(context: Application) : BaseViewModel(context), IUserModel, ITrainingModel {
}
