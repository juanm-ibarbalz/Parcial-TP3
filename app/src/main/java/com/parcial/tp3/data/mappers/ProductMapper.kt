package com.parcial.tp3.data.mappers

import com.parcial.tp3.data.remote.dto.ProductDto
import com.parcial.tp3.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        thumbnail = thumbnail,
        category = category,
        description = description
    )
}

