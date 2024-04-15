package com.example.chipedia.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.chipedia.module.MenuItems
import com.example.chipedia.module.SubMenuItem


@Composable
fun MenuScreen(navController: NavHostController) {
    val menuItems = MenuItems.getData()
    LazyColumn(modifier = Modifier.fillMaxSize().background(com.example.chipedia.ui.theme.colorBlue)) {
        items(menuItems) { menuItem ->
            MenuItemCard(menuItem = menuItem, navController = navController)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { navController.navigate("addFish") },
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Fish")
        }
    }
}


@Composable
fun MenuItemCard(menuItem: MenuItems, navController: NavHostController) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isExpanded = !isExpanded }
    ) {
        Column(
//            modifier = Modifier.padding(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = menuItem.image),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = stringResource(id = menuItem.title),
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 40.sp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp),
                    )
                }
            }
            if (isExpanded) {
                SubMenuList(subItems = menuItem.subItems, navController = navController)
            }
        }
    }
}


@Composable
fun MenuItemImage(painter: Painter) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.size(50.dp)
    )
}

@Composable
fun SubMenuList(subItems: List<SubMenuItem>, navController: NavHostController) {
    Column {
        subItems.forEach { subItem ->
            SubMenuItemCard(subItem, navController)
        }
    }
}

@Composable
fun SubMenuItemCard(subItem: SubMenuItem, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Navigate to HeheScreen passing the value of the clicked SubMenuItem
                navController.navigate("showList/${subItem.value}")
            }
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = subItem.image),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = stringResource(id = subItem.title),
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp),
                    )
                }
            }
        }
    }
}
