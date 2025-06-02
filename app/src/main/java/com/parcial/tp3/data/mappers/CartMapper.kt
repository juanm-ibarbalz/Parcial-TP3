package com.parcial.tp3.data.mappers

import com.parcial.tp3.data.remote.dto.CartDto
import com.parcial.tp3.data.remote.dto.CartItemDto
import com.parcial.tp3.domain.model.Cart
import com.parcial.tp3.domain.model.CartItem

fun CartDto.toDomain(): Cart {
    return Cart(
        id = id,
        userId = userId,
        products = products.map { it.toDomain() },
        total = total,
        totalProducts = totalProducts,
        totalQuantity = totalQuantity
    )
}

fun CartItemDto.toDomain(): CartItem {
    return CartItem(
        productId = id,
        title = title,
        price = price,
        quantity = quantity,
        total = total,
        thumbnail = thumbnail
    )
}
