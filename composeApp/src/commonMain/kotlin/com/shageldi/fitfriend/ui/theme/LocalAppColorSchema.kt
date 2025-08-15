package com.shageldi.fitfriend.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

val LocalAppColorSchema = compositionLocalOf {
    mutableStateOf<AppColorScheme>(appLightColorSchema)
}