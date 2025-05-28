package com.parcial.tp3.data.mappers

import com.parcial.tp3.data.local.entity.FavouriteEntity
import com.parcial.tp3.domain.model.ProductPreview

fun ProductPreview.toEntity(): FavouriteEntity {
    return FavouriteEntity(
        id = id,
        name = name,
        price = price,
        image = image
    )
}

fun FavouriteEntity.toDomain(): ProductPreview {
    return ProductPreview(
        id = id,
        name = name,
        price = price,
        image = image
    )
}
