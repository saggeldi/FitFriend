package com.shageldi.fitfriend.ui.navigation

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import com.russhwolf.settings.get

enum class ThemeType {
    LIGHT, DARK
}
class PreferencesManager {
    private val settings = Settings()
    private val onboardingKey = "onboarding_completed"
    private val loginKey = "user_logged_in"
    private val themeKey = "app_theme"
    private val localeKey = "app_locale"

    fun setOnboardingCompleted(completed: Boolean) {
        settings[onboardingKey] = completed
    }

    fun isOnboardingCompleted(): Boolean {
        return settings[onboardingKey, false]
    }

    fun setLoggedIn(loggedIn: Boolean) {
        settings[loginKey] = loggedIn
    }

    fun isLoggedIn(): Boolean {
        return settings[loginKey, false]
    }

    fun setTheme(theme: ThemeType) {
        settings[themeKey] = theme.name
    }

    fun getTheme(): ThemeType {
        val storedValue = settings[themeKey, ThemeType.LIGHT.name]
        return try {
            ThemeType.valueOf(storedValue)
        } catch (e: IllegalArgumentException) {
            ThemeType.LIGHT
        }
    }

    fun setLocale(localeTag: String) {
        settings[localeKey] = localeTag
    }

    fun getLocale(): String? {
        return settings.getStringOrNull(localeKey)
    }

}