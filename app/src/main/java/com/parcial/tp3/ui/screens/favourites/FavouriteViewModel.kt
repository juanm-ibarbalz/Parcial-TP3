package com.parcial.tp3.ui.screens.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parcial.tp3.domain.model.Product
import com.parcial.tp3.shared.IFavouriteService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val favouriteService: IFavouriteService
) : ViewModel() {

    val favourites = favouriteService.getAllFavourites() // StateFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addToFavourites(product: Product) {
        viewModelScope.launch {
            favouriteService.addToFavourites(product)
        }
    }

    fun removeFromFavourites(product: Product) {
        viewModelScope.launch {
            favouriteService.removeFromFavourites(product)
        }
    }

    suspend fun isFavourite(productId: Int): Boolean {
        return favouriteService.isFavourite(productId)
    }
}