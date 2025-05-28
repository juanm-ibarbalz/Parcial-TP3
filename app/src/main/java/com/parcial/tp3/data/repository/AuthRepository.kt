package com.parcial.tp3.data.repository

import com.parcial.tp3.data.remote.api.AuthApiService
import com.parcial.tp3.data.remote.dto.LoginRequestDto
import com.parcial.tp3.data.remote.dto.LoginResponseDto

class AuthRepository(
    private val api: AuthApiService
) {
    suspend fun login(username: String, password: String): LoginResponseDto {
        val request = LoginRequestDto(username, password)
        return api.login(request)
    }
}