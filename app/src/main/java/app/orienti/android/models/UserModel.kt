package app.orienti.android.models

import app.orienti.android.entities.UserType
import app.orienti.android.repositories.shared_preferences.UserSharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserModel @Inject constructor() {
    @Inject lateinit var userSharedPreferences: UserSharedPreferences

    fun setUserName(userName: String?){
        userSharedPreferences.setName(userName)
    }

    fun getUserName(): String? {
        return userSharedPreferences.getName()
    }

    fun hasUserName(): Boolean {
        return getUserName() != null
    }

    fun setUserType(userType: UserType) {
        userSharedPreferences.setType(userType)
    }

    fun getUserType(): UserType {
        return userSharedPreferences.getType()
    }
}