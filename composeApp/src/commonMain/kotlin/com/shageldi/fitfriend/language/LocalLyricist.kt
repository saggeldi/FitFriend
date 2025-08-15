package com.shageldi.fitfriend.language

import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.lyricist.Lyricist

val LocalLyricist = staticCompositionLocalOf<Lyricist<Strings>> {
    error("Lyricist not provided")
}
