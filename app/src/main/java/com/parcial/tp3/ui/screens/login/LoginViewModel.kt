package com.parcial.tp3.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import com.parcial.tp3.data.remote.dto.LoginRequestDto
import com.parcial.tp3.domain.model.User
import com.parcial.tp3.shared.IAuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: IAuthService
) : ViewModel() {
    companion object {
        private const val TAG = "LoginViewModel"
    }


    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var emailTouched by mutableStateOf(false)
        private set

    var passwordTouched by mutableStateOf(false)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        internal set

    var loginSuccess by mutableStateOf(false)
        internal set

    var loggedUser by mutableStateOf<User?>(null)
        internal set

    fun onUsernameChange(newUsername: String) {
        username = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onEmailTouched() {
        emailTouched = true
    }

    fun onPasswordTouched() {
        passwordTouched = true
    }

    fun isFormValid(): Boolean {
        return username.isNotBlank() && password.isNotBlank()
    }

    fun login() {
        if (!isFormValid()) {
            emailTouched = true
            passwordTouched = true
            errorMessage = "Complete all fields"
            return
        }
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                val request = LoginRequestDto(username = username, password = password)
                Log.d(TAG, "LoginRequestDto está armado: $request")


                Log.d(TAG, "Enviando login con username='$username', password='$password'")
                val user: User = authService.login(request.username,request.password)
                loggedUser = user
                loginSuccess = true
            } catch (e: Exception) {
                errorMessage = "Error while logging in"
            } finally {
                isLoading = false
            }
        }
    }
}
