package com.parcial.tp3.ui.screens.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parcial.tp3.domain.model.User
import com.parcial.tp3.shared.IAuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: IAuthService
) : ViewModel() {

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    var loggedUser by mutableStateOf<User?>(null)
        private set

    fun onUsernameChange(newUsername: String) {
        username = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun login() {
        isLoading = true
        errorMessage = null
        viewModelScope.launch {
            try {
                val user = authService.login(username, password)
                loggedUser = user
                loginSuccess = true
            } catch (e: Exception) {
                errorMessage = "Error al iniciar sesión"
            } finally {
                isLoading = false
            }
        }
    }
}
