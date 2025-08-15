package com.shageldi.fitfriend.presentation.screens.analytics


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shageldi.fitfriend.presentation.screens.analytics.components.ActiveCaloriesCard
import com.shageldi.fitfriend.presentation.screens.analytics.components.CalendarRow
import com.shageldi.fitfriend.presentation.screens.analytics.components.CyclingCard
import com.shageldi.fitfriend.presentation.screens.analytics.components.HeartRateCard
import com.shageldi.fitfriend.presentation.screens.analytics.components.SleepCard
import com.shageldi.fitfriend.presentation.screens.analytics.components.StepsCard
import com.shageldi.fitfriend.presentation.screens.analytics.components.TrainingTimeCard
import com.shageldi.fitfriend.presentation.screens.analytics.components.WaterCard

@Composable
fun AnalyticsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        // Calendar Row
        CalendarRow()

        Spacer(modifier = Modifier.height(24.dp))

        // Today Report Title
        Text(
            text = "Today Report",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Active Calories
        ActiveCaloriesCard()

        Spacer(modifier = Modifier.height(16.dp))

        // Training Time
        TrainingTimeCard()

        Spacer(modifier = Modifier.height(16.dp))

        // Cycling Map
        CyclingCard()

        Spacer(modifier = Modifier.height(16.dp))

        // Heart Rate and Steps Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                HeartRateCard()
            }
            Box(modifier = Modifier.weight(1f)) {
                StepsCard()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sleep and Water Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                SleepCard()
            }
            Box(modifier = Modifier.weight(1f)) {
                WaterCard()
            }
        }
    }
}