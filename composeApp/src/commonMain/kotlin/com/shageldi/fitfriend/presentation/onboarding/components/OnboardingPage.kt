package com.shageldi.fitfriend.presentation.onboarding.components

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key.Companion.R
import fitfriend.composeapp.generated.resources.Res
import fitfriend.composeapp.generated.resources.logo_new
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageResId: DrawableResource
)


