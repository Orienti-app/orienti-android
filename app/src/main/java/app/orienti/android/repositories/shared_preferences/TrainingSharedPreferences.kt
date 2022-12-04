package app.orienti.android.repositories.shared_preferences

import sk.backbone.parent.repositories.shared_preferences.SharedPreferencesDataProvider
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingSharedPreferences @Inject constructor() : SharedPreferencesDataProvider() {
    fun getCurrentlyActiveRunId(): UUID? {
        return getValue(CURRENTLY_ACTIVE_RUN_KEY)
    }

    fun setCurrentlyActiveRunId(uuid: UUID?){
        return storeValue(CURRENTLY_ACTIVE_RUN_KEY, uuid)
    }

    companion object {
        private const val CURRENTLY_ACTIVE_RUN_KEY = "CURRENTLY_ACTIVE_RUN_KEY"
    }

    override val sharedPreferencesKey: String get() = "TRAINING_SHARED_PREFERENCES"
}