package com.shageldi.fitfriend.domain.model

data class TodayPlan(
    val title: String,
    val subtitle: String,
    val progress: Float,
    val level: String,
    val imageRes: Int
)