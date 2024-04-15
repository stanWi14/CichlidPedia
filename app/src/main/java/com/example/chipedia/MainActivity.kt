package com.example.chipedia

import ListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chipedia.ui.theme.AddFish
import com.example.chipedia.ui.theme.MenuScreen
import com.example.chipedia.ui.theme.OnBoarding
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "onBoardingScreen") {
        composable("onBoardingScreen") {
            OnBoarding(navController)
        }
        composable("addFish") {
            AddFish(navController)
        }
        composable("menuScreen") {
            MenuScreen(navController)
        }
        composable(
            route = "showList/{value}",
            arguments = listOf(navArgument("value") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val value = navBackStackEntry.arguments?.getInt("value") ?: 0
            ListScreen(value = value, navController)
        }
    }
}

@Composable
fun Material3ComposeTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PreviewFunction()
                }
            }
        }
    }

    @ExperimentalPagerApi
    @Preview(showBackground = true)
    @Composable
    fun PreviewFunction() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Navigation()
        }
    }
}