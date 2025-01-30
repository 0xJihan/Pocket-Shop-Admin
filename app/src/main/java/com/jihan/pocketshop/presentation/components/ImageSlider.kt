package com.jihan.pocketshop.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue


@Composable
fun ImageSlider(
    imageUrls: List<String>,
    modifier: Modifier = Modifier,
    delayTime: Long = 3000,

) {
    val pagerState = rememberPagerState(initialPage = 0) { imageUrls.size }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(delayTime)
            // Add smoother animation with custom duration and easing
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % pagerState.pageCount,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }

    Column {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
                .height(114.dp)
                .fillMaxWidth()
        ) { page ->
            val painter = rememberAsyncImagePainter(
                model = imageUrls[page]
            )

            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                )
            }
        }

        Indicators(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            count = imageUrls.size,
            size = 8,
            spacer = 4,
            selectedIndex = pagerState.currentPage,
            selectedColor = MaterialTheme.colorScheme.primary,
            unselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            indicatorSelectedLength = 30
        ){
            scope.launch {
                pagerState.animateScrollToPage(it)
            }
        }
    }
}

