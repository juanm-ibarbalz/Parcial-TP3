package com.parcial.tp3.data.service

import com.parcial.tp3.data.remote.api.AuthApiService
import com.parcial.tp3.data.remote.dto.LoginRequestDto
import com.parcial.tp3.data.mappers.toDomain
import com.parcial.tp3.domain.model.User
import com.parcial.tp3.shared.IAuthService
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val api: AuthApiService
) : IAuthService {

    override suspend fun login(username: String, password: String): User {

        // NOTA:
        // Usamos directamente el DTO en lugar de Response<LoginResponseDto> porque dummyjson.com
        // lanza HttpException automáticamente si falla. Por eso no usamos response.isSuccessful,
        // y manejamos el error desde el ViewModel que hace el try-catch.
        // Vamos a tomar la certeza de que la response va a ser correcta con el fin de simplificar el código
        // y evitar desarrollar cosas que no vamos a necesitar por ahora.
        // Por lo que no haria falta recibir un Response<LoginResponseDto>.
        // Con el DTO deberia ser suficiente por ahora.

        val dto = api.login(LoginRequestDto(username, password))
        return dto.toDomain()
    }
}
