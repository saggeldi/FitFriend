package com.shageldi.fitfriend.domain.model

import androidx.compose.ui.graphics.Color

data class WorkoutCard(
    val title: String,
    val calories: String,
    val duration: String,
    val imageRes: Int,
    val gradientColors: List<Color>
)