package com.shageldi.fitfriend.presentation.onboarding.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shageldi.fitfriend.language.LocalLyricist
import com.shageldi.fitfriend.ui.theme.LocalAppColorSchema
import com.shageldi.fitfriend.ui.theme.appDarkColorSchema
import com.shageldi.fitfriend.ui.theme.appLightColorSchema
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardingPageContent(
    page: OnboardingPage,
    showFinishButton: Boolean = false,
    onFinish: (() -> Unit)? = null
) {
    val colorSchemeState = LocalAppColorSchema.current
    val strings = LocalLyricist.current.strings

    var isVisible by remember { mutableStateOf(false) }

    val imageScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 1.2f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = FastOutSlowInEasing
        ),
        label = "imageScale"
    )

    val imageAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            easing = FastOutSlowInEasing
        ),
        label = "imageAlpha"
    )


    val imageBlur by animateFloatAsState(
        targetValue = if (isVisible) 0f else 20f,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 200,
            easing = FastOutSlowInEasing
        ),
        label = "imageBlur"
    )


    val cornerRadius by animateDpAsState(
        targetValue = if (isVisible) 24.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 400,
            easing = FastOutSlowInEasing
        ),
        label = "cornerRadius"
    )

    val gradientAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 600,
            easing = FastOutSlowInEasing
        ),
        label = "gradientAlpha"
    )

    val contentOffset by animateDpAsState(
        targetValue = if (isVisible) 0.dp else 80.dp,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 800,
            easing = FastOutSlowInEasing
        ),
        label = "contentOffset"
    )

    val contentAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 700,
            delayMillis = 900,
            easing = FastOutSlowInEasing
        ),
        label = "contentAlpha"
    )


    val shimmerAlpha by animateFloatAsState(
        targetValue = if (isVisible) 0f else 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmerAlpha"
    )


    LaunchedEffect(Unit) {
        delay(100)
        isVisible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorSchemeState.value.backgroundColor)
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(page.imageResId),
                contentDescription = page.title,
                modifier = Modifier
                    .fillMaxSize()
                    .scale(imageScale)
                    .alpha(imageAlpha)
                    .clip(RoundedCornerShape(cornerRadius))
                    .blur(
                        radius = imageBlur.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    ),
                contentScale = ContentScale.Crop
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(shimmerAlpha)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.3f),
                                Color.Transparent
                            ),
                            start = Offset.Zero,
                            end = Offset.Infinite
                        )
                    )
                    .clip(RoundedCornerShape(cornerRadius))
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(imageAlpha)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.2f)
                            ),
                            radius = 800f
                        )
                    )
                    .clip(RoundedCornerShape(cornerRadius))
            )
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(gradientAlpha)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                colorSchemeState.value.backgroundColor.copy(alpha = 0.3f),
                                colorSchemeState.value.backgroundColor.copy(alpha = 0.8f),
                                colorSchemeState.value.backgroundColor
                            ),
                            startY = 200f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color.Transparent,
                                colorSchemeState.value.backgroundColor.copy(alpha = 0.1f)
                            ),
                            radius = 600f,
                            center = Offset(0.5f, 0.8f)
                        )
                    )
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 32.dp, vertical = 48.dp)
                .offset(y = contentOffset)
                .alpha(contentAlpha),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { it / 2 },
                    animationSpec = tween(
                        durationMillis = 700,
                        delayMillis = 1000,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(
                    animationSpec = tween(
                        durationMillis = 700,
                        delayMillis = 1000
                    )
                ) + scaleIn(
                    initialScale = 0.8f,
                    animationSpec = tween(
                        durationMillis = 700,
                        delayMillis = 1000,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {
                Box {

                    Text(
                        text = page.title,
                        style = TextStyle(
                            color = Color.Black.copy(alpha = 0.3f),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.offset(x = 2.dp, y = 2.dp)
                    )


                    Text(
                        text = page.title,
                        style = TextStyle(
                            color = colorSchemeState.value.textColor,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { it / 3 },
                    animationSpec = tween(
                        durationMillis = 600,
                        delayMillis = 1200,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(
                    animationSpec = tween(
                        durationMillis = 600,
                        delayMillis = 1200
                    )
                ) + expandVertically(
                    animationSpec = tween(
                        durationMillis = 600,
                        delayMillis = 1200,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {
                Text(
                    text = page.description,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = colorSchemeState.value.textColor.copy(alpha = 0.9f),
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }


        }
    }
}


