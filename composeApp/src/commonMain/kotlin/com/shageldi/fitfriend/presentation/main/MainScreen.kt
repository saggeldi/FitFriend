package com.shageldi.fitfriend.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
        bottomBar = {
            BottomNavigationBar(navController = navController,
                items = bottomNavScreens)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavScreen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(BottomNavScreen.Home.route) {
                HomeScreen()
            }
            composable(BottomNavScreen.Explore.route) {
                ExploreScreen()
            }
            composable(BottomNavScreen.Analytics.route) {
                AnalyticsScreen()
            }
            composable(BottomNavScreen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}


