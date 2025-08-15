package com.shageldi.fitfriend.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shageldi.fitfriend.domain.model.BottomNavScreen
import com.shageldi.fitfriend.presentation.screens.analytics.AnalyticsScreen
import com.shageldi.fitfriend.presentation.screens.explore.ExploreScreen
import com.shageldi.fitfriend.presentation.screens.home.HomeScreen
import com.shageldi.fitfriend.presentation.screens.profile.ProfileScreen

import com.shageldi.fitfriend.ui.components.BottomNavigationBar





@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomNavScreens = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Explore,
        BottomNavScreen.Analytics,
        BottomNavScreen.Profile
    )




    Scaffold(
        contentWindowInsets = WindowInsets.safeContent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .consumeWindowInsets(paddingValues)
        ) {

            NavHost(
                navController = navController,
                startDestination = BottomNavScreen.Home.route,
                modifier = Modifier
                    .fillMaxSize()


            ) {
                composable(BottomNavScreen.Home.route) { HomeScreen() }
                composable(BottomNavScreen.Explore.route) { ExploreScreen() }
                composable(BottomNavScreen.Analytics.route) { AnalyticsScreen() }
                composable(BottomNavScreen.Profile.route) { ProfileScreen() }
            }


            BottomNavigationBar(
                navController = navController,
                items = bottomNavScreens,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-16).dp)
                    .height(64.dp)

            )
        }
    }
}
