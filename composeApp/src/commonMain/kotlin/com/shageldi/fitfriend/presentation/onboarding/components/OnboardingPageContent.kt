package com.shageldi.fitfriend.presentation.onboarding.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardingPageContent(
    page: OnboardingPage,
    showFinishButton: Boolean = false,
    onFinish: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource( page.imageResId) ,
            contentDescription = page.title,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = page.title,

        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = page.description,

            textAlign = TextAlign.Center
        )
        if (showFinishButton && onFinish != null) {
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onFinish) {
                Text("Get Started")
            }
        }
    }
}
