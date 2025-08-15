package com.shageldi.fitfriend.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shageldi.fitfriend.domain.model.TodayPlan
import com.shageldi.fitfriend.domain.model.WorkoutCard
import com.shageldi.fitfriend.presentation.screens.home.components.HomeHeader
import com.shageldi.fitfriend.presentation.screens.home.components.PopularWorkoutsSection
import com.shageldi.fitfriend.presentation.screens.home.components.TodayPlanSection
import com.shageldi.fitfriend.ui.theme.LocalAppColorSchema
import fitfriend.composeapp.generated.resources.Res
import fitfriend.composeapp.generated.resources.logo_new
import fitfriend.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen() {

    val userImage = painterResource(Res.drawable.profile)
    val colorSchemeState = LocalAppColorSchema.current

    val popularWorkouts = listOf(
        WorkoutCard(
            title = "Lower Body\nTraining",
            calories = "500 Kcal",
            duration = "50 Min",
            imageRes = 0,
            gradientColors = listOf(Color(0xFF8B5CF6), Color(0xFF3B82F6))
        ),
        WorkoutCard(
            title = "Hand\nTraining",
            calories = "600 Kcal",
            duration = "40 Min",
            imageRes = 0,
            gradientColors = listOf(Color(0xFF10B981), Color(0xFF059669))
        )
    )

    val todayPlans = listOf(
        TodayPlan(
            title = "Push Up",
            subtitle = "100 Push up a day",
            progress = 0.45f,
            level = "Intermediate",
            imageRes = 0
        ),
        TodayPlan(
            title = "Sit Up",
            subtitle = "20 Sit up a day",
            progress = 0.75f,
            level = "Beginner",
            imageRes = 0
        ),
        TodayPlan(
            title = "Sit Up",
            subtitle = "20 Sit up a day",
            progress = 0.75f,
            level = "Beginner",
            imageRes = 0
        ),
        TodayPlan(
            title = "Sit Up",
            subtitle = "20 Sit up a day",
            progress = 0.75f,
            level = "Beginner",
            imageRes = 0
        ),
        TodayPlan(
            title = "Knee Push Up",
            subtitle = "15 Knee push up a day",
            progress = 0.0f,
            level = "Beginner",
            imageRes = 0
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)),
    ) {
        item { HomeHeader(userImage = userImage) }
        item { PopularWorkoutsSection(popularWorkouts) }
        item { TodayPlanSection(todayPlans) }
    }




}