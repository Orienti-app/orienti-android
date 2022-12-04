package app.orienti.android.repositories.shared_preferences

import app.orienti.android.entities.UserType
import app.orienti.android.entities.db_entities.User
import sk.backbone.parent.repositories.shared_preferences.SharedPreferencesDataProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSharedPreferences @Inject constructor() : SharedPreferencesDataProvider() {
    fun getCurrentUser(): User? {
        return getValue(USER_KEY)
    }

    fun setCurrentUser(user: User?){
        return storeValue(USER_KEY, user)
    }

    fun getType(): UserType {
        return getValue(USER_TYPE_KEY) ?: UserType.NONE
    }

    fun setType(userMode: UserType?){
        return storeValue(USER_TYPE_KEY, userMode)
    }

    companion object {
        private const val USER_KEY = "USER_KEY"
        private const val USER_TYPE_KEY = "USER_MODE_KEY"
    }

    override val sharedPreferencesKey: String get() = "USER_SHARED_PREFERENCES"
}