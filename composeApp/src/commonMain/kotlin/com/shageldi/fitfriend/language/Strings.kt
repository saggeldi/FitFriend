package com.shageldi.fitfriend.language

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.lyricist.LyricistStrings


data class Strings(
    val welcome: String,
    val countApples: (Int) -> String,
    val annotated: androidx.compose.ui.text.AnnotatedString,
)

@LyricistStrings(languageTag = "en", default = true)
val EnStrings = Strings(
    welcome = "Welcome!",
    countApples = { count -> "You have $count apples" },
    annotated = buildAnnotatedString {
        append("This is ")
        pushStyle(SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold))
        append("annotated")
        pop()
    }
)

@LyricistStrings(languageTag = "ru")
val RuStrings = Strings(
    welcome = "Добро пожаловать!",
    countApples = { count -> "У вас $count яблок" },
    annotated = buildAnnotatedString {
        append("Это ")
        pushStyle(SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold))
        append("аннотировано")
        pop()
    }
)

@LyricistStrings(languageTag = "tk")
val TkStrings = Strings(
    welcome = "Hoş geldiňiz!",
    countApples = { count -> "$count sany almaňyz bar" },
    annotated = buildAnnotatedString {
        append("Bu ")
        pushStyle(SpanStyle(color = Color.Green, fontWeight = FontWeight.Bold))
        append("bellikli")
        pop()
    }
)
