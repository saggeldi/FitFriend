package com.shageldi.fitfriend.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shageldi.fitfriend.language.LocalLyricist
import com.shageldi.fitfriend.ui.theme.LocalAppColorSchema
import com.shageldi.fitfriend.ui.theme.appDarkColorSchema
import fitfriend.composeapp.generated.resources.Res
import fitfriend.composeapp.generated.resources.logo
import fitfriend.composeapp.generated.resources.logo_new
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen() {
    val colorSchemeState = LocalAppColorSchema.current
    val strings = LocalLyricist.current.strings


//    LaunchedEffect(Unit) {
//
//        colorSchemeState.value = appDarkColorSchema
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorSchemeState.value.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource( Res.drawable.logo_new),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = strings.welcome ,
                color = colorSchemeState.value.mainColor,

            )
        }
    }
}

