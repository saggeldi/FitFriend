package com.shageldi.fitfriend.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


data class MetricCard(
    val title: String,
    val value: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconColor: Color = Color.White
)