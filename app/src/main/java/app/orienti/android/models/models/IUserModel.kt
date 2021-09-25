package app.orienti.android.models.models

import app.orienti.android.repositories.shared_preferences.UserSharedPreferences
import sk.backbone.parent.models.IModel

interface IUserModel : IModel<ModelsProvider> {
    val userSharedPreferences get() = UserSharedPreferences(context)

    fun setUserName(userName: String?){
        userSharedPreferences.setUserName(userName)
    }

    fun getUserName(): String? {
        return userSharedPreferences.getUserName()
    }

    fun hasUserName(): Boolean {
        return getUserName() != null
    }
}