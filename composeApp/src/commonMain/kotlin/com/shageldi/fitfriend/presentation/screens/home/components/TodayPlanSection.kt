package com.shageldi.fitfriend.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shageldi.fitfriend.domain.model.TodayPlan

@Composable
fun TodayPlanSection(plans: List<TodayPlan>) {
    Column(
        modifier =  Modifier
            .padding(top = 16.dp, ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Today Plan",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            plans.forEach { plan ->
                TodayPlanCard(plan = plan)
            }
        }
    }
}