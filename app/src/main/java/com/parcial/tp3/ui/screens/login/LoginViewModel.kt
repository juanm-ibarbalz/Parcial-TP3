package com.parcial.tp3.ui.screens.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parcial.tp3.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
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
                val response = authRepository.login(username, password)
                loginSuccess = true
            } catch (e: Exception) {
                errorMessage = "Error al iniciar sesión"
            } finally {
                isLoading = false
            }
        }
    }
}
