package com.shageldi.fitfriend.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.shageldi.fitfriend.presentation.auth.LoginScreen
import com.shageldi.fitfriend.presentation.auth.SignupScreen
import com.shageldi.fitfriend.presentation.onboarding.OnboardingContent
import com.shageldi.fitfriend.presentation.onboarding.SplashScreen


@Composable
fun RootNavigation() {
    val navController = rememberNavController()
    val preferencesManager = remember { PreferencesManager() }
    var startDestination by remember { mutableStateOf(AppRoutes.SPLASH) }

    LaunchedEffect(Unit) {
        delay(1000)
        val onboarded = preferencesManager.isOnboardingCompleted()
        val loggedIn = preferencesManager.isLoggedIn()

        startDestination = when {
            !onboarded -> AppRoutes.ONBOARDING
            !loggedIn -> AppRoutes.LOGIN
            else -> AppRoutes.HOME
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppRoutes.SPLASH) {
            SplashScreen()
        }

        composable(AppRoutes.ONBOARDING) {
            OnboardingContent(onFinish = {
                preferencesManager.setOnboardingCompleted(true)
                navController.navigate(AppRoutes.LOGIN) {
                    popUpTo(AppRoutes.ONBOARDING) { inclusive = true }
                }
            })
        }

        composable(AppRoutes.LOGIN) {
            LoginScreen(
                onLogin = {
                    preferencesManager.setLoggedIn(true)
                    navController.navigate(AppRoutes.HOME) {
                        popUpTo(AppRoutes.LOGIN) { inclusive = true }
                    }
                },
                onSignup = {
                    navController.navigate(AppRoutes.SIGNUP)
                }
            )
        }

        composable(AppRoutes.SIGNUP) {
            SignupScreen(onSignupComplete = {
                preferencesManager.setLoggedIn(true)
                navController.navigate(AppRoutes.HOME) {
                    popUpTo(AppRoutes.SIGNUP) { inclusive = true }
                }
            })
        }


    }
}
