package com.parcial.tp3.data.remote.api

import com.parcial.tp3.data.remote.dto.LoginRequestDto
import com.parcial.tp3.data.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): LoginResponseDto
}
