package com.mls.kmp.mor.nytnewskmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform