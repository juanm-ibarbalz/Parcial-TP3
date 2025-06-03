// app/src/main/java/com/parcial/tp3/ui/screens/login/LoginScreen.kt

package com.parcial.tp3.ui.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.parcial.tp3.navigation.Screen
import kotlinx.coroutines.launch
import com.parcial.tp3.ui.screens.login.LoginViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    // 1) Ligamos los estados del ViewModel
    val username by viewModel::username
    val password by viewModel::password
    val isLoading by viewModel::isLoading
    val errorMessage by viewModel::errorMessage
    val loginSuccess by viewModel::loginSuccess

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    // --- Cuando loginSuccess cambie a true, navegamos a Home y limpiamos el backstack de Login ---
    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Hello, Welcome Back!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Welcome back! Please enter your details.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(32.dp))

            // ——— Campo "Email" / Username ———
            OutlinedTextField(
                value = username,
                onValueChange = { viewModel.onUsernameChanged(it) }, // ← se llama onUsernameChanged
                label = { Text(text = "Email") },
                placeholder = { Text(text = "example@domain.com") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = viewModel.usernameTouched && username.isBlank(), // ← usernameTouched en lugar de emailTouched
                modifier = Modifier.fillMaxWidth()
            )
            if (viewModel.usernameTouched && username.isBlank()) {
                Text(
                    text = "This field is required",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp, top = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // ——— Campo "Password" ———
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.onPasswordChanged(it) }, // ← se llama onPasswordChanged
                label = { Text(text = "Password") },
                placeholder = { Text(text = "••••••••") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        viewModel.onPasswordFocusLost() // ← se llama onPasswordFocusLost
                        if (username.isBlank() || password.isBlank()) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Complete all fields")
                            }
                        } else {
                            viewModel.login()
                        }
                    }
                ),
                isError = viewModel.passwordTouched && password.isBlank(), // ← passwordTouched está bien
                modifier = Modifier.fillMaxWidth()
            )
            if (viewModel.passwordTouched && password.isBlank()) {
                Text(
                    text = "This field is required",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp, top = 4.dp)
                )
            }

            // —— Debug (opcional) — mostrar en pantalla lo que se envía —
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "DEBUG: Enviando -> user='$username', pass='$password'",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ——— Botón “Get Started” ———
            Button(
                onClick = {
                    focusManager.clearFocus()
                    viewModel.onUsernameFocusLost()    // ← se llama onUsernameFocusLost
                    viewModel.onPasswordFocusLost()    // ← se llama onPasswordFocusLost
                    if (username.isBlank() || password.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Complete all fields")
                        }
                    } else {
                        viewModel.login()
                    }
                },
                enabled = username.isNotBlank() && password.isNotBlank() && !isLoading,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (username.isNotBlank() && password.isNotBlank() && !isLoading)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
                    contentColor = Color.White
                )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(text = "Get Started", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ——— Mostrar mensaje de error (login fallido) ———
            errorMessage?.let { msg ->
                LaunchedEffect(msg) {
                    scope.launch {
                        snackbarHostState.showSnackbar(msg)
                        viewModel.errorMessage = null // Limpiamos mensaje luego de mostrarlo
                    }
                }
            }
            SnackbarHost(hostState = snackbarHostState)

            Spacer(modifier = Modifier.height(16.dp))

            // ——— Texto “Don’t have an account? Register” ———
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don’t have an account? ",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
                Text(
                    text = "Register",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Register.route)
                    }
                )
            }
        }
    }
}
