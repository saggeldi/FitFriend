package com.shageldi.fitfriend.presentation.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shageldi.fitfriend.language.LocalLyricist
import com.shageldi.fitfriend.ui.theme.LocalAppColorSchema
import fitfriend.composeapp.generated.resources.Res
import fitfriend.composeapp.generated.resources.sigup
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun AuthScreen(
    onSignIn: () -> Unit,
    onSignUp: () -> Unit
) {
    val colorSchemeState = LocalAppColorSchema.current
    val strings = LocalLyricist.current.strings


    var isVisible by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")


    val blurRadius by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "blur"
    )

    val contentAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(1000, delayMillis = 500),
        label = "contentAlpha"
    )

    val contentTranslationY by animateFloatAsState(
        targetValue = if (isVisible) 0f else 100f,
        animationSpec = tween(1000, delayMillis = 500, easing = EaseOutCubic),
        label = "contentTranslation"
    )

    val imageScale by animateFloatAsState(
        targetValue = if (isVisible) 1.1f else 1f,
        animationSpec = tween(8000, easing = EaseInOutSine),
        label = "imageScale"
    )


    val titleAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(800, delayMillis = 800),
        label = "titleAlpha"
    )

    val subtitleAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(800, delayMillis = 1000),
        label = "subtitleAlpha"
    )

    val descriptionAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(800, delayMillis = 1200),
        label = "descriptionAlpha"
    )

    val buttonsAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(800, delayMillis = 1400),
        label = "buttonsAlpha"
    )


    var signInPressed by remember { mutableStateOf(false) }
    var signUpPressed by remember { mutableStateOf(false) }

    val signInScale by animateFloatAsState(
        targetValue = if (signInPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "signInScale"
    )

    val signUpScale by animateFloatAsState(
        targetValue = if (signUpPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "signUpScale"
    )

    LaunchedEffect(Unit) {
        isVisible = true
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(Res.drawable.sigup),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = imageScale
                    scaleY = imageScale
                    renderEffect = BlurEffect(blurRadius, blurRadius)
                }
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorSchemeState.value.backgroundColor.copy(alpha = 0.8f),
                            Color.Transparent
                        ),
                        startY = 1000f,
                        endY = 0f
                    )
                )
                .alpha(contentAlpha)
        )


        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
                .padding(bottom = 100.dp)
                .graphicsLayer {
                    alpha = contentAlpha
                    translationY = contentTranslationY
                }
                .background(
                    colorSchemeState.value.backgroundColor.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.Start) {

                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(800, delayMillis = 800)
                    ) + fadeIn(animationSpec = tween(800, delayMillis = 800))
                ) {
                    Text(
                        text = strings.noMoreExcuses,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorSchemeState.value.textColor,
                        modifier = Modifier.alpha(titleAlpha)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))


                AnimatedVisibility(
                    visible = isVisible,
                    enter = scaleIn(
                        animationSpec = tween(600, delayMillis = 1000)
                    ) + fadeIn(animationSpec = tween(600, delayMillis = 1000))
                ) {
                    val pulseScale by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 1.05f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(2000, easing = EaseInOutSine),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "pulse"
                    )

                    Box(
                        modifier = Modifier
                            .graphicsLayer {
                                scaleX = pulseScale
                                scaleY = pulseScale
                            }
                            .background(
                                colorSchemeState.value.mainColor,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                            .alpha(subtitleAlpha)
                    ) {
                        Text(
                            text = strings.doItNow,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))


                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(
                        initialOffsetY = { it / 2 },
                        animationSpec = tween(800, delayMillis = 1200)
                    ) + fadeIn(animationSpec = tween(800, delayMillis = 1200))
                ) {
                    Text(
                        text = strings.achieveGoals,
                        fontSize = 14.sp,
                        color = Color.LightGray,
                        lineHeight = 20.sp,
                        modifier = Modifier.alpha(descriptionAlpha)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))


                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(
                        initialOffsetY = { it / 3 },
                        animationSpec = tween(800, delayMillis = 1400)
                    ) + fadeIn(animationSpec = tween(800, delayMillis = 1400))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(buttonsAlpha)
                    ) {
                        Button(
                            onClick = {
                                onSignIn()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(end = 8.dp)
                                .graphicsLayer {
                                    scaleX = signInScale
                                    scaleY = signInScale
                                }
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            signInPressed = true
                                            tryAwaitRelease()
                                            signInPressed = false
                                        }
                                    )
                                },
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Text(
                                strings.signIn,
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Button(
                            onClick = {
                                onSignUp()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorSchemeState.value.mainColor
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(start = 8.dp)
                                .graphicsLayer {
                                    scaleX = signUpScale
                                    scaleY = signUpScale
                                }
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            signUpPressed = true
                                            tryAwaitRelease()
                                            signUpPressed = false
                                        }
                                    )
                                },
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Text(
                                strings.signUp,
                                color = Color.Black,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}

