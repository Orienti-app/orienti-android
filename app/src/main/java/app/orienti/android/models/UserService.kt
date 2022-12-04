package app.orienti.android.models

import app.orienti.android.entities.UserType
import app.orienti.android.entities.db_entities.User
import app.orienti.android.repositories.shared_preferences.UserSharedPreferences
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor() {
    @Inject lateinit var userSharedPreferences: UserSharedPreferences

    val currentUser: User get() = userSharedPreferences.getCurrentUser() ?: createUserIfNecessary()
    val currentUserId get() = currentUser.userId

    fun createUserIfNecessary() : User {
        var currentUser = userSharedPreferences.getCurrentUser()
        if(currentUser == null){
            currentUser = User()
            userSharedPreferences.setCurrentUser(currentUser)
        }
        return currentUser
    }

    fun setUserName(userName: String?){
        val user = currentUser
        user.name = userName

        userSharedPreferences.setCurrentUser(user)
    }

    fun getUserName(): String? {
        return currentUser.name
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