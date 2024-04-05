package com.example.chipedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chipedia.ui.theme.OnBoarding
import com.google.accompanist.pager.ExperimentalPagerApi


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
            OnBoarding()
        }
    }
}