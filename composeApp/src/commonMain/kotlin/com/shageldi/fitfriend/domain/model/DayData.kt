package com.shageldi.fitfriend.domain.model

data class DayData(
    val day: String,
    val date: Int,
    val isSelected: Boolean = false,
    val isToday: Boolean = false
)
