package com.example.chipedia.ui.theme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chipedia.module.OnBoardingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

val colorTransparent = Color.Transparent
val colorWhite = Color.White

val colorRed = Color(0xFFd2261f)

//val colorRed = Color.Red
val colorBlue = Color(0xFF013d47)
val coloeBlack = Color.Black
val darkRedColor = Color(0xFF690505)

@ExperimentalPagerApi
@Composable
fun OnBoarding(navController: NavController) {
    val items = OnBoardingItems.getData()
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = items.size,
            state = pageState,
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth()
        ) { page ->
            OnBoardingItem(items = items[page])
        }
        BottomSection(
            size = items.size,
            index = pageState.currentPage,
            navController = navController
        ) {
            if (pageState.currentPage + 1 < items.size) scope.launch {
                pageState.scrollToPage(pageState.currentPage + 1)
            }
        }
    }
}

@Composable
fun BottomSection(
    size: Int,
    index: Int,
    navController: NavController,
    onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorRed)
            .padding(12.dp)
    ) {
        // Indicators
        Indicators(size, index)

        // Next Button
        FloatingActionButton(
            onClick = {
                if (index + 1 < size) {
                    onButtonClick()
                } else {
                    navController.navigate("menuScreen")
                }
            },
            contentColor = colorWhite,
            containerColor = colorBlue,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "Next")
        }
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) colorBlue else Color(0XFFF8E2E7)
            )
    ) {

    }
}

@Composable

fun OnBoardingItem(items: OnBoardingItems) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            painter = painterResource(id = items.image),
            contentDescription = "Image1",
            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.FillBounds
            contentScale = ContentScale.Crop
        )
        val gradientRedWhite = Brush.verticalGradient(0f to colorTransparent, 1000f to colorRed)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(
                    gradientRedWhite
                )
                .padding(5.dp)
        ) {
            Text(
                text = stringResource(id = items.title),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 40.sp),
                color = colorWhite,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 8.dp),
                letterSpacing = 1.sp,
            )
            Text(
                text = stringResource(id = items.subtitle),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 25.sp),
                color = colorWhite,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                letterSpacing = 1.sp,
            )

            Text(
                text = stringResource(id = items.desc),
                style = MaterialTheme.typography.bodyLarge,
                color = colorWhite,
                fontWeight = FontWeight.Light,
                letterSpacing = 1.sp,
            )
        }
    }
}