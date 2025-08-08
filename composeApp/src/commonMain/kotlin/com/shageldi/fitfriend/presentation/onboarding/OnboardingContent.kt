package com.shageldi.fitfriend.presentation.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.shageldi.fitfriend.language.LocalLyricist
import com.shageldi.fitfriend.presentation.onboarding.components.OnboardingPageContent
import com.shageldi.fitfriend.presentation.onboarding.components.onboardingItem
import com.shageldi.fitfriend.ui.theme.LocalAppColorSchema
import fitfriend.composeapp.generated.resources.Res
import kotlinx.coroutines.launch



@Composable
fun OnboardingContent(onFinish: () -> Unit) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { onboardingItem.size }
    )
    val scope = rememberCoroutineScope()

    val colorSchemeState = LocalAppColorSchema.current
    val strings = LocalLyricist.current.strings

    val currentPage = pagerState.currentPage
    val lastPageIndex = onboardingItem.lastIndex



    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage to pagerState.currentPageOffsetFraction }
            .collect { (page, offsetFraction) ->
                if (page == lastPageIndex && offsetFraction > 0.9f) {
                    onFinish()
                }
            }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(colorSchemeState.value.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { pageIndex ->
            val page = onboardingItem[pageIndex]
            val isLast = pageIndex == lastPageIndex
            OnboardingPageContent(
                page = page,
                showFinishButton = isLast,
                onFinish = if (isLast) onFinish else null
            )
        }
        Spacer(Modifier.height(36.dp))

        if (currentPage == 0 || currentPage == lastPageIndex) {
            Spacer(modifier = Modifier.height(12.dp))

            AnimatedVisibility(
                visible = currentPage == 0 || currentPage == lastPageIndex,
                enter = slideInVertically(
                    initialOffsetY = { it / 2 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it / 2 },
                    animationSpec = tween(
                        durationMillis = 400,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {
                    var isPressed by remember { mutableStateOf(false) }

                    val buttonScale by animateFloatAsState(
                        targetValue = if (isPressed) 0.95f else 1f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessHigh
                        ),
                        label = "buttonScale"
                    )

                    val buttonElevation by animateDpAsState(
                        targetValue = if (isPressed) 2.dp else 8.dp,
                        animationSpec = tween(150),
                        label = "buttonElevation"
                    )

                    Button(
                        onClick = {
                            if (currentPage == lastPageIndex) {
                                onFinish()
                            } else {
                                scope.launch { pagerState.animateScrollToPage(currentPage + 1) }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .scale(buttonScale)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = {
                                        isPressed = true
                                        tryAwaitRelease()
                                        isPressed = false
                                    }
                                )
                            },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorSchemeState.value.mainColor,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(28.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = buttonElevation
                        )
                    ) {
                        AnimatedContent(
                            targetState = if (currentPage == 0) strings.getStarted else strings.freeTrial,
                            transitionSpec = {
                                slideInHorizontally(
                                    initialOffsetX = { it },
                                    animationSpec = tween(300)
                                ) + fadeIn() togetherWith
                                        slideOutHorizontally(
                                            targetOffsetX = { -it },
                                            animationSpec = tween(300)
                                        ) + fadeOut()
                            },
                            label = "buttonText"
                        ) { text ->
                            Text(
                                text = text,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                )
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(36.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AnimatedVisibility(
                visible = currentPage in 1 until lastPageIndex,
                enter = slideInHorizontally(
                    initialOffsetX = { -it / 2 },
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(300)),
                exit = slideOutHorizontally(
                    targetOffsetX = { -it / 2 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(200))
            ) {
                var isPreviousPressed by remember { mutableStateOf(false) }
                val previousScale by animateFloatAsState(
                    targetValue = if (isPreviousPressed) 0.9f else 1f,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                    label = "previousScale"
                )

                TextButton(
                    onClick = {
                        scope.launch { pagerState.animateScrollToPage(currentPage - 1) }
                    },
                    modifier = Modifier
                        .scale(previousScale)
                        .size(48.dp)
                        .background(
                            color = colorSchemeState.value.mainColor.copy(alpha = 0.1f),
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = colorSchemeState.value.mainColor.copy(alpha = 0.3f),
                            shape = CircleShape
                        )
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    isPreviousPressed = true
                                    tryAwaitRelease()
                                    isPreviousPressed = false
                                }
                            )
                        },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = colorSchemeState.value.textColor
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val rippleAlpha by animateFloatAsState(
                            targetValue = if (isPreviousPressed) 0.2f else 0f,
                            animationSpec = tween(150),
                            label = "rippleAlpha"
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = colorSchemeState.value.mainColor.copy(alpha = rippleAlpha),
                                    shape = CircleShape
                                )
                        )

                        val iconRotation by animateFloatAsState(
                            targetValue = if (isPreviousPressed) -10f else 0f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessHigh
                            ),
                            label = "iconRotation"
                        )

                        AsyncImage(
                            model = Res.getUri("files/arrow_left.svg"),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .rotate(iconRotation),
                            colorFilter = ColorFilter.tint(color = colorSchemeState.value.textColor)
                        )
                    }
                }
            }

            if (currentPage in 1..2) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(32.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(4.dp)
                            .background(
                                color = colorSchemeState.value.textColor.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(2.dp)
                            )
                    )


                    val sliderOffset by animateDpAsState(
                        targetValue = if (currentPage == 1) (-20).dp else 20.dp,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMedium
                        ),
                        label = "sliderOffset"
                    )

                    Box(
                        modifier = Modifier
                            .offset(x = sliderOffset)
                            .width(40.dp)
                            .height(4.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        colorSchemeState.value.mainColor,
                                        colorSchemeState.value.mainColor.copy(alpha = 0.7f)
                                    )
                                ),
                                shape = RoundedCornerShape(2.dp)
                            )
                            .shadow(
                                elevation = 2.dp,
                                shape = RoundedCornerShape(2.dp)
                            )
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(onboardingItem.size - 1) { index ->
                            val dotIndex = index + 1
                            val isActive = dotIndex == currentPage

                            val dotScale by animateFloatAsState(
                                targetValue = if (isActive) 1.4f else 1f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessHigh
                                ),
                                label = "dotScale$index"
                            )

                            val dotAlpha by animateFloatAsState(
                                targetValue = if (isActive) 1f else 0.6f,
                                animationSpec = tween(200),
                                label = "dotAlpha$index"
                            )

                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .scale(dotScale)
                                    .background(
                                        color = colorSchemeState.value.mainColor.copy(alpha = dotAlpha),
                                        shape = CircleShape
                                    )
                                    .border(
                                        width = if (isActive) 2.dp else 0.dp,
                                        color = colorSchemeState.value.backgroundColor,
                                        shape = CircleShape
                                    )
                                    .shadow(
                                        elevation = if (isActive) 4.dp else 0.dp,
                                        shape = CircleShape
                                    )
                            )
                        }
                    }
                    val progressPercentage = ((currentPage.toFloat() / (onboardingItem.size - 1)) * 100).toInt()

                    Text(
                        text = "$progressPercentage%",
                        style = TextStyle(
                            color = colorSchemeState.value.textColor.copy(alpha = 0.5f),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = 16.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            AnimatedVisibility(
                visible = currentPage in 1 until lastPageIndex,
                enter = slideInHorizontally(
                    initialOffsetX = { it / 2 },
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(300)),
                exit = slideOutHorizontally(
                    targetOffsetX = { it / 2 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(200))
            ) {
                var isNextPressed by remember { mutableStateOf(false) }
                val nextScale by animateFloatAsState(
                    targetValue = if (isNextPressed) 0.9f else 1f,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                    label = "nextScale"
                )

                TextButton(
                    onClick = {
                        scope.launch { pagerState.animateScrollToPage(currentPage + 1) }
                    },
                    modifier = Modifier
                        .scale(nextScale)
                        .size(48.dp)
                        .background(
                            color = colorSchemeState.value.mainColor.copy(alpha = 0.1f),
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = colorSchemeState.value.mainColor.copy(alpha = 0.3f),
                            shape = CircleShape
                        )
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    isNextPressed = true
                                    tryAwaitRelease()
                                    isNextPressed = false
                                }
                            )
                        },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = colorSchemeState.value.textColor
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val rippleAlpha by animateFloatAsState(
                            targetValue = if (isNextPressed) 0.2f else 0f,
                            animationSpec = tween(150),
                            label = "rippleAlpha"
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = colorSchemeState.value.mainColor.copy(alpha = rippleAlpha),
                                    shape = CircleShape
                                )
                        )

                        val iconRotation by animateFloatAsState(
                            targetValue = if (isNextPressed) 10f else 0f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessHigh
                            ),
                            label = "iconRotation"
                        )

                        AsyncImage(
                            model = Res.getUri("files/arrow_right.svg"),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .rotate(iconRotation),
                            colorFilter = ColorFilter.tint(color = colorSchemeState.value.textColor)
                        )
                    }
                }
            }

            if (currentPage !in 1 until lastPageIndex) {
                Spacer(modifier = Modifier.width(80.dp))
            }
        }
        Spacer(modifier = Modifier.height(52.dp))
    }
}



