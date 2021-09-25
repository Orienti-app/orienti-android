package app.orienti.android.models.models

import app.orienti.android.entities.UserType
import app.orienti.android.repositories.shared_preferences.UserSharedPreferences
import sk.backbone.parent.models.IModel

interface IUserModel : IModel<ModelsProvider> {
    val userSharedPreferences get() = UserSharedPreferences(context)

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

    fun getUserType(): UserType? {
        return userSharedPreferences.getType()
    }
}