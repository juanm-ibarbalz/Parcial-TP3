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
        discountedTotal = discountedTotal,
        totalProducts = totalProducts,
        totalQuantity = totalQuantity
    )
}

fun CartItemDto.toDomain(): CartItem {
    return CartItem(
        id = id,
        title = title,
        price = price,
        quantity = quantity,
        total = total,
        discountPercentage = discountPercentage,
        discountedPrice = discountedPrice
    )
}
