package com.shageldi.fitfriend.domain.model

import com.shageldi.fitfriend.ui.navigation.AppRoutes


sealed class BottomNavScreen(val route: String, val title: String, val iconUri: String) {
    object Home : BottomNavScreen(AppRoutes.HOME, "Home", "files/home.svg")
    object Explore : BottomNavScreen(AppRoutes.EXPLORE, "Explore", "files/explore.svg")
    object Analytics : BottomNavScreen(AppRoutes.ANALYTICS, "Analytics", "files/analytic.svg")
    object Profile : BottomNavScreen(AppRoutes.PROFILE, "Profile", "files/profile.svg")
}
