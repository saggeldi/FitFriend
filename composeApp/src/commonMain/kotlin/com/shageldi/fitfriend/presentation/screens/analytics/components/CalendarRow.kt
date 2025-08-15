package com.shageldi.fitfriend.presentation.screens.analytics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shageldi.fitfriend.domain.model.DayData


@Composable
fun CalendarRow() {
    val days = listOf(
        DayData("S", 10, false),
        DayData("M", 11, false),
        DayData("T", 18, true), // Selected
        DayData("W", 13, false),
        DayData("T", 14, false),
        DayData("F", 15, false),
        DayData("S", 17, false)
    )

    Column {
        Text(
            text = "July 2022",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(days) { day ->
                CalendarDayItem(day)
            }
        }
    }
}

@Composable
fun CalendarDayItem(day: DayData) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(
                if (day.isSelected) Color(0xFF8BC34A)
                else Color.Transparent
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day.day,
                fontSize = 12.sp,
                color = if (day.isSelected) Color.White else Color.Gray
            )
            Text(
                text = day.date.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (day.isSelected) Color.White else Color.Black
            )
        }
    }
}