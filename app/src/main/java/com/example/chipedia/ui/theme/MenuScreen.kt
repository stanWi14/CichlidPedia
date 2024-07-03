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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.chipedia.module.MenuItems
import com.example.chipedia.module.SubMenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf<BottomNavItem>(BottomNavItem.News) }

    Scaffold(
        bottomBar = { BottomNavigationBar(selectedTab, onTabSelected = { selectedTab = it }) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                is BottomNavItem.News -> NewsScreen()
                is BottomNavItem.List -> ListScreen(navController)
                is BottomNavItem.AboutUs -> AboutUsScreen()
            }
        }
    }
}


@Composable
fun NewsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "News Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ListScreen(navController: NavHostController) {
    val menuItems = MenuItems.getData()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(com.example.chipedia.ui.theme.colorBlue)
    ) {
        items(menuItems) { menuItem ->
            MenuItemCard(menuItem = menuItem, navController = navController)
        }
    }
}

@Composable
fun AboutUsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "About Us Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
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
        Column {
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
    Column(
        modifier = Modifier.background(com.example.chipedia.ui.theme.colorBlue)
    ) {
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

@Composable
fun BottomNavigationBar(
    selectedTab: BottomNavItem,
    onTabSelected: (BottomNavItem) -> Unit
) {
    val items = listOf(
        BottomNavItem.News,
        BottomNavItem.List,
        BottomNavItem.AboutUs
    )
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedTab == item,
                onClick = { onTabSelected(item) }
            )
        }
    }
}

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object News : BottomNavItem("News", Icons.Default.Home, "news")
    object List : BottomNavItem("List", Icons.Default.List, "list")
    object AboutUs : BottomNavItem("About Us", Icons.Default.Info, "about_us")
}
