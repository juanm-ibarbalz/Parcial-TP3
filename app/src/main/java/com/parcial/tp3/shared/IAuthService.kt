package com.parcial.tp3.shared

import com.parcial.tp3.domain.model.User

interface IAuthService {
    suspend fun login(username: String, password: String): User
}
