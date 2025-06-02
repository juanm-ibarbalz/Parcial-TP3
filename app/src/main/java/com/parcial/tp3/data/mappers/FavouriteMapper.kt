package com.parcial.tp3.data.mappers

import com.parcial.tp3.data.local.entity.FavouriteEntity
import com.parcial.tp3.domain.model.Product

fun FavouriteEntity.toDomain(): Product {
    return Product(
        id = id,
        title = name,
        price = price,
        thumbnail = image,
        category = category,
        description = description
    )
}

fun Product.toEntity(): FavouriteEntity {
    return FavouriteEntity(
        id = id,
        name = title,
        price = price,
        image = thumbnail,
        category = category,
        description = description
    )
}
