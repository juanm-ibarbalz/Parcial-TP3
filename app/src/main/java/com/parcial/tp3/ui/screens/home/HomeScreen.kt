package com.parcial.tp3.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.parcial.tp3.components.LocationDisplay
import com.parcial.tp3.components.TopIcons
import com.parcial.tp3.components.PromoBannerCard
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.parcial.tp3.R
import androidx.compose.ui.zIndex
import com.parcial.tp3.components.BestSellerSection
import com.parcial.tp3.components.CategorySection



@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            LocationDisplay()
            TopIcons(
                onSearchClick = { /* TODO */ },
                onNotificationClick = { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(bottom = 8.dp)
        ) {
            PromoBannerCard(
                modifier = Modifier.offset(y = 45.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.comida_card),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterStart)
                    .offset(x = (-25).dp, y = (-30).dp)
                    .zIndex(0f),
                contentScale = ContentScale.Fit
            )

            Image(
                painter = painterResource(id = R.drawable.comida_card),
                contentDescription = null,
                modifier = Modifier
                    .size(185.dp)
                    .align(Alignment.CenterStart)
                    .offset(x = 15.dp, y = 20.dp)
                    .zIndex(1f),
                contentScale = ContentScale.Fit
            )
        }

        CategorySection()

        Spacer(modifier = Modifier.height(15.dp))

        BestSellerSection()
    }
}

