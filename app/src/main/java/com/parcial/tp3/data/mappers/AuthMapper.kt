package com.parcial.tp3.data.mappers

import com.parcial.tp3.data.remote.dto.LoginResponseDto
import com.parcial.tp3.domain.model.User

fun LoginResponseDto.toDomain(): User {
    return User(
        id = id,
        username = username,
        email = email,
        fullName = "$firstName $lastName",
        token = token,
        image = image
    )
}