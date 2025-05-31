package com.parcial.tp3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parcial.tp3.R
import com.parcial.tp3.ui.theme.MediumGrey
import com.parcial.tp3.ui.theme.PureBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDisplay() {
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(modifier = Modifier.clickable { showBottomSheet = true }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Location",
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Change location",
                tint = MediumGrey
            )
        }

        Text(
            text = "Jebres, Surakarta",
            style = MaterialTheme.typography.bodyLarge,
            color = PureBlack
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = { showBottomSheet = false },
            modifier = Modifier.fillMaxHeight(0.92f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .background(Color.LightGray, RoundedCornerShape(2.dp))
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Location",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search your Location") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start // esto lo lleva a la izquierda
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Location Pin",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Track your Location", fontWeight = FontWeight.Medium)
                        Text(
                            "automatically selects your\ncurrent location",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }


                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}