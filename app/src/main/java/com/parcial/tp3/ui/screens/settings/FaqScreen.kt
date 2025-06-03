package com.parcial.tp3.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.parcial.tp3.R
import com.parcial.tp3.ui.components.NotificationHeader

@Composable
fun FaqScreen(
    navController: NavHostController
) {
    val faqItems = List(5) { "Security" }
    val placeholderText =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum sapien sodales mi sagittis hendrerit. Curabitur ut lectus nec orci cursus rhoncus."
    val expandedItems = remember { mutableStateListOf(true, false, false, false, false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        NotificationHeader(
            navController = navController,
            title = "FAQ"
        )

        Spacer(modifier = Modifier.height(16.dp))

        faqItems.forEachIndexed { index, title ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedItems[index] = !expandedItems[index] },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )
                    Icon(
                        painter = painterResource(
                            id = if (expandedItems[index])
                                R.drawable.ic_arrow_up
                            else
                                R.drawable.ic_arrow_down
                        ),
                        contentDescription = if (expandedItems[index]) "Collapse" else "Expand",
                        modifier = Modifier.size(28.dp)
                    )
                }

                if (expandedItems[index]) {
                    Text(
                        text = placeholderText,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
