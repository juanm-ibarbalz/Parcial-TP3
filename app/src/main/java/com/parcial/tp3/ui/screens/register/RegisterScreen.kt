// app/src/main/java/com/parcial/tp3/ui/screens/register/RegisterScreen.kt

package com.parcial.tp3.ui.screens.register

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.parcial.tp3.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var usernameTouched by remember { mutableStateOf(false) }
    var emailTouched by remember { mutableStateOf(false) }
    var passwordTouched by remember { mutableStateOf(false) }

    val isUsernameValid = remember(username) { username.trim().length > 4 }
    val isEmailValid = remember(email) { Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() }
    val passwordRegex = remember {
        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9\\W]).{8,}\$")
    }
    val isPasswordValid = remember(password) { passwordRegex.matches(password) }
    val isFormValid = isUsernameValid && isEmailValid && isPasswordValid

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(paddingValues)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Create New Account",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Welcome! Please fill the information below to create a new account.",
                fontSize = 14.sp,
                color = Color(0xFF777777),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Campo: Username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) usernameTouched = true
                    },
                label = { Text("Username") },
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = true,
                isError = usernameTouched && !isUsernameValid,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { emailTouched = true }
                )
                // Sin parámetros de colores personalizados
            )
            if (usernameTouched && !isUsernameValid) {
                Text(
                    text = "El usuario debe tener más de 4 caracteres",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 4.dp)
                        .align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo: Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) emailTouched = true
                    },
                label = { Text("Email") },
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = true,
                isError = emailTouched && !isEmailValid,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { passwordTouched = true }
                )
                // Sin parámetros de colores personalizados
            )
            if (emailTouched && !isEmailValid) {
                Text(
                    text = "Formato de email inválido",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 4.dp)
                        .align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo: Password
            var passwordVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) passwordTouched = true
                    },
                label = { Text("Password") },
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = true,
                isError = passwordTouched && !isPasswordValid,
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(onClick = { passwordVisible = !passwordVisible }) {
                        Text(
                            text = if (passwordVisible) "Ocultar" else "Mostrar",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { passwordTouched = true }
                )
                // Sin parámetros de colores personalizados
            )
            if (passwordTouched && !isPasswordValid) {
                Text(
                    text = "La contraseña debe tener al menos 8 caracteres,\n1 mayúscula, 1 minúscula y 1 número o carácter especial",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 4.dp)
                        .align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón “Get Started”
            Button(
                onClick = {
                    if (!isFormValid) {
                        usernameTouched = true
                        emailTouched = true
                        passwordTouched = true
                        return@Button
                    }
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Cuenta creada")
                    }
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = isFormValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid)
                        MaterialTheme.colorScheme.primary
                    else
                        Color(0xFFD0D0D0),
                    contentColor = if (isFormValid)
                        Color.White
                    else
                        Color(0xFF888888)
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto para volver a Login
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Have an account? ",
                    fontSize = 14.sp,
                    color = Color(0xFF777777)
                )
                Text(
                    text = "Login",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
