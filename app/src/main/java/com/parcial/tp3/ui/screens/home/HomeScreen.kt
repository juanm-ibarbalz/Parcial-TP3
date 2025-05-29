package com.parcial.tp3.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.parcial.tp3.R
import androidx.compose.ui.zIndex
import com.parcial.tp3.Components.BestSellerSection
import com.parcial.tp3.Components.PromoBannerCard
import com.parcial.tp3.Components.TopIcons
import com.parcial.tp3.Components.CategorySection
import com.parcial.tp3.Components.LocationDisplay


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
        ) {
            PromoBannerCard(
                modifier = Modifier
                    .align(Alignment.CenterStart)
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

        CategorySection(

        )

        Spacer(modifier = Modifier.height(15.dp))

        BestSellerSection()
    }
}



