package com.shageldi.fitfriend.language

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.lyricist.LyricistStrings


data class Strings(
    val welcome: String,
    val onboardingTitle1: String,
    val onboardingDesc1: String,
    val onboardingTitle2: String,
    val onboardingDesc2: String,
    val onboardingTitle3: String,
    val onboardingDesc3: String,
    val onboardingTitle4: String,
    val onboardingDesc4: String,
    val getStarted: String,
    val freeTrial: String,
    // CTA Section
    val noMoreExcuses: String,
    val doItNow: String,
    val achieveGoals: String,
    val signIn: String,
    val signUp: String,
)

@LyricistStrings(languageTag = "en", default = true)
val EnStrings = Strings(
    welcome = "Welcome!",
    onboardingTitle1 = "Welcome!",
    onboardingDesc1 = "Your fitness journey starts here. Let’s move smarter, together.",

    onboardingTitle2 = "Discover",
    onboardingDesc2 = "Explore intelligent workouts and personalized insights just for you.",

    onboardingTitle3 = "Connect",
    onboardingDesc3 = "Join a vibrant community. Stay inspired, stay consistent.",

    onboardingTitle4 = "Achieve",
    onboardingDesc4 = "Track progress, smash goals, and enjoy every step of your transformation.",

    getStarted = "Get Started",
    freeTrial = "14 Day Free Trial",

    // CTA Section
    noMoreExcuses = "NO MORE EXCUSES |",
    doItNow = "DO IT NOW",
    achieveGoals = "Achieve your fitness goals with our expert trainers! Join us for personalized workouts that get results.",
    signIn = "SIGN IN",
    signUp = "SIGN UP"
)

@LyricistStrings(languageTag = "ru")
val RuStrings = Strings(
    welcome = "Добро пожаловать!",
    onboardingTitle1 = "Welcome!",
    onboardingDesc1 = "Your fitness journey starts here. Let’s move smarter, together.",

    onboardingTitle2 = "Discover",
    onboardingDesc2 = "Explore intelligent workouts and personalized insights just for you.",

    onboardingTitle3 = "Connect",
    onboardingDesc3 = "Join a vibrant community. Stay inspired, stay consistent.",

    onboardingTitle4 = "Achieve",
    onboardingDesc4 = "Track progress, smash goals, and enjoy every step of your transformation.",

    getStarted = "Get Started",
    freeTrial = "14 Day Free Trial",
    // CTA Section
    noMoreExcuses = "NO MORE EXCUSES |",
    doItNow = "DO IT NOW",
    achieveGoals = "Achieve your fitness goals with our expert trainers! Join us for personalized workouts that get results.",
    signIn = "SIGN IN",
    signUp = "SIGN UP"

)

@LyricistStrings(languageTag = "tk")
val TkStrings = Strings(
    welcome = "Hoş geldiňiz!",
    onboardingTitle1 = "Welcome!",
    onboardingDesc1 = "Your fitness journey starts here. Let’s move smarter, together.",

    onboardingTitle2 = "Discover",
    onboardingDesc2 = "Explore intelligent workouts and personalized insights just for you.",

    onboardingTitle3 = "Connect",
    onboardingDesc3 = "Join a vibrant community. Stay inspired, stay consistent.",

    onboardingTitle4 = "Achieve",
    onboardingDesc4 = "Track progress, smash goals, and enjoy every step of your transformation.",

    getStarted = "Get Started",
    freeTrial = "14 Day Free Trial",
    // CTA Section
    noMoreExcuses = "NO MORE EXCUSES |",
    doItNow = "DO IT NOW",
    achieveGoals = "Achieve your fitness goals with our expert trainers! Join us for personalized workouts that get results.",
    signIn = "SIGN IN",
    signUp = "SIGN UP"

)
