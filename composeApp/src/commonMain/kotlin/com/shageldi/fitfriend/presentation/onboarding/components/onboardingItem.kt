package com.shageldi.fitfriend.presentation.onboarding.components

import androidx.compose.runtime.Composable
import fitfriend.composeapp.generated.resources.Res
import fitfriend.composeapp.generated.resources.logo_new
import fitfriend.composeapp.generated.resources.onboard1
import fitfriend.composeapp.generated.resources.onboard2
import fitfriend.composeapp.generated.resources.onboard3
import fitfriend.composeapp.generated.resources.onboard4
import org.jetbrains.compose.resources.painterResource



val onboardingItem = listOf(
    OnboardingPage(
        "Welcome!",
        "Your fitness journey starts here. Let’s move smarter, together.",
        Res.drawable.onboard1
    ),
    OnboardingPage(
        "Discover",
        "Explore intelligent workouts and personalized insights just for you.",
        Res.drawable.onboard2
    ),
    OnboardingPage(
        "Connect",
        "Join a vibrant community. Stay inspired, stay consistent.",
        Res.drawable.onboard3
    ),
    OnboardingPage(
        "Achieve",
        "Track progress, smash goals, and enjoy every step of your transformation.",
        Res.drawable.onboard4
    )
)
