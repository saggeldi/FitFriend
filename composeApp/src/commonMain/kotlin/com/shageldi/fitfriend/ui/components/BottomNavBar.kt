package com.shageldi.fitfriend.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil3.compose.AsyncImage
import com.shageldi.fitfriend.domain.model.BottomNavScreen
import com.shageldi.fitfriend.language.LocalLyricist
import com.shageldi.fitfriend.ui.theme.LocalAppColorSchema
import fitfriend.composeapp.generated.resources.Res



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<BottomNavScreen>,
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val colorSchemeState = LocalAppColorSchema.current

    val springSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )

    val fastSpringSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessMedium
    )

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .animateContentSize(
                    animationSpec = tween(300, easing = FastOutSlowInEasing)
                ),
            shape = RoundedCornerShape(32.dp),
            color = Color(0xFF0F1113),

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items.forEach { screen ->
                    val isSelected = currentRoute == screen.route

                    val animatedWeight by animateFloatAsState(
                        targetValue = if (isSelected) 2f else 1f,
                        animationSpec = springSpec,
                        label = "weight_animation"
                    )

                    var isPressed by remember { mutableStateOf(false) }
                    val scale by animateFloatAsState(
                        targetValue = if (isPressed) 0.95f else 1f,
                        animationSpec = fastSpringSpec,
                        label = "scale_animation"
                    )

                    Box(
                        modifier = Modifier
                            .weight(animatedWeight)
                            .padding(horizontal = 4.dp)
                            .scale(scale)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = {
                                        isPressed = true
                                        tryAwaitRelease()
                                        isPressed = false
                                    },
                                    onTap = {
                                        if (currentRoute != screen.route) {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.startDestinationId) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    }
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        AnimatedContent(
                            targetState = isSelected,
                            transitionSpec = {

                                if (targetState) {

                                    slideInHorizontally(
                                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                                    ) { it / 2 } + fadeIn(
                                        animationSpec = tween(300, delayMillis = 100)
                                    ) + scaleIn(
                                        animationSpec = tween(300, delayMillis = 50),
                                        initialScale = 0.8f
                                    ) with slideOutHorizontally(
                                        animationSpec = tween(200, easing = FastOutLinearInEasing)
                                    ) { -it / 3 } + fadeOut(
                                        animationSpec = tween(150)
                                    ) + scaleOut(
                                        animationSpec = tween(200),
                                        targetScale = 1.1f
                                    )
                                } else {

                                    slideInHorizontally(
                                        animationSpec = tween(300, easing = LinearOutSlowInEasing)
                                    ) { -it / 3 } + fadeIn(
                                        animationSpec = tween(250, delayMillis = 50)
                                    ) + scaleIn(
                                        animationSpec = tween(250),
                                        initialScale = 1.2f
                                    ) with slideOutHorizontally(
                                        animationSpec = tween(350, easing = FastOutSlowInEasing)
                                    ) { it / 2 } + fadeOut(
                                        animationSpec = tween(200)
                                    ) + scaleOut(
                                        animationSpec = tween(300),
                                        targetScale = 0.7f
                                    )
                                }
                            },
                            label = "nav_item_animation"
                        ) { selected ->
                            if (selected) {

                                val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
                                val shimmerAlpha by infiniteTransition.animateFloat(
                                    initialValue = 0.6f,
                                    targetValue = 1f,
                                    animationSpec = infiniteRepeatable(
                                        animation = tween(1500, easing = LinearEasing),
                                        repeatMode = RepeatMode.Reverse
                                    ),
                                    label = "shimmer_animation"
                                )

                                Surface(
                                    color = colorSchemeState.value.mainColor.copy(alpha = shimmerAlpha),
                                    shape = RoundedCornerShape(24.dp),
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .height(45.dp)
                                        .widthIn(min = 80.dp)
                                        .animateContentSize(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                                stiffness = Spring.StiffnessMedium
                                            )
                                        )
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                    ) {

                                        val iconRotation by animateFloatAsState(
                                            targetValue = if (isSelected) 360f else 0f,
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                                stiffness = Spring.StiffnessMedium
                                            ),
                                            label = "icon_rotation"
                                        )

                                        AsyncImage(
                                            model = Res.getUri(screen.iconUri),
                                            contentDescription = screen.title,
                                            modifier = Modifier
                                                .size(22.dp)
                                                .rotate(iconRotation),
                                            colorFilter = ColorFilter.tint(Color.Black)
                                        )

                                        AnimatedVisibility(
                                            visible = true,
                                            enter = expandHorizontally(
                                                animationSpec = tween(300, delayMillis = 100)
                                            ) + fadeIn(
                                                animationSpec = tween(200, delayMillis = 150)
                                            ),
                                            exit = shrinkHorizontally() + fadeOut()
                                        ) {
                                            Spacer(modifier = Modifier.width(8.dp))
                                        }

                                        AnimatedVisibility(
                                            visible = true,
                                            enter = slideInHorizontally(
                                                animationSpec = tween(300, delayMillis = 200)
                                            ) { it } + fadeIn(
                                                animationSpec = tween(250, delayMillis = 200)
                                            ),
                                            exit = slideOutHorizontally() + fadeOut()
                                        ) {
                                            Text(
                                                text = screen.title,
                                                color = Color.Black,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            } else {

                                val breathingScale by animateFloatAsState(
                                    targetValue = 1f,
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessLow
                                    ),
                                    label = "breathing_animation"
                                )

                                val iconAlpha by animateFloatAsState(
                                    targetValue = 0.7f,
                                    animationSpec = tween(200),
                                    label = "icon_alpha"
                                )

                                AsyncImage(
                                    model = Res.getUri(screen.iconUri),
                                    contentDescription = screen.title,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .scale(breathingScale)
                                        .alpha(iconAlpha),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


