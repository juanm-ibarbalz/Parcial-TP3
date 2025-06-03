// app/src/main/java/com/parcial/tp3/ui/screens/profile/ProfileScreen.kt

package com.parcial.tp3.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.parcial.tp3.data.session.UserSession
import com.parcial.tp3.domain.model.Product   // ← Importa el Product de dominio

@Composable
fun ProfileScreen() {
    // 1. Obtenemos el usuario actual desde UserSession
    val currentUserState by UserSession.currentUser.collectAsState()

    // 2. Si no hay usuario (null), mostramos un indicador de carga
    if (currentUserState == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    // 3. Guardamos en variable no anulable
    val user = currentUserState!!

    // 4. Estado para alternar entre modo “Profile” o “Seller Mode”
    var isSellerMode by remember { mutableStateOf(false) }

    // 5. Lista de productos estáticos (solo visual, del ViewModel)
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val sampleProducts by profileViewModel.myProducts.collectAsState(initial = emptyList())

    // Cada vez que cambiamos a “Seller Mode”, cargamos productos
    LaunchedEffect(isSellerMode) {
        if (isSellerMode) {
            profileViewModel.loadMyProducts(user.id)
        }
    }

    Scaffold(
        topBar = {
            TabRow(
                selectedTabIndex = if (isSellerMode) 1 else 0,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Tab(
                    selected = !isSellerMode,
                    onClick = { isSellerMode = false },
                    text = { Text(text = "Profile") }
                )
                Tab(
                    selected = isSellerMode,
                    onClick = { isSellerMode = true },
                    text = { Text(text = "Seller Mode") }
                )
            }
        },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // 6. Encabezado con cover y avatar usando datos de usuario real
            ProfileHeader(user = user)

            Spacer(modifier = Modifier.height(24.dp))

            if (!isSellerMode) {
                // 7a. Vista “Profile” con datos reales
                ProfileContent(user = user)
            } else {
                // 7b. Vista “Seller Mode” con lista de products de dominio
                SellerContent(products = sampleProducts)
            }
        }
    }
}

@Composable
private fun ProfileHeader(user: com.parcial.tp3.data.session.UserData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(color = Color.LightGray),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            val painter = rememberAsyncImagePainter(model = user.avatarUrl ?: "")
            Image(
                painter = painter,
                contentDescription = "Avatar de ${user.name}",
                modifier = Modifier
                    .size(72.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun ProfileContent(user: com.parcial.tp3.data.session.UserData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = user.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = user.email,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${user.followers}", fontWeight = FontWeight.Bold)
                Text(text = "Followers", fontSize = 12.sp, color = Color.Gray)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${user.following}", fontWeight = FontWeight.Bold)
                Text(text = "Following", fontSize = 12.sp, color = Color.Gray)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${user.sales}", fontWeight = FontWeight.Bold)
                Text(text = "Sales", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = { /* Sin funcionalidad */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Saved")
            }
            Spacer(modifier = Modifier.width(12.dp))
            TextButton(onClick = { /* Sin funcionalidad */ }) {
                Text(text = "Edit Profile")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            sampleCard(
                imageUrl = "https://dummyimage.com/200x200/ffb6c1/ffffff&text=Kitten",
                title = "RC Kitten",
                price = "$20,99"
            )
            sampleCard(
                imageUrl = "https://dummyimage.com/200x200/ffe4e1/ffffff&text=Persian",
                title = "RC Persian",
                price = "$22,99"
            )
        }
    }
}

@Composable
private fun sampleCard(imageUrl: String, title: String, price: String) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            val painter = rememberAsyncImagePainter(model = imageUrl)
            Image(
                painter = painter,
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = price, fontWeight = FontWeight.Bold)
            Text(
                text = "+",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { /* Sin funcionalidad */ }
            )
        }
    }
}

@Composable
private fun SellerContent(products: List<Product>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { /* Sin funcionalidad */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        ) {
            Text(text = "+ Add Product")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(products) { product ->
                ProductSellerItem(product = product)
            }
        }
    }
}

@Composable
private fun ProductSellerItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray)
            ) {
                val painter = rememberAsyncImagePainter(model = product.thumbnail)
                Image(
                    painter = painter,
                    contentDescription = product.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.title, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "$${product.price}", color = Color.Gray)
            }
            Text(
                text = "+",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { /* Sin funcionalidad */ }
            )
        }
    }
}
