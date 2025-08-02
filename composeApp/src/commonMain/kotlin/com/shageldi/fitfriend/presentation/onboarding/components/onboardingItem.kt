package com.shageldi.fitfriend.presentation.onboarding.components

import androidx.compose.runtime.Composable
import fitfriend.composeapp.generated.resources.Res
import fitfriend.composeapp.generated.resources.logo_new
import org.jetbrains.compose.resources.painterResource



val onboardingItem = listOf(
    OnboardingPage(
        "Welcome",
        "Welcome to the App! Let's get started.",
        Res.drawable.logo_new
    ),
    OnboardingPage(
        "Discover",
        "Find amazing features and tools.",
        Res.drawable.logo_new
    ),
    OnboardingPage(
        "Connect",
         "Connect with others and grow.",
        Res.drawable.logo_new
    ),
    OnboardingPage(
        "Enjoy",
        "Start using the app now!",
        Res.drawable.logo_new
    )
)