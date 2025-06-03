// app/src/main/java/com/parcial/tp3/ui/screens/login/LoginViewModel.kt

package com.parcial.tp3.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parcial.tp3.data.remote.dto.LoginRequestDto
import com.parcial.tp3.data.session.UserData
import com.parcial.tp3.data.session.UserSession
import com.parcial.tp3.domain.model.User
import com.parcial.tp3.shared.IAuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: IAuthService
) : ViewModel() {

    // 1) Campos de texto para usuario y contraseña
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    // 2) Flags que indican si el usuario “tocó” el campo (para mostrar error)
    var usernameTouched by mutableStateOf(false)
        private set
    var passwordTouched by mutableStateOf(false)
        private set

    // 3) Indicador de carga (cuando estamos haciendo la petición al backend)
    var isLoading by mutableStateOf(false)
        private set

    // 4) Mensaje de error para mostrar al usuario (por ejemplo, credenciales inválidas)
    var errorMessage by mutableStateOf<String?>(null)

    // 5) Usuario resultante del login (nullable hasta que realmente haya hecho login)
    var loggedUser by mutableStateOf<User?>(null)
        private set

    // 6) Flag de “login exitoso”, para saber si procedemos a navegar o lo que sea
    var loginSuccess by mutableStateOf(false)
        private set

    // ***********************
    // Métodos públicos que actualizan esos estados:
    // ***********************

    fun onUsernameChanged(newUsername: String) {
        username = newUsername
    }

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
    }

    fun onUsernameFocusLost() {
        usernameTouched = true
    }

    fun onPasswordFocusLost() {
        passwordTouched = true
    }

    // ***********************
    // Validación mínima: usuario y contraseña no pueden estar vacíos
    // ***********************
    private fun isFormValid(): Boolean {
        return username.isNotBlank() && password.isNotBlank()
    }

    // ***********************
    // Lógica de login
    // ***********************
    fun login() {
        // 1) Validamos primero que los campos no estén vacíos
        if (!isFormValid()) {
            // Si al menos uno está vacío, marcamos “touched” para mostrar mensajes de error
            usernameTouched = true
            passwordTouched = true
            errorMessage = "Complete all fields"
            return
        }

        // 2) Empezamos la carga
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                // 3) Llamamos al servicio de autenticación pasándole username y password
                val user: User = authService.login(username, password)

                // 4) Si no hubo excepción, guardamos el usuario en loggedUser y marcamos éxito
                loggedUser = user
                loginSuccess = true

                // 5) Actualizamos el UserSession con los datos del usuario ya retornado
                UserSession.login(
                    UserData(
                        id = user.id.toString(),
                        name = user.username,
                        email = user.email,
                        avatarUrl = user.image,
                        followers = 0,   // Si no tienes estos números reales, pon 0
                        following = 0,
                        sales = 0
                    )
                )

                // 6) (Opcional) aquí podrías emitir un evento o cambiar otro estado
                // que tu Composable observe para, por ejemplo, navegar a la pantalla siguiente.

            } catch (e: Exception) {
                // 7) Si la llamada al backend falla (credenciales incorrectas, error de red, etc.)
                errorMessage = "Error while logging in"
            } finally {
                // 8) Detenemos el indicador de carga
                isLoading = false
            }
        }
    }
}
