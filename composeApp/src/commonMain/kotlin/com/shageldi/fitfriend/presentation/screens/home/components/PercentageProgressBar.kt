package com.shageldi.fitfriend.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PercentageProgressBar(progress: Float) {
    val percentage = (progress * 100).toInt()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp)
            .height(24.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFFEDEDED)) // track color
    ) {
        // Progress fill
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .fillMaxHeight()
                .background(Color(0xFFB9F645))
        )

        // Percentage text (always visible on top)
        Text(
            text = "$percentage%",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 8.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview()
@Composable
fun PercentageProgressBarPreview() {
    PercentageProgressBar(progress = 0.45f)
}
