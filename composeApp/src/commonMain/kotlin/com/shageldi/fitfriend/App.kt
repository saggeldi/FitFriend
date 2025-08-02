package com.shageldi.fitfriend

import androidx.compose.runtime.*
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.rememberStrings
import com.shageldi.fitfriend.language.EnStrings
import com.shageldi.fitfriend.language.LocalLyricist
import com.shageldi.fitfriend.language.RuStrings
import com.shageldi.fitfriend.language.Strings
import com.shageldi.fitfriend.language.TkStrings
import com.shageldi.fitfriend.ui.navigation.PreferencesManager
import com.shageldi.fitfriend.ui.navigation.RootNavigation
import com.shageldi.fitfriend.ui.navigation.ThemeType
import com.shageldi.fitfriend.ui.theme.LocalAppColorSchema
import com.shageldi.fitfriend.ui.theme.appDarkColorSchema
import com.shageldi.fitfriend.ui.theme.appLightColorSchema

import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun App() {
    val preferencesManager = remember { PreferencesManager() }
    val colorSchemeState = remember {
        mutableStateOf(
            when (preferencesManager.getTheme()) {
                ThemeType.DARK -> appDarkColorSchema
                else -> appLightColorSchema
            }
        )
    }

    val currentLocale = preferencesManager.getLocale() ?: "en"

    val lyricist = rememberStrings(
        translations = mapOf(
            "en" to EnStrings,
            "ru" to RuStrings,
            "tk" to TkStrings
        ),
        defaultLanguageTag = "en",
        currentLanguageTag = currentLocale
    )

    CompositionLocalProvider(LocalAppColorSchema provides colorSchemeState, LocalLyricist provides lyricist) {
        RootNavigation()
    }
}