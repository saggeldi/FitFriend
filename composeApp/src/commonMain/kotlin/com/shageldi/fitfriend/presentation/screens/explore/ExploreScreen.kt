package com.shageldi.fitfriend.presentation.screens.explore


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ExploreScreen() {
    Text(
        "Explore Screen",
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .wrapContentSize(),
        color = Color.White // White text for contrast
    )
}
