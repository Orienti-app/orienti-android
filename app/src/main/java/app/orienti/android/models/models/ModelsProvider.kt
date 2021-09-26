package app.orienti.android.models.models

import android.content.Context
import sk.backbone.parent.models.IModelsProvider

interface ModelsProvider : IModelsProvider {
    val userModel: IUserModel
        get() = object : IUserModel {
            override val context: Context get() = this@ModelsProvider.context
            override val modelsProvider: ModelsProvider get() = this@ModelsProvider
        }

    val trainingModel: ITrainingModel
        get() = object : ITrainingModel {
            override val context: Context get() = this@ModelsProvider.context
            override val modelsProvider: ModelsProvider get() = this@ModelsProvider
        }
}