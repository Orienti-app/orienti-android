package app.orienti.android.repositories.shared_preferences

import android.content.Context
import app.orienti.android.entities.UserType
import sk.backbone.parent.repositories.shared_preferences.SharedPreferencesDataProvider

class UserSharedPreferences(context: Context) : SharedPreferencesDataProvider("USER_SHARED_PREFERENCES", context) {
    fun getName(): String? {
        return getValue(USER_NAME_KEY)
    }

    fun setName(userName: String?){
        return storeValue(USER_NAME_KEY, userName)
    }

    fun getType(): UserType {
        return getValue(USER_TYPE_KEY) ?: UserType.NONE
    }

    fun setType(userMode: UserType?){
        return storeValue(USER_TYPE_KEY, userMode)
    }

    companion object {
        private const val USER_NAME_KEY = "USER_NAME_KEY"
        private const val USER_TYPE_KEY = "USER_MODE_KEY"

    }
}