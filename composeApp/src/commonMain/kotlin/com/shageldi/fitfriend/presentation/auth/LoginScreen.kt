package com.shageldi.fitfriend.presentation.auth

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.shageldi.fitfriend.language.LocalLyricist
import com.shageldi.fitfriend.presentation.auth.components.CustomTextField
import com.shageldi.fitfriend.ui.theme.LocalAppColorSchema
import fitfriend.composeapp.generated.resources.Res
import fitfriend.composeapp.generated.resources.frame_sign
import fitfriend.composeapp.generated.resources.logo_new
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

import androidx.compose.foundation.layout.Box

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.shageldi.fitfriend.language.LocalLyricist
import com.shageldi.fitfriend.presentation.auth.components.CustomTextField
import com.shageldi.fitfriend.ui.theme.LocalAppColorSchema

import fitfriend.composeapp.generated.resources.frame_sign
import fitfriend.composeapp.generated.resources.logo_new
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource



@Composable
fun LoginScreen(onLogin: () -> Unit, onSignup: () -> Unit) {
    var email by remember { mutableStateOf("elementary221b@gmail.com") }
    var password by remember { mutableStateOf("**************") }
    var confirmPassword by remember { mutableStateOf("elementary221") }
    val colorSchemeState = LocalAppColorSchema.current
    val strings = LocalLyricist.current.strings
    val passwordsMatch = password == confirmPassword


    val animationVisible = remember { mutableStateOf(false) }
    val logoScale by animateFloatAsState(
        targetValue = if (animationVisible.value) 1f else 0.8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    val logoRotation by animateFloatAsState(
        targetValue = if (animationVisible.value) 0f else -10f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    val contentAlpha by animateFloatAsState(
        targetValue = if (animationVisible.value) 1f else 0f,
        animationSpec = tween(durationMillis = 800, delayMillis = 300)
    )
    val contentOffset by animateDpAsState(
        targetValue = if (animationVisible.value) 0.dp else 50.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    LaunchedEffect(Unit) {
        delay(100)
        animationVisible.value = true
    }

    Box(modifier = Modifier.fillMaxSize()
        .background( colorSchemeState.value.backgroundColor)) {
        Image(
            painter = painterResource(Res.drawable.frame_sign),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(250.dp)

                .graphicsLayer {
                    scaleX = logoScale
                    scaleY = logoScale
                    renderEffect = BlurEffect(2f, 2f)
                }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorSchemeState.value.backgroundColor.copy(alpha = 1f),
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
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
                .graphicsLayer {
                    alpha = contentAlpha
                    scaleX = logoScale
                    scaleY = logoScale
                    rotationZ = logoRotation
                }
        ) {
            Icon(
                painter = painterResource(Res.drawable.logo_new),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(120.dp)

            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorSchemeState.value.backgroundColor.copy(alpha = 0.2f),
                            Color.Transparent
                        ),
                        startY = 1000f,
                        endY = 0f
                    )
                )
                .alpha(contentAlpha)

        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Spacer(modifier = Modifier.height(250.dp))
                Text(
                    text = "Sign In To Ulpift",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = colorSchemeState.value.textColor,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Let’s personalize your fitness with AI",
                    fontSize = 16.sp,
                    color = colorSchemeState.value.textColor.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                    textAlign = TextAlign.Start
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(350.dp))
            }
            item {

                Column(
                    modifier = Modifier
                        .alpha(contentAlpha)
                        .offset(y = contentOffset)
                ) {
                    AnimatedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email Address",
                        icon = "files/mail.svg",
                        placeholder = "Enter your email",
                        keyboardType = KeyboardType.Email,
                        animationDelay = 400
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password",
                        icon = "files/lock.svg",
                        placeholder = "Enter your password",
                        isPassword = true,
                        keyboardType = KeyboardType.Password,
                        animationDelay = 500
                    )


                }

                Spacer(modifier = Modifier.height(80.dp))


                val buttonScale by animateFloatAsState(
                    targetValue = if (animationVisible.value) 1f else 0.9f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )

                Button(
                    onClick = {
                        onLogin()
//                        if (passwordsMatch && email.isNotBlank() && password.isNotBlank()) {
//
//                        }
                    },
                    shape = RoundedCornerShape(27.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .scale(buttonScale)
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(27.dp),
                            clip = false
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorSchemeState.value.mainColor
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 2.dp
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Sign In",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        AsyncImage(
                            model = Res.getUri("files/arrow_right.svg"),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                            ,
                            colorFilter = ColorFilter.tint(color = Color.Black)
                        )

                    }
                }

                Spacer(modifier = Modifier.height(24.dp))


                Row(
                    modifier = Modifier
                        .alpha(contentAlpha)
                        .offset(y = contentOffset)
                ) {
                    Text(
                        "Don’t have an account? ",
                        color = colorSchemeState.value.textColor.copy(alpha = 0.8f)
                    )
                    Text(
                        "Sign Up.",
                        color = colorSchemeState.value.mainColor,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier
                            .clickable {
                                onSignup()
                            }
                            .padding(horizontal = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Forgot Password",
                    color = colorSchemeState.value.mainColor,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier
                        .clickable {

                        }
                        .padding(horizontal = 4.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun AnimatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorMessage: String = "",
    animationDelay: Int = 0
) {
    val animationVisible = remember { mutableStateOf(false) }
    val fieldAlpha by animateFloatAsState(
        targetValue = if (animationVisible.value) 1f else 0f,
        animationSpec = tween(durationMillis = 600, delayMillis = animationDelay)
    )
    val fieldOffset by animateDpAsState(
        targetValue = if (animationVisible.value) 0.dp else 30.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    LaunchedEffect(Unit) {
        delay(100)
        animationVisible.value = true
    }

    Column(
        modifier = Modifier
            .alpha(fieldAlpha)
            .offset(y = fieldOffset)
    ) {
        CustomTextField(
            value = value,
            onValueChange = onValueChange,
            label = label,
            placeholder = placeholder,
            leadingIcon = icon,
            keyboardType = keyboardType,
            isPassword = isPassword,
            isError = isError,
            errorMessage = errorMessage
        )
    }
}