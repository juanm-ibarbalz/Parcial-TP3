// app/src/main/java/com/parcial/tp3/data/session/UserSession.kt
package com.parcial.tp3.data.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class UserData(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String?,
    val followers: Int,
    val following: Int,
    val sales: Int
)

object UserSession {
    private val _currentUser = MutableStateFlow<UserData?>(null)
    val currentUser: StateFlow<UserData?> = _currentUser

    fun login(user: UserData) {
        _currentUser.value = user
    }

    fun logout() {
        _currentUser.value = null
    }
}
