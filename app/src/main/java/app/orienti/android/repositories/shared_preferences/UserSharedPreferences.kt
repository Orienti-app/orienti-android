package app.orienti.android.repositories.shared_preferences

import android.content.Context
import sk.backbone.parent.repositories.shared_preferences.SharedPreferencesDataProvider

class UserSharedPreferences(context: Context) : SharedPreferencesDataProvider("USER_SHARED_PREFERENCES", context) {
    fun getUserName(): String? {
        return getValue(USER_NAME_KEY)
    }

    fun setUserName(userName: String?){
        return storeValue(USER_NAME_KEY, userName)
    }

    companion object {
        private const val USER_NAME_KEY = "USER_NAME_KEY"
    }
}