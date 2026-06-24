package com.depogramming.kmpdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform