package com.shageldi.fitfriend

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform